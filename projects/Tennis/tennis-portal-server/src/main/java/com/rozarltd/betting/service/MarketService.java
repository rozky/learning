package com.rozarltd.betting.service;

import com.rozarltd.domain.market.MarketAdapter;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;

import java.util.List;
import java.util.Map;

public interface MarketService {

    public List<MarketAdapter> getMatchOddMarkets();
    public List<BetfairMarket> getTodayMarkets();

    // todo - remove this one
    public Map<Integer, String> getRunnerNames();



    // main methods
    public Map<String, List<BetfairMarket>> getMarkets(int eventId);
    public List<BetfairMarket> getLiveMarkets(int eventId);
}
