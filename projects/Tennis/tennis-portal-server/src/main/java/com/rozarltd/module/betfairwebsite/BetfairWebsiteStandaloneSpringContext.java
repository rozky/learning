package com.rozarltd.module.betfairwebsite;

import com.rozarltd.util.http.HttpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BetfairWebsiteSpringContext.class)
public class BetfairWebsiteStandaloneSpringContext {

    @Bean
    public HttpTemplate httpTemplate() {
        return new HttpTemplate();
    }
}
