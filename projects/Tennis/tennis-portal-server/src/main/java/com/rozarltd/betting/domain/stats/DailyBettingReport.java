package com.rozarltd.betting.domain.stats;

import java.util.Date;

public class DailyBettingReport {
    private Date date;
    private double startBalance;
    private double endBalance;
    private double deposits;
    private double withdrawals;
    private int placedBets;
    private int wonBets;
    private int lostBets;
    private double totalAmountWon;
    private double totalAmountLost;
    private double totalAmountPlaced;
    private double roi;
    private double profit;
    private double commissionPaid;
    private int markets;
    private int wonMarkets;
    private int lostMarkets;
    private ReportStatus status;
    private int illegalBets;
    private int wonIllegalBets;
    private int lostIllegalBets;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(double startBalance) {
        this.startBalance = startBalance;
    }

    public double getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(double endBalance) {
        this.endBalance = endBalance;
    }

    public double getDeposits() {
        return deposits;
    }

    public void setDeposits(double deposits) {
        this.deposits = deposits;
    }

    public double getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(double withdrawals) {
        this.withdrawals = withdrawals;
    }

    public int getPlacedBets() {
        return placedBets;
    }

    public void setPlacedBets(int placedBets) {
        this.placedBets = placedBets;
    }

    public int getWonBets() {
        return wonBets;
    }

    public void setWonBets(int wonBets) {
        this.wonBets = wonBets;
    }

    public int getLostBets() {
        return lostBets;
    }

    public void setLostBets(int lostBets) {
        this.lostBets = lostBets;
    }

    public double getTotalAmountPlaced() {
        return totalAmountPlaced;
    }

    public void setTotalAmountPlaced(double totalAmountPlaced) {
        this.totalAmountPlaced = totalAmountPlaced;
    }

    public double getRoi() {
        return roi;
    }

    public void setRoi(double roi) {
        this.roi = roi;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getMarkets() {
        return markets;
    }

    public void setMarkets(int markets) {
        this.markets = markets;
    }

    public double getCommissionPaid() {
        return commissionPaid;
    }

    public void setCommissionPaid(double commissionPaid) {
        this.commissionPaid = commissionPaid;
    }

    public double getTotalAmountWon() {
        return totalAmountWon;
    }

    public void setTotalAmountWon(double totalAmountWon) {
        this.totalAmountWon = totalAmountWon;
    }

    public double getTotalAmountLost() {
        return totalAmountLost;
    }

    public void setTotalAmountLost(double totalAmountLost) {
        this.totalAmountLost = totalAmountLost;
    }

    public int getWonMarkets() {
        return wonMarkets;
    }

    public void setWonMarkets(int wonMarkets) {
        this.wonMarkets = wonMarkets;
    }

    public int getLostMarkets() {
        return lostMarkets;
    }

    public void setLostMarkets(int lostMarkets) {
        this.lostMarkets = lostMarkets;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }
}
