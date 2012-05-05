package com.rozarltd.module.betfairdata.strategy;

class DelayedStartingOddsStrategy extends StartingOddsStrategy {
    private float maxStartOdds = 1.5f;
    private float rate = 1.5f;


    public boolean isBetAllowed(SelectionStats selection) {
        return selection.getStartOdds() < maxStartOdds && (selection.getStartOdds() * rate < selection.getMaxOdds());
    }

    public float getOdds(SelectionStats selection) {
        return selection.getStartOdds() * rate;
    }
}
