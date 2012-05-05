package com.rozarltd.module.betfairdata.strategy;

class AdvanceDelayedStartingOddsStrategy extends StartingOddsStrategy {
    private float rate = 1.6f;

    public boolean isBetAllowed(SelectionStats selection) {
        return super.isBetAllowed(selection) && ( passAdvanceCondition(selection) ? (selection.getStartOdds() * rate < selection.getMaxOdds()) : true);
    }

    public float getOdds(SelectionStats selection) {
        return passAdvanceCondition(selection) ? selection.getStartOdds() * rate : super.getOdds(selection);
    }

    private boolean passAdvanceCondition(SelectionStats selection) {
        return selection.getStartOdds() > 1.5;
    }
}
