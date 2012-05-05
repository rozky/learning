package com.rozarltd.module.betfairdata.strategy;

import com.rozarltd.module.betfairdata.parser.BetfairDataRow;

class SelectionStats {
    private float minOdds = 1000;
    private float maxOdds = 0;
    private float startOdds = 0;
    private String startDate;

    public void update(BetfairDataRow row) {
        float odds = row.getOdds();
        if (odds > maxOdds) {
            maxOdds = odds;
        }

        if (odds < minOdds) {
            minOdds = odds;
        }

        if (startDate == null) {
            startDate = row.getFirstTaken();
            startOdds = odds;
        }
        else if (startDate.compareTo(row.getFirstTaken()) > 0) {
            startDate = row.getFirstTaken();
            startOdds = odds;
        }
    }

    public float getMinOdds() {
        return minOdds;
    }

    public float getMaxOdds() {
        return maxOdds;
    }

    public float getStartOdds() {
        return startOdds;
    }

    public String getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return String.format("[start = %s; min = %s; max = %s]", startOdds, minOdds, maxOdds);
    }
}
