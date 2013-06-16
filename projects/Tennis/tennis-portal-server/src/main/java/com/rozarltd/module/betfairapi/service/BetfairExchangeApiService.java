package com.rozarltd.module.betfairapi.service;


import com.betfair.publicapi.exchange.BFExchangeService;
import com.betfair.publicapi.exchange.types.ArrayOfMUBet;
import com.betfair.publicapi.exchange.types.BetStatusEnum;
import com.betfair.publicapi.exchange.types.CancelBetsReq;
import com.betfair.publicapi.exchange.types.CancelBetsResp;
import com.betfair.publicapi.exchange.types.GetAllMarketsErrorEnum;
import com.betfair.publicapi.exchange.types.GetAllMarketsReq;
import com.betfair.publicapi.exchange.types.GetAllMarketsResp;
import com.betfair.publicapi.exchange.types.GetCompleteMarketPricesCompressedReq;
import com.betfair.publicapi.exchange.types.GetCompleteMarketPricesCompressedResp;
import com.betfair.publicapi.exchange.types.GetCompleteMarketPricesErrorEnum;
import com.betfair.publicapi.exchange.types.GetMUBetsErrorEnum;
import com.betfair.publicapi.exchange.types.GetMUBetsReq;
import com.betfair.publicapi.exchange.types.GetMUBetsResp;
import com.betfair.publicapi.exchange.types.GetMarketErrorEnum;
import com.betfair.publicapi.exchange.types.GetMarketReq;
import com.betfair.publicapi.exchange.types.GetMarketResp;
import com.betfair.publicapi.exchange.types.MUBet;
import com.betfair.publicapi.exchange.types.Market;
import com.google.inject.Inject;
import com.rozarltd.application.ApplicationProperties;
import com.rozarltd.module.betfairapi.domain.bet.BetfairBet;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarketPrices;
import com.rozarltd.module.betfairapi.internal.factory.BFExchangeAPIRequestFactory;
import com.rozarltd.module.betfairapi.internal.mapper.ObjectTypeMapper;
import com.rozarltd.module.betfairapi.internal.mapper.ObjectTypeMapperManager;
import com.rozarltd.module.betfairapi.internal.parser.GetAllMarketResponseDataParser;
import com.rozarltd.util.java.lang.DoubleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BetfairExchangeApiService implements BFExchangeApiService {
    private static final Logger logger = LoggerFactory.getLogger(BetfairExchangeApiService.class);

    private BFExchangeService exchangeApiService;
    private BFExchangeAPIRequestFactory exchangeApiRequestFactory;
    private GetAllMarketResponseDataParser getAllMarketResponseDataParser;
    private ObjectTypeMapperManager typeMapperManager;
    private BetfairAccountApi betfairAccountService;
    private String betfairApiSessionToken;

    @Autowired
    @Inject
    public BetfairExchangeApiService(BFExchangeService exchangeApiService,
                                     BFExchangeAPIRequestFactory requestFactory,
                                     GetAllMarketResponseDataParser getAllMarketResponseDataParser,
                                     ObjectTypeMapperManager betfairApiTypeMapperManager,
                                     BetfairAccountApi accountService) {
        this.exchangeApiRequestFactory = requestFactory;
        this.exchangeApiService = exchangeApiService;
        this.getAllMarketResponseDataParser = getAllMarketResponseDataParser;
        this.typeMapperManager = betfairApiTypeMapperManager;
        this.betfairAccountService = accountService;
    }

    @Cacheable("betfairMarket")
    @Override
    public BetfairMarket getMarket(int marketId) {
        GetMarketReq request = exchangeApiRequestFactory.createGetMarketRequest(getBetfairApiSessionToken(), marketId);
        GetMarketResp response = exchangeApiService.getMarket(request);

        if(GetMarketErrorEnum.OK.equals(response.getErrorCode())) {
            return typeMapperManager.getMapper(Market.class, BetfairMarket.class).mapFrom(response.getMarket());
        }

        return null;
    }

//    @Cacheable("betfairMarkets")
    @Override
    public List<BetfairMarket> getMarkets(int eventId) {
        GetAllMarketsReq request =
                exchangeApiRequestFactory.createGetAllMarketsRequest(getBetfairApiSessionToken(), eventId);
        GetAllMarketsResp response = exchangeApiService.getAllMarkets(request);

        if(GetAllMarketsErrorEnum.OK == response.getErrorCode()) {
            return getAllMarketResponseDataParser.parse(response.getMarketData());
        }
        else {
            logger.info(String.format("The '%s' Betfair API request failed with error '%s'. Event Type: %s",
                    "GET ALL MARKETS", response.getErrorCode(), eventId));
        }

        return new ArrayList<BetfairMarket>();
    }

    @Cacheable("betfairMarketPrices")
    @Override
    public BetfairMarketPrices getMarketPrices(String marketId) {
        GetCompleteMarketPricesCompressedReq request = null;
        GetCompleteMarketPricesCompressedResp response = exchangeApiService.getCompleteMarketPricesCompressed(request);

        if(GetCompleteMarketPricesErrorEnum.OK.equals(response.getErrorCode())) {
            return new BetfairMarketPrices();
        }

        return null;
    }

    @Override
    public List<BetfairBet> getCurrentBets(String sessionToken) {
        GetMUBetsReq request = exchangeApiRequestFactory.createGetMUBetsRequest(sessionToken);
        GetMUBetsResp response = exchangeApiService.getMUBets(request);

        if(GetMUBetsErrorEnum.OK.equals(response.getErrorCode())) {
            List<BetfairBet> matchedBets =  new ArrayList<BetfairBet>();
            List<BetfairBet> unmatchedBets = new ArrayList<BetfairBet>();

            ArrayOfMUBet betArray = response.getBets();
            if(betArray != null && betArray.getMUBet() != null) {

                ObjectTypeMapper<MUBet, BetfairBet> typeMapper = typeMapperManager.getMapper(MUBet.class, BetfairBet.class);
                Map <Long, BetfairBet> groupedBets = new HashMap<Long, BetfairBet>();
                for(MUBet bet: betArray.getMUBet()) {
                    BetfairBet betfairBet = typeMapper.mapFrom(bet);

                    if(BetStatusEnum.U.equals(bet.getBetStatus())) {
                        unmatchedBets.add(betfairBet);
                    }
                    else {
                        if(groupedBets.containsKey(betfairBet.getBetId())) {
                            mergeBetfairBets(groupedBets.get(betfairBet.getBetId()), betfairBet);
                        }
                        else {
                            groupedBets.put(betfairBet.getBetId(), betfairBet);
                            matchedBets.add(betfairBet);
                        }
                    }
                }
            }

            // appends matched bets
            unmatchedBets.addAll(matchedBets);

            return unmatchedBets;
        }

        return null;
    }

    @Override
    public String cancelBet(String sessionToken, long betId) {
        CancelBetsReq request = exchangeApiRequestFactory.createCancelBetsRequest(sessionToken, betId);
        CancelBetsResp response = exchangeApiService.cancelBets(request);
        return response.getErrorCode().name();
    }

    private void mergeBetfairBets(BetfairBet betA, BetfairBet betB) {
        Assert.isTrue(betA.getBetId() == betB.getBetId());

        betA.setSize(betA.getSize() + betB.getSize());
        double profitAndLost = betA.getProfitAndLost() + betB.getProfitAndLost();
        betA.setProfitAndLost(DoubleUtils.valueOf(profitAndLost, 2));
    }

    private String getBetfairApiSessionToken() {
        if(betfairApiSessionToken == null) {
            String username = ApplicationProperties.getDevelopmentBetfairUsername();
            String password = ApplicationProperties.getDevelopmentBetfairPassword();

            betfairApiSessionToken = betfairAccountService.login(username, password);
        }

        return betfairApiSessionToken;
    }
}
