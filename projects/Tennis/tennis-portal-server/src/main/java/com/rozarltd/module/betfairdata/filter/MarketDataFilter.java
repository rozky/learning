package com.rozarltd.module.betfairdata.filter;

import com.rozarltd.module.betfairdata.strategy.MarketData;

public interface MarketDataFilter {
    public boolean filter(MarketData marketData);
}
