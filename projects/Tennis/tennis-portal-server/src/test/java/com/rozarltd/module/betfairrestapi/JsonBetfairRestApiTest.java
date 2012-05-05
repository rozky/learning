package com.rozarltd.module.betfairrestapi;

import com.rozarltd.module.betfairrestapi.domain.response.GetMarketResponse;
import com.rozarltd.module.betfairrestapi.spring.BetfairRestApiStandaloneSpringContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes={BetfairRestApiStandaloneConfiguration.class}, loader=AnnotationConfigContextLoader.class)
@ContextConfiguration(classes={BetfairRestApiStandaloneSpringContext.class}, loader=AnnotationConfigContextLoader.class)
public class JsonBetfairRestApiTest {

    @Autowired private BetfairRestApi betfairRestApi;

    @Test
    public void shouldRetrieveMarketInformation() {
        // given
        int marketId = 104432877;

        // when
        GetMarketResponse response = betfairRestApi.getMarket(marketId);

        // then
        assertThat(response.getMarket().getId(), is(String.valueOf(marketId)));
    }
}
