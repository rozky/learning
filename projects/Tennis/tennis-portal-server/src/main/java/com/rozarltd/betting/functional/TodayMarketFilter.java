package com.rozarltd.betting.functional;

import com.google.common.base.Predicate;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.util.java.lang.DateUtilities;

import java.util.Date;

public class TodayMarketFilter implements Predicate<BetfairMarket> {

    private Date tomorrow = DateUtilities.tomorrow();

    @Override
    public boolean apply(BetfairMarket market) {
          return !market.isClosed() && tomorrow.after(market.getStartAtDate());
    }
}
