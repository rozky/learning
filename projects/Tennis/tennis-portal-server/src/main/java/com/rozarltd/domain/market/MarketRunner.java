package com.rozarltd.domain.market;

public class MarketRunner {
    private int runnerId;
    private String runnerName;
    private int marketId;
    private RunnerPrice bestBackPrice;
    private RunnerPrice bestLayPrice;

    public MarketRunner() {
    }

    public MarketRunner(int runnerId, String runnerName, RunnerPrice bestBackPrice, RunnerPrice bestLayPrice) {
        this.runnerId = runnerId;
        this.runnerName = runnerName;
        this.bestBackPrice = bestBackPrice;
        this.bestLayPrice = bestLayPrice;
    }

    public int getRunnerId() {
        return runnerId;
    }

    public void setRunnerId(int runnerId) {
        this.runnerId = runnerId;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    public RunnerPrice getBestBackPrice() {
        return bestBackPrice;
    }

    public void setBestBackPrice(RunnerPrice bestBackPrice) {
        this.bestBackPrice = bestBackPrice;
    }

    public RunnerPrice getBestLayPrice() {
        return bestLayPrice;
    }

    public void setBestLayPrice(RunnerPrice bestLayPrice) {
        this.bestLayPrice = bestLayPrice;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }
}
