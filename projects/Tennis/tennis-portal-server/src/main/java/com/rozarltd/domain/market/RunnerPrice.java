package com.rozarltd.domain.market;

public class RunnerPrice {
    private double price;
    private double amount;

    public RunnerPrice(double price, double amount) {
        this.price = price;
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public double getAmount() {
        return amount;
    }
}
