package com.rozarltd.module.betfairapi.internal.parser;

import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.util.java.lang.IntegerUtils;
import com.rozarltd.util.java.lang.LongUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// todo - implement interface
@Component
public class GetAllMarketResponseDataParser implements CompressedDataParser<List<BetfairMarket>> {
    private static final int FIELD_COUNT = 16;

    private static final Logger logger = LoggerFactory.getLogger(GetAllMarketResponseDataParser.class);

    // move to external config file
    private static final String MARKETS_DELIMITER = ":";
    private static final String MARKET_FIELDS_DELIMITER = "~";
    private static final String MENU_PATH_SEPARATOR = "\\\\";

    public List<BetfairMarket> parse(String marketsToken) {
        List<BetfairMarket> marketList = new ArrayList<BetfairMarket>();
        if (!StringUtils.isBlank(marketsToken)) {
            String markets[] = marketsToken.split(MARKETS_DELIMITER);


            for (String marketAsString : markets) {
                if (!StringUtils.isEmpty(marketAsString)) {
                    BetfairMarket market = parseMarket(marketAsString);
    //                if(market != null && "Match Odds".equals(market.getMarketName())) {
    //                    marketList.add(market);
    //                }

                    if(market != null) {
                        marketList.add(market);
                    }
                }
            }
        }

        return marketList;
    }

    private BetfairMarket parseMarket(String marketToken) {
        BetfairMarket market = null;
        if (!StringUtils.isBlank(marketToken)) {
            String[] fields = marketToken.split(MARKET_FIELDS_DELIMITER);

            if (fields.length == FIELD_COUNT) {
                market = new BetfairMarket();
                market.setMarketId(IntegerUtils.valueOf(fields[MarketField.marketId.getPosition()]));
                market.setMarketName(fields[MarketField.marketName.getPosition()]);
                market.setMarketType(fields[MarketField.marketType.getPosition()]);
                market.setMarketStatus(fields[MarketField.marketStatus.getPosition()]);
                market.setStartAt(LongUtils.valueOf(fields[MarketField.startAt.getPosition()]));
                market.setMenuPath(fields[MarketField.menuPath.getPosition()]);
                market.setEventHierarchy(fields[MarketField.eventHierarchy.getPosition()]);
                market.setBetDelay(fields[MarketField.betDelay.getPosition()]);
                market.setExchangeId(fields[MarketField.exchangeId.getPosition()]);
                market.setCountryCodeISO3(fields[MarketField.countryCodeISO3.getPosition()]);
                market.setLastRefreshedAt(fields[MarketField.lastRefreshedAt.getPosition()]);
                market.setNumberOfRunners(fields[MarketField.numberOfRunners.getPosition()]);
                market.setNumberOfWinners(fields[MarketField.numberOfWinners.getPosition()]);
                market.setTotalAmountMatched(fields[MarketField.totalAmountMatched.getPosition()]);
                market.setBspMarket(fields[MarketField.bspMarket.getPosition()]);
                market.setTurningInPlay(fields[MarketField.turningInPlay.getPosition()]);
            }
            else {
                logger.warn("Unable to parse market string. Value=" + marketToken);
            }
        }

        return market;
    }

    private enum MarketField {
        marketId(0),
        marketName(1),
        marketType(2),
        marketStatus(3),
        startAt(4),
        menuPath(5),
        eventHierarchy(6),
        betDelay(7),
        exchangeId(8),
        countryCodeISO3(9),
        lastRefreshedAt(10),
        numberOfRunners(11),
        numberOfWinners(12),
        totalAmountMatched(13),
        bspMarket(14),
        turningInPlay(15);

        private int position;

        private MarketField(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }
    }
}
