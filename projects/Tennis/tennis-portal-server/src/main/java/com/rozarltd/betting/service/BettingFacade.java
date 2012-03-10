package com.rozarltd.betting.service;

import com.rozarltd.betting.domain.BetLittle;
import com.rozarltd.domain.account.User;

public interface BettingFacade {
    public BetPlacementResult placeABet(User user, BetLittle bet);
}
