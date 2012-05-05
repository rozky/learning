package com.rozarltd.betting;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rozarltd.module.betfairapi.service.BFExchangeApiService;
import com.rozarltd.module.betfairrestapi.BetfairRestApi;
import com.rozarltd.betting.service.MarketService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class BettingSpringContext implements InitializingBean {

    // Module dependencies
    @Autowired private BFExchangeApiService bfExchangeApiService;
    @Autowired private ConversionService conversionService;
    @Autowired private BetfairRestApi betfairRestApi;

    // Exposed beans by the module
    @Bean
    public MarketService marketService() {
        return moduleBeans.getInstance(MarketService.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        BettingGuiceModule module = new BettingGuiceModule();
        module.setConversionService(conversionService);
        module.setBfExchangeApiService(bfExchangeApiService);
        module.setBetfairRestApi(betfairRestApi);

        moduleBeans = Guice.createInjector(module);
    }

    private Injector moduleBeans;
}
