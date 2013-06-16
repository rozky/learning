package com.rozarltd.module.betfairapi;

import com.betfair.publicapi.global.BFGlobalService;
import com.betfair.publicapi.global.types.LoginErrorEnum;
import com.betfair.publicapi.global.types.LoginReq;
import com.betfair.publicapi.global.types.LoginResp;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rozarltd.module.betfairapi.internal.BetfairSessionToken;
import com.rozarltd.module.betfairapi.internal.factory.GlobalAPIRequestFactory;
import com.rozarltd.module.betfairapi.internal.mapper.ObjectTypeMapperManager;
import com.rozarltd.module.betfairapi.internal.mapper.betfair.BFTypeMapperManager;
import com.rozarltd.module.betfairapi.service.BetfairAccountApi;
import com.rozarltd.module.betfairapi.service.BFExchangeApiService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

// todo - missing caching and some other service from com.rozarltd.module.betfairapi.service
@Configuration
public class BetfairPublicApiSpringContext implements InitializingBean {
    private Injector moduleBeans;

    @Override
    public void afterPropertiesSet() throws Exception {
        moduleBeans = Guice.createInjector(new BetfairPublicApiGuiceModule());
    }

    @Bean
    public BFExchangeApiService bfExchangeApiService() {
        return moduleBeans.getInstance(BFExchangeApiService.class);
    }

    @Bean
    public BetfairAccountApi accountService() {
        return moduleBeans.getInstance(BetfairAccountApi.class);
    }

//    @Bean
//    public AccountFacade accountFacade() {
//        return moduleBeans.getInstance(BetfairAccountFacade.class);
//    }

    // TODO - I think this one should be internal or provided by root container
    @Bean
    public ObjectTypeMapperManager BFTypeMapperManager() {
        return new BFTypeMapperManager();
    }


    // TODO - remove this token from here and move it to guice

    private static final String username = "undefined";
    private static final String password = "undefined";

    @Bean
    @Lazy
    public BetfairSessionToken betfairApiSessionToken(BFGlobalService betfairGlobalApiService,
                                               GlobalAPIRequestFactory betfairGlobalApiRequestFactory) {

        LoginReq request = betfairGlobalApiRequestFactory.createLoginRequest(username, password);
        LoginResp response = betfairGlobalApiService.login(request);

        if(LoginErrorEnum.OK == response.getErrorCode()) {
            return new BetfairSessionToken(response.getHeader().getSessionToken());
        }

        throw new BetfairApiRuntimeException(String.format("Login to Betfair API failed with error '%s'.",
                response.getErrorCode()));
    }
}
