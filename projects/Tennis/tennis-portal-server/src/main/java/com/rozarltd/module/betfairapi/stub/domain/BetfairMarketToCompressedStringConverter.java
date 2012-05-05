package com.rozarltd.module.betfairapi.stub.domain;

import com.google.common.base.Function;
import com.googlecode.functionalcollections.FunctionalIterables;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class BetfairMarketToCompressedStringConverter {
    private static final String FIELD_SEPARATOR = "~";
    private static final String MARKET_SEPARATOR = ":";

    public static String convert(BetfairMarket market) {
        return market.getMarketId() +
                FIELD_SEPARATOR + market.getMarketName() +
                FIELD_SEPARATOR + market.getMarketType() +
                FIELD_SEPARATOR + market.getMarketStatus() +
                FIELD_SEPARATOR + market.getStartAt() +
                FIELD_SEPARATOR + market.getMenuPath() +
                FIELD_SEPARATOR + market.getEventHierarchy() +
                FIELD_SEPARATOR + market.getBetDelay() +
                FIELD_SEPARATOR + market.getExchangeId() +
                FIELD_SEPARATOR + market.getCountryCodeISO3() +
                FIELD_SEPARATOR + market.getLastRefreshedAt() +
                FIELD_SEPARATOR + market.getNumberOfRunners() +
                FIELD_SEPARATOR + market.getNumberOfWinners() +
                FIELD_SEPARATOR + market.getTotalAmountMatched() +
                FIELD_SEPARATOR + market.getBspMarket() +
                FIELD_SEPARATOR + market.getTurningInPlay();
    }

    public static String convert(BetfairMarket... markets) {
        List<String> compressedMarkets = FunctionalIterables.make(markets).map(CompressBetfairMarketsMapFunction.getInstance()).toList();
        return StringUtils.join(compressedMarkets, MARKET_SEPARATOR);
    }

    private static class CompressBetfairMarketsMapFunction implements Function<BetfairMarket, String> {
        private static final CompressBetfairMarketsMapFunction instance = new CompressBetfairMarketsMapFunction();

        public static CompressBetfairMarketsMapFunction getInstance() {
            return instance;
        }

        @Override
        public String apply(BetfairMarket market) {
            return convert(market);
        }
    }
}
