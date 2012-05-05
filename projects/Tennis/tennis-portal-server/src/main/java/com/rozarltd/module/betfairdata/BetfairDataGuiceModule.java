package com.rozarltd.module.betfairdata;

import com.google.inject.AbstractModule;
import com.rozarltd.module.betfairdata.repository.MarketDataRepository;
import com.rozarltd.module.betfairdata.service.BetfairDataReader;
import com.rozarltd.module.betfairdata.strategy.StartingOddsStrategyExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Singleton;

public class BetfairDataGuiceModule extends AbstractModule {
    @Autowired private MarketDataRepository marketDataRepository;

    public void setMarketDataRepository(MarketDataRepository marketDataRepository) {
        this.marketDataRepository = marketDataRepository;
    }

    @Override
    protected void configure() {
        // injected external dependencies
        bind(MarketDataRepository.class).toInstance(marketDataRepository);

        // exposed beans
        bind(BetfairDataReader.class).in(Singleton.class);

        // internal beans
        bind(BetfairDataReader.class).in(Singleton.class);
        bind(StartingOddsStrategyExecutor.class).in(Singleton.class);
    }
}