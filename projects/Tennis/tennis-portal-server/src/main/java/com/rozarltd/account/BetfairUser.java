package com.rozarltd.account;

import java.util.Date;

public class BetfairUser {
    private String username;
    private String publicApiToken;
    private String restApiToken;
    private Date createdAt;

    public BetfairUser(String username, String publicApiToken, String restApiToken) {
        this.username = username;
        this.publicApiToken = publicApiToken;
        this.restApiToken = restApiToken;
        this.createdAt = new Date();
    }

    public String getUsername() {
        return username;
    }

    public String getPublicApiToken() {
        return publicApiToken;
    }

    public String getRestApiToken() {
        return restApiToken;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
