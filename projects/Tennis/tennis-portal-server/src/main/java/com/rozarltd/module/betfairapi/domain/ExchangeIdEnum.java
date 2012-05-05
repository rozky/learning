package com.rozarltd.module.betfairapi.domain;

public enum ExchangeIdEnum {
    uk(1);
    private int id;

    ExchangeIdEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
