package com.rozarltd.module.betfairrestapi.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(BetfairRestApiSpringContext.class)
public class BetfairRestApiStandaloneSpringContext {

    @Bean
    public RestOperations restTemplate() {
        return new RestTemplate();
    }
}
