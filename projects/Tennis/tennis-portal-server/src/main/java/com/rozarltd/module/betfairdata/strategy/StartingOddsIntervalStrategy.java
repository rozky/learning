package com.rozarltd.module.betfairdata.strategy;

public class StartingOddsIntervalStrategy extends StrategySupport {
    private float minOdds;
    private float maxOdds;

    public StartingOddsIntervalStrategy(float minOdds, float maxOdds) {
        this.minOdds = minOdds;
        this.maxOdds = maxOdds;
    }

    @Override
    public boolean isBetAllowed(SelectionStats selection) {
        return selection.getStartOdds() >= minOdds && selection.getStartOdds() < maxOdds ;
    }

    @Override
    public float getOdds(SelectionStats selection) {
        return selection.getStartOdds();
    }

    @Override
    public String toString() {
        return "{" +
                "minOdds=" + minOdds +
                ", maxOdds=" + maxOdds +
                '}';
    }
}
