package com.rozarltd.module.betfairapi.service;

import com.rozarltd.module.betfairapi.domain.account.Wallet;
import com.rozarltd.account.User;

import java.util.List;

public interface AccountFacade {
    public List<Wallet> getWallets(User user);
}
