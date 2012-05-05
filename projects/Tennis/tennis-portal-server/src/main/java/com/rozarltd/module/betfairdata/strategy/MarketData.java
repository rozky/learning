package com.rozarltd.module.betfairdata.strategy;

import com.rozarltd.module.betfairdata.parser.BetfairDataRow;

public class MarketData {
    private int marketId;
    private String marketName;
    private SelectionStats winningSelection = new SelectionStats();
    private SelectionStats losingSelection = new SelectionStats();

    MarketData(int marketId, String marketName) {
        this.marketId = marketId;
        this.marketName = marketName;
    }

    public void update(BetfairDataRow row) {
        if(row.isInPlay()) {
            return;
        }

        if(row.isWin()) {
            winningSelection.update(row);
        }
        else {
            losingSelection.update(row);
        }
    }

    public int getMarketId() {
        return marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public SelectionStats getWinningSelection() {
        return winningSelection;
    }

    public SelectionStats getLosingSelection() {
        return losingSelection;
    }

    @Override
    public String toString() {
        return String.format("[name = %s; winning = %s; losing = %s;]", marketName, winningSelection, losingSelection);
    }
}
