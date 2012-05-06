package com.rozarltd.module.betfairapi.service;

import com.google.inject.Inject;
import com.rozarltd.account.User;
import com.rozarltd.module.betfairapi.domain.account.Wallet;

import java.util.ArrayList;
import java.util.List;

public class BetfairAccountFacade implements AccountFacade {
    private BetfairAccountService betfairAccountService;

    @Inject
    public BetfairAccountFacade(BetfairAccountService betfairAccountService) {
        this.betfairAccountService = betfairAccountService;
    }

    @Override
    public List<Wallet> getWallets(User user) {
        List<Wallet> wallets = new ArrayList<Wallet>();
        wallets.add(betfairAccountService.getAccountWallets(user.getBetfairPublicApiToken()));

        return wallets;
    }
}
