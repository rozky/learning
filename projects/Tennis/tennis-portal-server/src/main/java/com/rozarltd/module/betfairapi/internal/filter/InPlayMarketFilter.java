package com.rozarltd.module.betfairapi.internal.filter;

import com.google.common.base.Predicate;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;

public class InPlayMarketFilter implements Predicate<BetfairMarket> {
    @Override
    public boolean apply(BetfairMarket market) {
        return market.isInPlay();
    }
}
