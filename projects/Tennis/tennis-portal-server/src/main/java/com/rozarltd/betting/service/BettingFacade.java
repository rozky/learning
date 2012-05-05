package com.rozarltd.betting.service;

import com.rozarltd.betting.domain.BetRequest;
import com.rozarltd.account.User;

public interface BettingFacade {
    public BetPlacementResult placeABet(User user, BetRequest bet);
}
