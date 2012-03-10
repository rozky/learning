package com.rozarltd.betting.portal.service;

import com.rozarltd.betfairapi.domain.BetfairEventId;
import com.rozarltd.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.betfairapi.service.BetfairExchangeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TennisMarketService {

    private BetfairExchangeApiService marketService;

    @Autowired
    public TennisMarketService(BetfairExchangeApiService marketService) {
        this.marketService = marketService;
    }

    // todo convert to SportMarket
    public List<BetfairMarket> getInPlayMarkets() {
        List<BetfairMarket> markets = marketService.getMarkets(BetfairEventId.tennis.getId());

        return markets;
    }
}
