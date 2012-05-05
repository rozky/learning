package com.rozarltd.module.betfairapi;

import com.betfair.publicapi.exchange.BFExchangeService;
import com.betfair.publicapi.global.BFGlobalService;
import com.google.inject.AbstractModule;
import com.rozarltd.module.betfairapi.internal.factory.BFExchangeAPIRequestFactory;
import com.rozarltd.module.betfairapi.internal.factory.GlobalAPIRequestFactory;
import com.rozarltd.module.betfairapi.internal.mapper.ObjectTypeMapperManager;
import com.rozarltd.module.betfairapi.internal.mapper.betfair.BFTypeMapperManager;
import com.rozarltd.module.betfairapi.internal.parser.GetAllMarketResponseDataParser;
import com.rozarltd.module.betfairapi.service.AccountService;
import com.rozarltd.module.betfairapi.service.BFExchangeApiService;
import com.rozarltd.module.betfairapi.service.BetfairAccountService;
import com.rozarltd.module.betfairapi.service.BetfairExchangeApiService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import javax.inject.Singleton;

class BetfairPublicApiGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        // injected external dependencies

        // exposed beans
        bind(AccountService.class).to(BetfairAccountService.class).in(Singleton.class);
        bind(BFExchangeApiService.class).to(BetfairExchangeApiService.class).in(Singleton.class);

        // internal beans
        bind(GlobalAPIRequestFactory.class).in(Singleton.class);
        bind(BFExchangeAPIRequestFactory.class).in(Singleton.class);
        bind(GetAllMarketResponseDataParser.class).in(Singleton.class);
        bind(ObjectTypeMapperManager.class).to(BFTypeMapperManager.class).in(Singleton.class);

        // web service clients
        bind(BFGlobalService.class).toInstance(bfGlobalService());
        bind(BFExchangeService.class).toInstance(bfExchangeService());
    }

    private BFGlobalService bfGlobalService() {
        JaxWsProxyFactoryBean wsClientFactory = new JaxWsProxyFactoryBean();
        wsClientFactory.setServiceClass(BFGlobalService.class);
        wsClientFactory.setAddress("https://api.betfair.com/global/v3/BFGlobalService");

        return (BFGlobalService) wsClientFactory.create();
    }

    private BFExchangeService bfExchangeService() {
        JaxWsProxyFactoryBean wsClientFactory = new JaxWsProxyFactoryBean();
        wsClientFactory.setServiceClass(BFExchangeService.class);
        wsClientFactory.setAddress("https://api.betfair.com/exchange/v5/BFExchangeService");

        return (BFExchangeService) wsClientFactory.create();
    }
}
