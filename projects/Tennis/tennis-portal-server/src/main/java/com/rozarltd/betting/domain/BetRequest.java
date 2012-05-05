package com.rozarltd.betting.domain;

public class BetRequest {
    private int marketId;
    private int selectionId;
    private double price;
    private double stake;

    public BetRequest() {
    }

    public BetRequest(int marketId, int selectionId, double price, double stake) {
        this.marketId = marketId;
        this.selectionId = selectionId;
        this.price = price;
        this.stake = stake;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public int getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(int selectionId) {
        this.selectionId = selectionId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }
}
