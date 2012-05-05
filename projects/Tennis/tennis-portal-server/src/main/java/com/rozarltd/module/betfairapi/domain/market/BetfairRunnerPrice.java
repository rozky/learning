package com.rozarltd.module.betfairapi.domain.market;

public class BetfairRunnerPrice {
    private Double price;
    private Double totalAvailableToBack;
    private Double totalAvailableToLay;
    private Double totalBspLayLiability;
    private Double totalBspBackersStakeVolume;

    public BetfairRunnerPrice() {
    }

    public BetfairRunnerPrice(Double price,
                              Double totalAvailableToBack,
                              Double totalAvailableToLay,
                              Double totalBspLayLiability,
                              Double totalBspBackersStakeVolume) {
        this.price = price;
        this.totalAvailableToBack = totalAvailableToBack;
        this.totalAvailableToLay = totalAvailableToLay;
        this.totalBspLayLiability = totalBspLayLiability;
        this.totalBspBackersStakeVolume = totalBspBackersStakeVolume;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalAvailableToBack() {
        return totalAvailableToBack;
    }

    public void setTotalAvailableToBack(Double totalAvailableToBack) {
        this.totalAvailableToBack = totalAvailableToBack;
    }

    public Double getTotalAvailableToLay() {
        return totalAvailableToLay;
    }

    public void setTotalAvailableToLay(Double totalAvailableToLay) {
        this.totalAvailableToLay = totalAvailableToLay;
    }

    public Double getTotalBspLayLiability() {
        return totalBspLayLiability;
    }

    public void setTotalBspLayLiability(Double totalBspLayLiability) {
        this.totalBspLayLiability = totalBspLayLiability;
    }

    public Double getTotalBspBackersStakeVolume() {
        return totalBspBackersStakeVolume;
    }

    public void setTotalBspBackersStakeVolume(Double totalBspBackersStakeVolume) {
        this.totalBspBackersStakeVolume = totalBspBackersStakeVolume;
    }
}
