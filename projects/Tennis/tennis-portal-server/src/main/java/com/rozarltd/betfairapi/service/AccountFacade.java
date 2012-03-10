package com.rozarltd.betfairapi.service;

import com.rozarltd.betfairapi.domain.account.Wallet;
import com.rozarltd.domain.account.User;

import java.util.List;

public interface AccountFacade {
    public List<Wallet> getWallets(User user);
}
