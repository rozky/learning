package com.rozarltd.betfairrestapi.domain.response;

import com.rozarltd.betfairrestapi.domain.bet.BFRestBet;

public class PlaceBetResponse {
    private BFRestBet bet;

    public boolean isError() {
        return bet == null || !"OK".equals(bet.getBetStatus());
    }

    public String error() {
        return isError() && bet != null ? bet.getErrorMessage() : null;
    }

    public BFRestBet getBet() {
        return bet;
    }

    public void setBet(BFRestBet bet) {
        this.bet = bet;
    }
}
