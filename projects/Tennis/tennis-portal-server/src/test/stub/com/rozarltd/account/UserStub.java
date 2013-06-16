package com.rozarltd.account;

public class UserStub  extends BetfairUser {
    public static final BetfairUser DEFAULT = new UserStub();

    public UserStub() {
        super("MichalR", "BetfairApiToken", "BetfairRestApiToken");
    }
}
