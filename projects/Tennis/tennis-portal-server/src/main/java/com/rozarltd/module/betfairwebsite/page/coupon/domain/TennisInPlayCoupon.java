package com.rozarltd.module.betfairwebsite.page.coupon.domain;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.rozarltd.domain.market.MarketRunner;

import java.util.List;

public class TennisInPlayCoupon {
    private Multimap<Integer, MarketRunner> marketRunners = LinkedListMultimap.create();

    public void addRunner(MarketRunner runner) {
        marketRunners.put(runner.getMarketId(), runner);
    }

    public List<MarketRunner> getMarketRunners(int marketId) {
        return (List<MarketRunner>) marketRunners.get(marketId);
    }

    public boolean containsMarket(int marketId) {
        return marketRunners.containsKey(marketId);
    }
}
