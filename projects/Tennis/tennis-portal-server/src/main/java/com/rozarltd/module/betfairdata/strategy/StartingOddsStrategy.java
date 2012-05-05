package com.rozarltd.module.betfairdata.strategy;

public class StartingOddsStrategy extends StrategySupport {
    private float maxStartOdds = 2f;

    public StartingOddsStrategy() {
    }

    public StartingOddsStrategy(float maxStartOdds) {
        this.maxStartOdds = maxStartOdds;
    }

    public boolean isBetAllowed(SelectionStats selection) {
        return selection.getStartOdds() < maxStartOdds;
    }

    public float getOdds(SelectionStats selection) {
        return selection.getStartOdds();
    }

    @Override
    public String toString() {
        return "{" +
                "maxStartOdds=" + maxStartOdds +
                '}';
    }
}
