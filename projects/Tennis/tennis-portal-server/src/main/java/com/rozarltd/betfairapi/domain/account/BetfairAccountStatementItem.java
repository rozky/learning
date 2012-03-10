package com.rozarltd.betfairapi.domain.account;

public class BetfairAccountStatementItem {
    private int eventId;
    private int eventTypeId;
    private long betId;
    private String marketName;
    private int selectionId;
    private String selectionName;
    private double stake;
    private double price;
    private double amount;
    private double grossBetAmount;
    private double accountBalance;
    private String winLost;
    private String betType;
    private String transactionType;
    private long placedAt;
    private long settledAt;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public long getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public long getBetId() {
        return betId;
    }

    public void setBetId(long betId) {
        this.betId = betId;
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

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getGrossBetAmount() {
        return grossBetAmount;
    }

    public void setGrossBetAmount(double grossBetAmount) {
        this.grossBetAmount = grossBetAmount;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public long getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(long placedAt) {
        this.placedAt = placedAt;
    }

    public long getSettledAt() {
        return settledAt;
    }

    public void setSettledAt(long settledAt) {
        this.settledAt = settledAt;
    }

    public String getWinLost() {
        return winLost;
    }

    public void setWinLost(String winLost) {
        this.winLost = winLost;
    }
}
