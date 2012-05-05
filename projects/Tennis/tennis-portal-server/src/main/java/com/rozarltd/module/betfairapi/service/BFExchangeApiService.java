package com.rozarltd.module.betfairapi.service;

import com.rozarltd.module.betfairapi.domain.bet.BetfairBet;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarketPrices;

import java.util.List;

public interface BFExchangeApiService {
    public BetfairMarket getMarket(int marketId);
    public List<BetfairMarket> getMarkets(int eventId);
    public BetfairMarketPrices getMarketPrices(String marketId);
    public List<BetfairBet> getCurrentBets(String sessionToken);
    public String cancelBet(String sessionToken, long betId);
}
