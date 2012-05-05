package com.rozarltd.module.betfairapi.internal.function;

import com.google.inject.Inject;
import com.googlecode.functionalcollections.Block;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.domain.market.BetfairRunner;
import com.rozarltd.module.betfairapi.service.BFRestApiFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarketRefresher implements Block<BetfairMarket> {
    private BFRestApiFacade restApiFacade;

    @Autowired
    @Inject
    public MarketRefresher(BFRestApiFacade restApiFacade) {
        this.restApiFacade = restApiFacade;
    }

    @Override
    public void apply(BetfairMarket market) {
        BetfairMarket currentMarket = restApiFacade.getMarket(market.getMarketId());
        market.setMarketStatus(currentMarket.getMarketStatus());

        if(!market.isClosed()) {
            List<BetfairRunner> runners = currentMarket.getRunners();
            if(runners != null && runners.size() > 0) {
                market.setRunners(runners);
            }
        }
    }
}
