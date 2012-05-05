package com.rozarltd.module.betfairapi.domain;

public enum BetfairEventId {
    tennis(2);

    private int id;

    BetfairEventId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
