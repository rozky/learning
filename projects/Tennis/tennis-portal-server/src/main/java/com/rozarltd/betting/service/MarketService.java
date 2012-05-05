package com.rozarltd.betting.service;

import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.domain.market.Market;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MarketService {
    public List<BetfairMarket> getInPlayMarkets(int eventId);
    public Map<String, List<BetfairMarket>> getMarkets(int eventId);
    public Set<Market> getMatchOddMarkets();
    public Set<BetfairMarket> getTodayMarkets();

    // todo - remove this one
    public Map<Integer, String> getRunnerNames();
}
