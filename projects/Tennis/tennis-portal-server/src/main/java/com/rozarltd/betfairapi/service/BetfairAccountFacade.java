package com.rozarltd.betfairapi.service;

import com.rozarltd.betfairapi.domain.account.Wallet;
import com.rozarltd.domain.account.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BetfairAccountFacade implements AccountFacade {
    private BetfairAccountService betfairAccountService;

    @Autowired
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
