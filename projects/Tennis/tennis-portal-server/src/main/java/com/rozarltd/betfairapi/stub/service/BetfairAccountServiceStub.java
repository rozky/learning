package com.rozarltd.betfairapi.stub.service;

import com.rozarltd.betfairapi.domain.account.Wallet;
import com.rozarltd.betfairapi.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class BetfairAccountServiceStub implements AccountService {

    @Override
    public String login(String username, String password) {
        return "valid-session-token";
    }

    @Override
    public Wallet getAccountWallets(String sessionToken) {
        return new Wallet("UK", true);
    }
}
