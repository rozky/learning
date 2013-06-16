package com.rozarltd.module.betfairapi;

import com.rozarltd.module.betfairapi.service.BetfairAccountApi;
import com.rozarltd.module.betfairapi.service.BFExchangeApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BetfairPublicApiSpringContext.class, loader=AnnotationConfigContextLoader.class)
public class BetfairPublicApiSpringContextTest {
    @Autowired private BetfairAccountApi accountService;
    @Autowired private BFExchangeApiService bfExchangeApiService;
    
    @Test
    public void shouldWireUp() {
        // given
        // when
        // then
    }
}
