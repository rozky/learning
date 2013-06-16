package com.rozarltd.module.frontendservices;

import com.rozarltd.account.BetfairUser;
import com.rozarltd.module.betfairapi.domain.account.BetfairWallet;

import java.util.List;

public interface AccountFacade {
    public List<BetfairWallet> getWallets(BetfairUser user);
}
