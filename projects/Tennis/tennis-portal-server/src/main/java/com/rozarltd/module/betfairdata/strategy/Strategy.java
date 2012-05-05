package com.rozarltd.module.betfairdata.strategy;

public interface Strategy {
    public boolean isBetAllowed(MarketData marketStats);
    public boolean isBetAllowed(SelectionStats selection);
    public float getOdds(SelectionStats selection);
}
