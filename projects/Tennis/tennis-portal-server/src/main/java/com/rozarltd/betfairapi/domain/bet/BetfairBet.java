package com.rozarltd.betfairapi.domain.bet;

import java.util.Date;

public class BetfairBet {
    private long betId;
    private long transactionId;
    private int marketId;
    private String marketName;
    private int selectionId;
    private String selectionName;
    private String fullMarketName;
    private double price;
    private double averagePrice;
    private double profitAndLost;
    private double matchedSize;
    private double size;
    private String betStatus;
    private String betType;
    private String marketType;
    private Date placedDate;
    private Date settledData;

    public String getBetState() {
        if(!"OK".equals(betStatus)) {
            return "FAILED";
        }

        if(matchedSize > 0.0) {
            return matchedSize == size ? "MATCHED" : "PART_MATCHED";
        }
        else {
            return "UNMATCHED";
        }
    }

    public long getBetId() {
        return betId;
    }

    public void setBetId(long betId) {
        this.betId = betId;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public int getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(int selectionId) {
        this.selectionId = selectionId;
    }

    public String getSelectionName() {
        return selectionName;
    }

    public void setSelectionName(String selectionName) {
        this.selectionName = selectionName;
    }

    public String getFullMarketName() {
        return fullMarketName;
    }

    public void setFullMarketName(String fullMarketName) {
        this.fullMarketName = fullMarketName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public double getProfitAndLost() {
        return profitAndLost;
    }

    public void setProfitAndLost(double profitAndLost) {
        this.profitAndLost = profitAndLost;
    }

    public double getMatchedSize() {
        return matchedSize;
    }

    public void setMatchedSize(double matchedSize) {
        this.matchedSize = matchedSize;
    }

    public String getBetStatus() {
        return betStatus;
    }

    public void setBetStatus(String betStatus) {
        this.betStatus = betStatus;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public Date getPlacedDate() {
        return placedDate;
    }

    public void setPlacedDate(Date placedDate) {
        this.placedDate = placedDate;
    }

    public Date getSettledData() {
        return settledData;
    }

    public void setSettledData(Date settledData) {
        this.settledData = settledData;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }
}
