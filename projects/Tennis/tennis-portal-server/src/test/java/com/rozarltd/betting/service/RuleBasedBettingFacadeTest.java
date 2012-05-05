package com.rozarltd.betting.service;

import com.rozarltd.module.betfairapi.internal.mapper.betfair.BFTypeMapperManager;
import com.rozarltd.module.betfairrestapi.BetfairRestApi;
import com.rozarltd.betting.domain.BetRequest;
import com.rozarltd.account.User;
import com.rozarltd.betting.rules.BetSizeLimitRule;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class RuleBasedBettingFacadeTest {
    private static User DEFAULT_USER = new User("username", "betfairSoapApiToken", "betfairRestApiToken");
    private BettingFacade bettingFacade;
    private BFTypeMapperManager typeMapperManager;
    @Mock private BetfairRestApi betfairRestApiService;

    @Before
    public void beforeEach() {
        initMocks(this);
        typeMapperManager = new BFTypeMapperManager();
        bettingFacade = new RuleBasedBettingFacade(betfairRestApiService, typeMapperManager);
    }

    @Test
    public void shouldNotPlaceWhenBetSizeExceedAllowedBetSize() {
        // given
        BetRequest betWithToHeightStake = createBet(BetSizeLimitRule.MAX_BET_SIZE + 1);

        // when
        BetPlacementResult result = bettingFacade.placeABet(DEFAULT_USER, betWithToHeightStake);

        // then
        assertThat(result.isError(), is(true));
    }

    private BetRequest createBet(double betSize) {
        return new BetRequest(1,1,1,betSize);
    }

    private User createUser() {
        return new User("username", "betfairSoapApiToken", "betfairRestApiToken");
    }
}
