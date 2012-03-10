package com.rozarltd.betfairapi.service;

import com.rozarltd.betfairapi.domain.account.Wallet;

public interface AccountService {
    public String login(String username, String password);
    public Wallet getAccountWallets(String sessionToken);
}
