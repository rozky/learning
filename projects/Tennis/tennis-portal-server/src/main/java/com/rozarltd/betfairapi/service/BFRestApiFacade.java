package com.rozarltd.betfairapi.service;

import com.rozarltd.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.betfairapi.domain.market.BetfairRunner;

import java.util.List;

public interface BFRestApiFacade {
    public BetfairMarket getMarket(int marketId);
    public List<BetfairRunner> getMarketRunners(int marketId);
    public List<BetfairRunner> getMarketPrices(int marketId);
}
