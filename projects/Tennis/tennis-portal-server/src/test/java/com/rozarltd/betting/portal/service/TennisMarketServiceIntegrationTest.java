package com.rozarltd.betting.portal.service;

import com.rozarltd.betfairapi.domain.market.BetfairMarket;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/external/server-context.xml")
@Ignore
public class TennisMarketServiceIntegrationTest {
    @Autowired private TennisMarketService service;

    @Test
    public void shouldReturnAtLeastOneMarket() {
        // given
        // when
        List<BetfairMarket> markets = service.getInPlayMarkets();

        // then
        assertThat(markets.size(), greaterThan(0));
    }
}
