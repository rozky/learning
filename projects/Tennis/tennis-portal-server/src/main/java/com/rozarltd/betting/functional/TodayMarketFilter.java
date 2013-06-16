package com.rozarltd.betting.functional;

import com.googlecode.totallylazy.Predicate;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.util.java.lang.DateUtilities;

import java.util.Date;

public class TodayMarketFilter implements Predicate<BetfairMarket> {

    private Date tomorrow = DateUtilities.tomorrow();

    @Override
    public boolean matches(BetfairMarket betfairMarket) {
        return !betfairMarket.isClosed() && tomorrow.after(betfairMarket.getStartAtDate());
    }
}
