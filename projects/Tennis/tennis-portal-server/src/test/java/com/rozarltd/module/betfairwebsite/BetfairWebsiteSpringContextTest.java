package com.rozarltd.module.betfairwebsite;

import com.rozarltd.module.betfairwebsite.service.BetfairWebsiteClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BetfairWebsiteStandaloneSpringContext.class, loader=AnnotationConfigContextLoader.class)
public class BetfairWebsiteSpringContextTest {
    @Autowired private BetfairWebsiteClient betfairWebsiteClient;

    @Test
    public void shouldWireUp() {
        // given
        // when
        // then
    }
}
