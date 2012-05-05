package com.rozarltd.module.betfairdata.filter;

import com.rozarltd.module.betfairdata.strategy.MarketData;

public class WomanMarketFilter implements MarketDataFilter {
    @Override
    public boolean filter(MarketData marketData) {
        return marketData.getMarketName().contains("Fed Cup") || marketData.getMarketName().contains("Women");
    }
}
