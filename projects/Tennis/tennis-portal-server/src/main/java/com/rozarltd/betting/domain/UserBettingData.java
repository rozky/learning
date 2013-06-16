package com.rozarltd.betting.domain;

import com.rozarltd.module.betfairapi.domain.account.BetfairWallet;
import com.rozarltd.module.betfairapi.domain.bet.BetfairBet;

import java.util.List;

public class UserBettingData {
    private List<BetfairWallet> wallets;
    private List<BetfairBet> currentBets;

    public List<BetfairWallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<BetfairWallet> wallets) {
        this.wallets = wallets;
    }

    public List<BetfairBet> getCurrentBets() {
        return currentBets;
    }

    public void setCurrentBets(List<BetfairBet> currentBets) {
        this.currentBets = currentBets;
    }
}
