package com.rozarltd.module.betfairapi.service;

import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.domain.market.BetfairRunner;

import java.util.List;

public interface BFRestApiFacade {
    public BetfairMarket getMarket(int marketId);
    public List<BetfairRunner> getMarketRunners(int marketId);
}
