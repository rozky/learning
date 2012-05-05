package com.rozarltd.module.betfairapi.service;

import com.google.inject.Inject;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.domain.market.BetfairRunner;
import com.rozarltd.module.betfairapi.domain.market.BetfairRunnerPrice;
import com.rozarltd.module.betfairrestapi.BetfairRestApi;
import com.rozarltd.module.betfairrestapi.domain.market.BetfairRestApiMarket;
import com.rozarltd.module.betfairrestapi.domain.market.BetfairRestApiMarketRunner;
import com.rozarltd.module.betfairrestapi.domain.market.BetfairRestApiMarketRunnerPrice;
import com.rozarltd.module.betfairrestapi.domain.response.GetMarketResponse;
import com.rozarltd.util.java.lang.DoubleUtils;
import com.rozarltd.util.java.lang.IntegerUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BetfairRestApiFacade implements BFRestApiFacade {
    private BetfairRestApi restApiService;

    @Autowired
    @Inject
    public BetfairRestApiFacade(BetfairRestApi restApiService) {
        this.restApiService = restApiService;
    }

    @Override
    public BetfairMarket getMarket(int marketId) {
        BetfairMarket market = new BetfairMarket();



        BetfairRestApiMarket apiMarket = restApiService.getMarket(marketId).getMarket();
        if(apiMarket != null) {
            market.setMarketId(NumberUtils.toInt(apiMarket.getId()));
            market.setMarketStatus(apiMarket.getMarketStatus());
            market.setRunners(new ArrayList<BetfairRunner>());

            // build runners
            List<BetfairRestApiMarketRunner> runners = apiMarket.getRunners().getRunner();
            if(runners != null) {
                List<BetfairRunner> marketRunners = market.getRunners();
                for(BetfairRestApiMarketRunner marketRunner: runners) {
                    BetfairRunner runner = new BetfairRunner(IntegerUtils.valueOf(marketRunner.getId()), marketRunner.getName());

                    BetfairRestApiMarketRunnerPrice runnerPrice = marketRunner.getPrice();
                    if(runnerPrice != null) {
                        runner.setBestBackPrice(buildRunnerBackPrice(runnerPrice));
                        runner.setBestLayPrice(buildRunnerLayPrice(runnerPrice));
                    }

                    marketRunners.add(runner);
                }
            }
        }

        return market;
    }

    @Override
    public List<BetfairRunner> getMarketRunners(int marketId) {
        List<BetfairRunner> marketRunners = new ArrayList<BetfairRunner>();

        GetMarketResponse marketResp = restApiService.getMarket(marketId);

        List<BetfairRestApiMarketRunner> runners = marketResp.getMarket().getRunners().getRunner();

        if(runners != null) {
            for(BetfairRestApiMarketRunner marketRunner: runners) {
                BetfairRunner runner = new BetfairRunner(IntegerUtils.valueOf(marketRunner.getId()), marketRunner.getName());


                BetfairRestApiMarketRunnerPrice runnerPrice = marketRunner.getPrice();
                if(runnerPrice != null) {
                    runner.setBestBackPrice(buildRunnerBackPrice(runnerPrice));
                    runner.setBestLayPrice(buildRunnerLayPrice(runnerPrice));
                }



                marketRunners.add(runner);
            }
        }

        return marketRunners;
    }

    private BetfairRunnerPrice buildRunnerBackPrice(BetfairRestApiMarketRunnerPrice price) {
        BetfairRunnerPrice runnerPrice = new BetfairRunnerPrice();
        runnerPrice.setPrice(DoubleUtils.valueOf(price.getBestBackPrice()));
        runnerPrice.setTotalAvailableToBack(DoubleUtils.valueOf(price.getBestBackAmount()));
        return runnerPrice;
    }

    private BetfairRunnerPrice buildRunnerLayPrice(BetfairRestApiMarketRunnerPrice price) {
        BetfairRunnerPrice runnerPrice = new BetfairRunnerPrice();
        runnerPrice.setPrice(DoubleUtils.valueOf(price.getBestLayPrice()));
        runnerPrice.setTotalAvailableToLay(DoubleUtils.valueOf(price.getBestLayAmount()));
        return runnerPrice;
    }
}
