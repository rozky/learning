package com.rozarltd.module.betfairdata.filter;

import com.rozarltd.module.betfairdata.strategy.MarketData;

public class FairOddsFilter implements MarketDataFilter {

    @Override
    public boolean filter(MarketData marketData) {
        return getOverRound(marketData) < 1.01;
    }

    // todo - implement
    private float getOverRound(MarketData market) {
        return 1.0f;
        // return (1 / this.getOdds(market.getWinningSelection())) + (1 / this.getOdds(market.getLosingSelection()));
    }
}
