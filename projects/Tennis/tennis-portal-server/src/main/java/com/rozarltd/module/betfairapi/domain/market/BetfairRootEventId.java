package com.rozarltd.module.betfairapi.domain.market;

public enum BetfairRootEventId {
    tennis(2);

    private int eventId;

    BetfairRootEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }
}
