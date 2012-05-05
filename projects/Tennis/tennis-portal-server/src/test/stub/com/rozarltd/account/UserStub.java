package com.rozarltd.account;

public class UserStub  extends User {
    public static final User DEFAULT = new UserStub();

    public UserStub() {
        super("MichalR", "BetfairApiToken", "BetfairRestApiToken");
    }
}
