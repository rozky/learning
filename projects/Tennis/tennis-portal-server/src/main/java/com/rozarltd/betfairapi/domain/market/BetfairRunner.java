package com.rozarltd.betfairapi.domain.market;

import java.util.List;

public class BetfairRunner {
    private int runnerId;
    private String runnerName;
    private Integer orderIndex;
    private Double totalAmountMatched;
    private Double lastPriceMatched;
    private Double handicap;
    private Double reductionFactor;
    private boolean vacant;
    private Integer asianLineId;
    private Double farSpPrice;
    private Double nearSpPrice;
    private Double actualSpPrice;
    private List<BetfairRunnerPrice> prices;
    private List<BetfairRunnerPrice> backPrices;
    private List<BetfairRunnerPrice> layPrices;
    private BetfairRunnerPrice bestBackPrice;
    private BetfairRunnerPrice bestLayPrice;

    public BetfairRunner() {
    }

    public BetfairRunner(int runnerId, String runnerName) {
        this.runnerId = runnerId;
        this.runnerName = runnerName;
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

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Double getTotalAmountMatched() {
        return totalAmountMatched;
    }

    public void setTotalAmountMatched(Double totalAmountMatched) {
        this.totalAmountMatched = totalAmountMatched;
    }

    public Double getLastPriceMatched() {
        return lastPriceMatched;
    }

    public void setLastPriceMatched(Double lastPriceMatched) {
        this.lastPriceMatched = lastPriceMatched;
    }

    public Double getHandicap() {
        return handicap;
    }

    public void setHandicap(Double handicap) {
        this.handicap = handicap;
    }

    public Double getReductionFactor() {
        return reductionFactor;
    }

    public void setReductionFactor(Double reductionFactor) {
        this.reductionFactor = reductionFactor;
    }

    public boolean isVacant() {
        return vacant;
    }

    public void setVacant(boolean vacant) {
        this.vacant = vacant;
    }

    public Integer getAsianLineId() {
        return asianLineId;
    }

    public void setAsianLineId(Integer asianLineId) {
        this.asianLineId = asianLineId;
    }

    public Double getFarSpPrice() {
        return farSpPrice;
    }

    public void setFarSpPrice(Double farSpPrice) {
        this.farSpPrice = farSpPrice;
    }

    public Double getNearSpPrice() {
        return nearSpPrice;
    }

    public void setNearSpPrice(Double nearSpPrice) {
        this.nearSpPrice = nearSpPrice;
    }

    public Double getActualSpPrice() {
        return actualSpPrice;
    }

    public void setActualSpPrice(Double actualSpPrice) {
        this.actualSpPrice = actualSpPrice;
    }

    public List<BetfairRunnerPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<BetfairRunnerPrice> prices) {
        this.prices = prices;
    }

    public List<BetfairRunnerPrice> getBackPrices() {
        return backPrices;
    }

    public void setBackPrices(List<BetfairRunnerPrice> backPrices) {
        this.backPrices = backPrices;
    }

    public List<BetfairRunnerPrice> getLayPrices() {
        return layPrices;
    }

    public void setLayPrices(List<BetfairRunnerPrice> layPrices) {
        this.layPrices = layPrices;
    }

    public BetfairRunnerPrice getBestBackPrice() {
        return bestBackPrice;
    }

    public void setBestBackPrice(BetfairRunnerPrice bestBackPrice) {
        this.bestBackPrice = bestBackPrice;
    }

    public BetfairRunnerPrice getBestLayPrice() {
        return bestLayPrice;
    }

    public void setBestLayPrice(BetfairRunnerPrice bestLayPrice) {
        this.bestLayPrice = bestLayPrice;
    }
}
