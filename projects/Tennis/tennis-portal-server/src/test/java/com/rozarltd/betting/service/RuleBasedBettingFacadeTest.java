package com.rozarltd.betting.service;

import com.rozarltd.betfairapi.internal.mapper.betfair.BFTypeMapperManager;
import com.rozarltd.betfairapi.service.BFRestApiService;
import com.rozarltd.betting.domain.BetLittle;
import com.rozarltd.domain.account.User;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class RuleBasedBettingFacadeTest {
    private static User DEFAULT_USER = new User("username", "betfairSoapApiToken", "betfairRestApiToken");
    private BettingFacade bettingFacade;
    private BFTypeMapperManager typeMapperManager;
    @Mock private BFRestApiService betfairRestApiService;

    public void beforeEach() {
        initMocks(this);
        typeMapperManager = new BFTypeMapperManager();
        bettingFacade = new RuleBasedBettingFacade(betfairRestApiService, typeMapperManager);
    }

    @Test
    public void shouldNotPlaceWhenBetSizeExceedAllowedBetSize() {
        // given
        BetLittle betWithToHeightStake = createBet(2001);

        // when
        BetPlacementResult result = bettingFacade.placeABet(DEFAULT_USER, betWithToHeightStake);

        // then
        assertThat(result.isError(), is(true));
    }

    private BetLittle createBet(double betSize) {
        return new BetLittle(1,1,1,betSize);
    }

    private User createUser() {
        return new User("username", "betfairSoapApiToken", "betfairRestApiToken");
    }
}
