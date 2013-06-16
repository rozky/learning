package com.rozarltd.betting;

import com.google.inject.AbstractModule;
import com.rozarltd.betting.functional.BetfairMarketFunctions;
import com.rozarltd.betting.service.BettingFacade;
import com.rozarltd.betting.service.MarketReplicationService;
import com.rozarltd.betting.service.MarketService;
import com.rozarltd.betting.service.RuleBasedBettingFacade;
import com.rozarltd.betting.service.TennisMarketReplicationService;
import com.rozarltd.betting.service.TennisMarketService;
import com.rozarltd.module.betfairapi.internal.mapper.ObjectTypeMapperManager;
import com.rozarltd.module.betfairapi.internal.mapper.betfair.BFTypeMapperManager;
import com.rozarltd.module.betfairapi.service.BFExchangeApiService;
import com.rozarltd.module.betfairapi.service.BFRestApiFacade;
import com.rozarltd.module.betfairapi.service.BetfairRestApiFacade;
import com.rozarltd.module.betfairrestapi.BetfairRestApi;
import com.rozarltd.repository.BetfairMarketRepository;
import org.springframework.core.convert.ConversionService;

import javax.inject.Singleton;

class BettingGuiceModule extends AbstractModule {

    private BFExchangeApiService bfExchangeApiService;
    private ConversionService conversionService;
    private BetfairRestApi betfairRestApi;
    private BetfairMarketRepository betfairMarketRepository;

    public void setBfExchangeApiService(BFExchangeApiService bfExchangeApiService) {
        this.bfExchangeApiService = bfExchangeApiService;
    }

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public void setBetfairRestApi(BetfairRestApi betfairRestApi) {
        this.betfairRestApi = betfairRestApi;
    }

    public void setBetfairMarketRepository(BetfairMarketRepository betfairMarketRepository) {
        this.betfairMarketRepository = betfairMarketRepository;
    }

    @Override
    protected void configure() {
        // injected external dependencies
        bind(BFExchangeApiService.class).toInstance(bfExchangeApiService);
        bind(ConversionService.class).toInstance(conversionService);
        bind(BetfairRestApi.class).toInstance(betfairRestApi);
        bind(BetfairMarketRepository.class).toInstance(betfairMarketRepository);

        // exposed beans
        bind(MarketService.class).to(TennisMarketService.class).in(Singleton.class);
        bind(MarketReplicationService.class).to(TennisMarketReplicationService.class).in(Singleton.class);
        bind(BettingFacade.class).to(RuleBasedBettingFacade.class).in(Singleton.class);

        // internal beans
        bind(BFRestApiFacade.class).to(BetfairRestApiFacade.class).in(Singleton.class);
//        bind(MarketRefresher.class).in(Singleton.class);
        bind(BetfairMarketFunctions.class).in(Singleton.class);

        // todo - remove as this one is from different module , I need to find out why I need it
        bind(ObjectTypeMapperManager.class).to(BFTypeMapperManager.class).in(Singleton.class);
    }
}
