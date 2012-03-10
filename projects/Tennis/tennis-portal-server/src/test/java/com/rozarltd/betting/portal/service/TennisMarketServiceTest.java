package com.rozarltd.betting.portal.service;

import com.rozarltd.betfairapi.service.BetfairExchangeApiService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

public class TennisMarketServiceTest {
    @Mock private BetfairExchangeApiService betfairMarketService;

    private TennisMarketService service;

    @Before
    public void beforeEachTest() {
        initMocks(this);

        service = new TennisMarketService(betfairMarketService);
    }
    
    @Test
    public void shouldParseDataReturnedFromBetfairAPICorrectly() {
        // given
        // when
        // then
    }
    
    @Test
    public void shouldNotFailWhenBetfairServiceResponseWithErrorResponse() {
        // given
        // when
        // then
    }

    @Test
    public void shouldGetOnlyTennisMarkets() {
        // given

        // when

        // then
    }
}
