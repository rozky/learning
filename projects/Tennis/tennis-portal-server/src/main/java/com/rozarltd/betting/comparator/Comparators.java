package com.rozarltd.betting.comparator;

import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;

import java.util.Comparator;

public abstract class Comparators {
    public static Comparator<BetfairMarket> sortByStartDate() {
        return new BetfairMarketByStartDateComparator();
    }
}
