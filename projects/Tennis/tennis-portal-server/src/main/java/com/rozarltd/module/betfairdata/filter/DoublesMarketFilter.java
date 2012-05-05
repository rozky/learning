package com.rozarltd.module.betfairdata.filter;

import com.rozarltd.module.betfairdata.strategy.MarketData;

public class DoublesMarketFilter implements MarketDataFilter {
    @Override
    public boolean filter(MarketData marketData) {
        return marketData.getMarketName().contains("Doubles");
    }
}
