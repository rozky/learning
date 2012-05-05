package com.rozarltd.module.betfairwebsite;

import com.google.inject.AbstractModule;
import com.rozarltd.module.betfairwebsite.service.BetfairWebsiteClient;
import com.rozarltd.util.http.HttpTemplate;

import javax.inject.Singleton;

public class BetfairWebsiteGuiceModule extends AbstractModule {
    private HttpTemplate httpTemplate;

    public void setHttpTemplate(HttpTemplate httpTemplate) {
        this.httpTemplate = httpTemplate;
    }

    @Override
    protected void configure() {
        // injected external dependencies
        bind(HttpTemplate.class).toInstance(httpTemplate);

        // exposed beans
        bind(BetfairWebsiteClient.class).in(Singleton.class);

        // internal beans

        // web service clients
    }
}