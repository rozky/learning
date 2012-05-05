package com.rozarltd.module.betfairapi.internal.function;

import com.googlecode.functionalcollections.Block;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairwebsite.service.BetfairWebsiteClient;
import org.springframework.beans.factory.annotation.Autowired;

public class MarketPriceLoader implements Block<BetfairMarket> {
    private BetfairWebsiteClient betfairWebsiteService;

    @Autowired
    public MarketPriceLoader(BetfairWebsiteClient betfairWebsiteService) {
        this.betfairWebsiteService = betfairWebsiteService;
    }

    @Override
    public void apply(BetfairMarket market) {
//        WebMarket webMarket = betfairWebsiteService.getMarket(market.getMarketId());
    }
}
