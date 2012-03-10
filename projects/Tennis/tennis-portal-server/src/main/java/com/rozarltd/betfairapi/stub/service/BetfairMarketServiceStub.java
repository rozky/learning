package com.rozarltd.betfairapi.stub.service;

import com.rozarltd.betfairapi.domain.bet.BetfairBet;
import com.rozarltd.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.betfairapi.domain.market.BetfairMarketPrices;
import com.rozarltd.betfairapi.service.BFExchangeApiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BetfairMarketServiceStub implements BFExchangeApiService {

    @Override
    public BetfairMarket getMarket(int marketId) {
        return null;
    }

    @Override
    public List<BetfairMarket> getMarkets(int eventId) {
        List<BetfairMarket> markets = new ArrayList<BetfairMarket>();
        return markets;
    }

    @Override
    public BetfairMarketPrices getMarketPrices(String marketId) {
        return null;
    }

    @Override
    public List<BetfairBet> getCurrentBets(String sessionToken) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String cancelBet(String sessionToken, long betId) {
        return null;
    }
}
