package com.rozarltd.betting.functional;

import com.google.common.base.Predicate;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;

public abstract class Filters {

    public static Predicate<BetfairMarket> todayMarket() {
        return new TodayMarketFilter();
    }
}
