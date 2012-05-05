package com.rozarltd.module.betfairapi.internal;

import java.util.Date;

public class BetfairSessionToken {
    private String token;
    private Date createdAt;

    public BetfairSessionToken(String token) {
        this.token = token;
        this.createdAt = new Date();
    }

    public String getToken() {
        return token;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
