package com.rozarltd.module.betfairapi;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rozarltd.module.betfairapi.service.AccountService;
import com.rozarltd.module.betfairapi.service.BFExchangeApiService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public AccountService accountService() {
        return moduleBeans.getInstance(AccountService.class);
    }
}
