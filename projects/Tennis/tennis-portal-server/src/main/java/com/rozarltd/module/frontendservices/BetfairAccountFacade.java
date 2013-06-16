package com.rozarltd.module.frontendservices;

import com.google.inject.Inject;
import com.rozarltd.account.BetfairUser;
import com.rozarltd.module.betfairapi.domain.account.BetfairWallet;
import com.rozarltd.module.betfairapi.service.DefaultBetfairAccountApi;

import java.util.ArrayList;
import java.util.List;

public class BetfairAccountFacade implements AccountFacade {
    private DefaultBetfairAccountApi betfairAccountService;

    @Inject
    public BetfairAccountFacade(DefaultBetfairAccountApi betfairAccountService) {
        this.betfairAccountService = betfairAccountService;
    }

    @Override
    public List<BetfairWallet> getWallets(BetfairUser user) {
        List<BetfairWallet> wallets = new ArrayList<BetfairWallet>();
        wallets.add(betfairAccountService.getAccountWallets(user.getPublicApiToken()));

        return wallets;
    }
}
