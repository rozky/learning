package com.rozarltd.module.betfairdata.strategy;

public class BettingStrategyResult {
    private int markets;
    private int betPlaced;
    private int betWon;
    private int betLost;
    private float betSize;
    private float investment;
    private float profit;
    private float profitMargin;
    private Strategy strategy;

    public int getMarkets() {
        return markets;
    }

    public void setMarkets(int markets) {
        this.markets = markets;
    }

    public int getBetPlaced() {
        return betPlaced;
    }

    public void setBetPlaced(int betPlaced) {
        this.betPlaced = betPlaced;
    }

    public int getBetWon() {
        return betWon;
    }

    public void setBetWon(int betWon) {
        this.betWon = betWon;
    }

    public int getBetLost() {
        return betLost;
    }

    public void setBetLost(int betLost) {
        this.betLost = betLost;
    }

    public float getBetSize() {
        return betSize;
    }

    public void setBetSize(float betSize) {
        this.betSize = betSize;
    }

    public float getInvestment() {
        return investment;
    }

    public void setInvestment(float investment) {
        this.investment = investment;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public float getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(float profitMargin) {
        this.profitMargin = profitMargin;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return "BettingStrategyResult{" +
                "strategy=" + strategy +
                ", profitMargin=" + profitMargin +
                ", markets=" + markets +
                ", betPlaced=" + betPlaced +
                ", betWon=" + betWon +
                ", betLost=" + betLost +
                ", betSize=" + betSize +
                ", investment=" + investment +
                ", profit=" + profit +
                '}';
    }
}
