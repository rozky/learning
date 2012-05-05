package com.rozarltd.betting.comparator;

import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;

import java.util.Comparator;

public class BetfairMarketByStartDateComparator implements Comparator<BetfairMarket> {
    public static Comparator<BetfairMarket> instance = new BetfairMarketByStartDateComparator();

    public static Comparator<BetfairMarket> getInstance() {
        return instance;
    }

    @Override
    public int compare(BetfairMarket o1, BetfairMarket o2) {
        boolean bothInPlay = o1.isInPlay() == o2.isInPlay();
        return  bothInPlay ? compareStartDate(o1, o2) : o1.isInPlay() ? -1 : 1;
    }

    private int compareStartDate(BetfairMarket o1, BetfairMarket o2) {
        return o1.getStartAt().equals(o2.getStartAt()) ? 0 : (o1.getStartAt() > o2.getStartAt() ? 1 : -1);
    }
}
