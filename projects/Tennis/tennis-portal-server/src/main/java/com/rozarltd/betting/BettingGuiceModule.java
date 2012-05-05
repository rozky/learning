package com.rozarltd.betting;

import com.google.inject.AbstractModule;
import com.rozarltd.module.betfairapi.internal.function.MarketRefresher;
import com.rozarltd.module.betfairapi.service.BFExchangeApiService;
import com.rozarltd.module.betfairapi.service.BFRestApiFacade;
import com.rozarltd.module.betfairapi.service.BetfairRestApiFacade;
import com.rozarltd.module.betfairrestapi.BetfairRestApi;
import com.rozarltd.betting.service.MarketService;
import com.rozarltd.betting.service.TennisMarketService;
import org.springframework.core.convert.ConversionService;

import javax.inject.Singleton;

class BettingGuiceModule extends AbstractModule {

    private BFExchangeApiService bfExchangeApiService;
    private ConversionService conversionService;
    private BetfairRestApi betfairRestApi;

    public void setBfExchangeApiService(BFExchangeApiService bfExchangeApiService) {
        this.bfExchangeApiService = bfExchangeApiService;
    }

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public void setBetfairRestApi(BetfairRestApi betfairRestApi) {
        this.betfairRestApi = betfairRestApi;
    }

    @Override
    protected void configure() {
        // injected external dependencies
        bind(BFExchangeApiService.class).toInstance(bfExchangeApiService);
        bind(ConversionService.class).toInstance(conversionService);
        bind(BetfairRestApi.class).toInstance(betfairRestApi);

        // exposed beans
        bind(MarketService.class).to(TennisMarketService.class).in(Singleton.class);

        // internal beans
        bind(BFRestApiFacade.class).to(BetfairRestApiFacade.class).in(Singleton.class);
        bind(MarketRefresher.class).in(Singleton.class);
    }
}
