package com.rozarltd.betting.functional;

import com.googlecode.totallylazy.Predicate;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;

public abstract class Filters {

    public static Predicate<BetfairMarket> todayMarket() {
        return new TodayMarketFilter();
    }

    public static Predicate<BetfairMarket> liveMarket() {
        return new LiveMarketFilter();
    }
}
