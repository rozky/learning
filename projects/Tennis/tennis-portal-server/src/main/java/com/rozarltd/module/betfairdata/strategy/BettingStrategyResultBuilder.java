package com.rozarltd.module.betfairdata.strategy;

public class BettingStrategyResultBuilder {
    private float betSize = 1f;
    private int marketCount;
    private int wonMarketCount;
    private int lostMarketCount;
    private float wonAmount;
    private Strategy strategy;

    public void setBetSize(float betSize) {
        this.betSize = betSize;
    }

    public void addMarketCount(int marketCount) {
        this.marketCount += marketCount;
    }

    public void addWonMarketCount(int wonMarketCount) {
        this.wonMarketCount += wonMarketCount;
    }

    public void addLostMarketCount(int lostMarketCount) {
        this.lostMarketCount += lostMarketCount;
    }

    public void addWonAmount(float wonAmount) {
        this.wonAmount += wonAmount;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public BettingStrategyResult buildResult() {
        int placedBets = wonMarketCount + lostMarketCount;
        float investment = placedBets * betSize;
        float lostAmount = betSize * lostMarketCount;
        float profit = wonAmount - lostAmount;

        BettingStrategyResult result = new BettingStrategyResult();
        result.setStrategy(strategy);
        result.setMarkets(marketCount);
        result.setBetWon(wonMarketCount);
        result.setBetLost(lostMarketCount);
        result.setBetPlaced(placedBets);
        result.setBetSize(betSize);
        result.setInvestment(investment);
        result.setProfit(profit);
        result.setProfitMargin(profit / investment * 100);

        return result;
    }
}
