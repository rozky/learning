package com.rozarltd.module.betfairrestapi.spring;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rozarltd.module.betfairrestapi.BetfairRestApi;
import com.rozarltd.module.betfairrestapi.JsonBetfairRestApi;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

import javax.inject.Singleton;

@Configuration
public class BetfairRestApiSpringContext implements InitializingBean {

    @Autowired private RestOperations restTemplate;

    @Bean
    public BetfairRestApi betfairRestApi() {
        return injector.getInstance(BetfairRestApi.class);
    }

    public class Module extends AbstractModule {

        @Override
        protected void configure() {
            bind(BetfairRestApi.class).to(JsonBetfairRestApi.class).in(Singleton.class);
            bind(RestOperations.class).toInstance(restTemplate);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        injector = Guice.createInjector(new Module());
    }

    private Injector injector;
}
