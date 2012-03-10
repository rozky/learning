package com.rozarltd.domain.account;

import java.util.Date;

public class User {
    private String username;
    private String betfairPublicApiToken;
    private String betfairRestApiToken;
    private Date createdAt;

    public User(String username, String betfairPublicApiToken, String betfairRestApiToken) {
        this.username = username;
        this.betfairPublicApiToken = betfairPublicApiToken;
        this.betfairRestApiToken = betfairRestApiToken;
        this.createdAt = new Date();
    }

    public String getUsername() {
        return username;
    }

    public String getBetfairPublicApiToken() {
        return betfairPublicApiToken;
    }

    public String getBetfairRestApiToken() {
        return betfairRestApiToken;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
