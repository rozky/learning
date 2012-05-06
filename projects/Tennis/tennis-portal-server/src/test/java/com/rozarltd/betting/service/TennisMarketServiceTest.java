package com.rozarltd.betting.service;

import com.rozarltd.betting.BettingStandaloneSpringContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BettingStandaloneSpringContext.class, loader=AnnotationConfigContextLoader.class)
public class TennisMarketServiceTest {

    @Autowired
    private MarketService tennisMarketService;


    @Test
    public void shouldWireUp() {
        // given
        // when
//        Set<BetfairMarket> todayMarkets = tennisMarketService.getTodayMarkets();

        // then
//        assertThat(todayMarkets.size(), greaterThan(0));
    }


}
