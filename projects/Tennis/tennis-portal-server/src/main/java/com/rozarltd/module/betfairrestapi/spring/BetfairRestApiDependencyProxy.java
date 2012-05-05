package com.rozarltd.module.betfairrestapi.spring;

import org.springframework.web.client.RestOperations;

public class BetfairRestApiDependencyProxy implements BetfairRestApiDependency {

    @Override
    public RestOperations restTemplate() {
        return null;
    }
}
