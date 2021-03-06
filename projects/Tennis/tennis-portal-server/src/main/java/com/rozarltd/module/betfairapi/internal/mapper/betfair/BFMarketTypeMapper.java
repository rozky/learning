package com.rozarltd.module.betfairapi.internal.mapper.betfair;

import com.betfair.publicapi.exchange.types.ArrayOfRunner;
import com.betfair.publicapi.exchange.types.Market;
import com.betfair.publicapi.exchange.types.Runner;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.domain.market.BetfairRunner;
import com.rozarltd.module.betfairapi.internal.mapper.ObjectTypeMapperSupport;

import java.util.ArrayList;
import java.util.List;

public class BFMarketTypeMapper extends ObjectTypeMapperSupport<Market, BetfairMarket> {

    @Override
    public BetfairMarket mapFrom(Market betfairApiType) {
        BetfairMarket resultMarket = new BetfairMarket();

        // map runners
        ArrayOfRunner runnersArray = betfairApiType.getRunners();
        if(runnersArray != null) {
            List<BetfairRunner> runners = new ArrayList<BetfairRunner>();
            List<Runner> bfApiRunners = runnersArray.getRunner();
            for(Runner bfApiRunner: bfApiRunners) {
                runners.add(new BetfairRunner(bfApiRunner.getSelectionId(), bfApiRunner.getName()));
            }

            resultMarket.setRunners(runners);
        }

        // todo - map the rest of the object
        return resultMarket;
    }
}
