package com.rozarltd.module.betfairdata;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rozarltd.module.betfairdata.repository.MarketDataRepository;
import com.rozarltd.module.betfairdata.service.BetfairDataService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BetfairDataSpringContext implements InitializingBean {

    @Autowired private MarketDataRepository marketDataRepository;

    @Bean
    public BetfairDataService betfairDataService() {
        return moduleBeans.getInstance(BetfairDataService.class);
    }

    // dependency or internal

    @Override
    public void afterPropertiesSet() throws Exception {
        BetfairDataGuiceModule guiceModule = new BetfairDataGuiceModule();
        guiceModule.setMarketDataRepository(marketDataRepository);

        moduleBeans = Guice.createInjector(guiceModule);
    }

    private Injector moduleBeans;
}
