package com.rozarltd.module.betfairrestapi.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BetfairRestApiEmbeddedConfiguration extends BetfairRestApiSpringContext {

    @Autowired private ApplicationContext container;

//    @Override
    public final RestOperations restTemplate() {
        return container.getBean(RestTemplate.class);
    }
}
