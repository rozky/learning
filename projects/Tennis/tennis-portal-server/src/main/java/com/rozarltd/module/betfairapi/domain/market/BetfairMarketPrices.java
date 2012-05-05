package com.rozarltd.module.betfairapi.domain.market;

import java.util.ArrayList;
import java.util.List;

public class BetfairMarketPrices {
    private String marketId;
    private String inPlayDelay;
    private List<BetfairRunner> runners = new ArrayList<BetfairRunner>();

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getInPlayDelay() {
        return inPlayDelay;
    }

    public void setInPlayDelay(String inPlayDelay) {
        this.inPlayDelay = inPlayDelay;
    }

    public List<BetfairRunner> getRunners() {
        return runners;
    }

    public void setRunners(List<BetfairRunner> runners) {
        this.runners = runners;
    }
}
