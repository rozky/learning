package com.rozarltd.module.betfairapi.internal.filter;

import com.google.common.base.Predicate;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;

public class MarketNameFilter implements Predicate<BetfairMarket> {
    private final String marketName;

    public MarketNameFilter(String marketName) {
        this.marketName = marketName;
    }

    @Override
    public boolean apply(BetfairMarket market) {
        return marketName == null || marketName.equals(market.getMarketName());
    }
}
