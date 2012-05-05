package com.rozarltd.betting.domain;

import com.rozarltd.module.betfairapi.domain.account.Wallet;
import com.rozarltd.module.betfairapi.domain.bet.BetfairBet;

import java.util.List;

public class UserBettingData {
    private List<Wallet> wallets;
    private List<BetfairBet> currentBets;

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }

    public List<BetfairBet> getCurrentBets() {
        return currentBets;
    }

    public void setCurrentBets(List<BetfairBet> currentBets) {
        this.currentBets = currentBets;
    }
}
