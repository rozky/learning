package com.rozarltd.domain.market;

import java.util.List;

public class Market {
    private int marketId;
    private String fullMarketName;
    private List<MarketRunner> runners;

    public Market() {
    }

    public Market(int marketId, String fullMarketName, List<MarketRunner> runners) {
        this.marketId = marketId;
        this.fullMarketName = fullMarketName;
        this.runners = runners;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public String getFullMarketName() {
        return fullMarketName;
    }

    public void setFullMarketName(String fullMarketName) {
        this.fullMarketName = fullMarketName;
    }

    public List<MarketRunner> getRunners() {
        return runners;
    }

    public void setRunners(List<MarketRunner> runners) {
        this.runners = runners;
    }

    public boolean hasRunners() {
        return this.runners != null && !runners.isEmpty();
    }
}
