package com.rozarltd.module.betfairrestapi.spring;

import org.springframework.web.client.RestOperations;

public interface BetfairRestApiDependency {
    RestOperations restTemplate();
}
