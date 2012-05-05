package com.rozarltd.module.betfairdata.strategy;

public abstract class StrategySupport implements Strategy {

    @Override
    public boolean isBetAllowed(MarketData market) {
        boolean isBetAllowedOnWinSelection = this.isBetAllowed(market.getWinningSelection());
        boolean isBetAllowedOnLoseSelection = this.isBetAllowed(market.getLosingSelection());


        float overRound = getOverRound(market);
        // overRound < 1.15 &&
        return
                ((isBetAllowedOnWinSelection && !isBetAllowedOnLoseSelection)
                || (!isBetAllowedOnWinSelection && isBetAllowedOnLoseSelection));
    }

    private float getOverRound(MarketData market) {
        return (1 / this.getOdds(market.getWinningSelection())) + (1 / this.getOdds(market.getLosingSelection()));
    }
}
