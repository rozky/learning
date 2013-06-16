package com.rozarltd.betting.functional;

import com.googlecode.totallylazy.Callable1;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.domain.market.BetfairRunner;
import com.rozarltd.module.betfairapi.service.BFRestApiFacade;

import java.util.List;

public class AddMarketPrices implements Callable1<BetfairMarket, BetfairMarket> {

    private BFRestApiFacade restApiFacade;

    public AddMarketPrices(BFRestApiFacade restApiFacade) {
        this.restApiFacade = restApiFacade;
    }

    @Override
    public BetfairMarket call(BetfairMarket market) throws Exception {
        BetfairMarket marketWithPrices = restApiFacade.getMarket(market.getMarketId());
        market.setMarketStatus(marketWithPrices.getMarketStatus());

        if(!market.isClosed()) {
            List<BetfairRunner> runners = marketWithPrices.getRunners();
            if(runners != null && runners.size() > 0) {
                market.setRunners(runners);
            }
        }

        return market;
    }

}
