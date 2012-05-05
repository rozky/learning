package com.rozarltd.module.betfairwebsite;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rozarltd.module.betfairwebsite.service.BetfairWebsiteClient;
import com.rozarltd.util.http.HttpTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BetfairWebsiteSpringContext implements InitializingBean {

    @Autowired private HttpTemplate httpTemplate;

    @Bean
    public BetfairWebsiteClient betfairWebsiteClient() {
        return moduleBeans.getInstance(BetfairWebsiteClient.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        BetfairWebsiteGuiceModule guiceModule = new BetfairWebsiteGuiceModule();
        guiceModule.setHttpTemplate(httpTemplate);

        moduleBeans = Guice.createInjector(guiceModule);
    }

    private Injector moduleBeans;
}
