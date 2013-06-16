package com.rozarltd.module.betfairapi.domain.market;

public enum BetfairPopularEvent {
    tennis(2);

    private int eventId;

    BetfairPopularEvent(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }
}
