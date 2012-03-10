package com.rozarltd.betfairapi.service;

import com.rozarltd.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.betfairapi.domain.market.BetfairMarketNameEnum;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MarketFacade {
    public List<BetfairMarket> getInPlayMarkets(int eventId);
    public List<BetfairMarket> getInPlayMarkets(int eventId, BetfairMarketNameEnum marketName);
    public Map<String, List<BetfairMarket>> getMarkets(int eventId);
    public List<BetfairMarket> getMarkets(int eventId, String marketName);
    public Set<BetfairMarket> getMatchOddMarkets(int rootEventId);
    public Map<Integer, String> getRunnerNames();
}
