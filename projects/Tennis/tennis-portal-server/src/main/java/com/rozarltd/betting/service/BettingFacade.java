package com.rozarltd.betting.service;

import com.rozarltd.account.BetfairUser;
import com.rozarltd.betting.common.domain.BetParams;

public interface BettingFacade {
    public BetPlacementResult placeBet(BetfairUser user, BetParams bet);
}
