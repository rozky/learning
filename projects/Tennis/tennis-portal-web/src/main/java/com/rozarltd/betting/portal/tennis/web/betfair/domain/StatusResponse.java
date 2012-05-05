package com.rozarltd.betting.portal.tennis.web.betfair.domain;

public class StatusResponse {
    public static StatusResponse ERROR = new StatusResponse("FAILED");
    public static StatusResponse OK = new StatusResponse("OK");

    private String status;

    public StatusResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
