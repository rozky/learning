package com.rozarltd.module.betfairapi.service.market;

import com.rozarltd.domain.market.Market;

import java.util.Collection;

public interface MarketRefresher {
    public void refresh(Collection<Market> markets);
}
