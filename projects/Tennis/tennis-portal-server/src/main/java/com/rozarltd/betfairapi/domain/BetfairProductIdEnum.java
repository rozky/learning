package com.rozarltd.betfairapi.domain;

public enum BetfairProductIdEnum {
    FREE_API(82);

    private int id;

    BetfairProductIdEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
