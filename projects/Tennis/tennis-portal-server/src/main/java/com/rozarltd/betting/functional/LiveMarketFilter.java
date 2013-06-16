package com.rozarltd.betting.functional;

import com.googlecode.totallylazy.Predicate;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;

public class LiveMarketFilter implements Predicate<BetfairMarket> {
    @Override
    public boolean matches(BetfairMarket other) {
        return other.isInPlay();
    }
}
