package com.rozarltd.betting.service;

import com.rozarltd.betfairapi.domain.bet.BetfairBet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class BetPlacementResultTest {

    @Test
    public void shouldSetStatusToErrorWhenRuleViolationsAreReported() {
        // given
        List<RuleViolation> violations = new ArrayList<RuleViolation>();

        // when
        BetPlacementResult betPlacementResult = new BetPlacementResult(violations);

        // then
        assertThat(betPlacementResult.isError(), is(true));
        assertThat(betPlacementResult.getStatus(), is("BETTING_RULE_VIOLATION"));
        assertThat(betPlacementResult.getBettingRuleViolations(), is(violations));
        assertThat(betPlacementResult.getBet(), nullValue());
    }

    @Test
    public void shouldSetStatusToErrorWhenStatusIsNotOK() {
        // given
        String status = "API_ERROR";

        // when
        BetPlacementResult betPlacementResult = new BetPlacementResult(status);

        // then
        assertThat(betPlacementResult.isError(), is(true));
        assertThat(betPlacementResult.getStatus(), is(status));
        assertThat(betPlacementResult.getBet(), nullValue());
        assertThat(betPlacementResult.getBettingRuleViolations(), nullValue());
    }

    @Test
    public void shouldSetStatusToOKWhenCreatedForBetfairBet() {
        // given
        BetfairBet bet = new BetfairBet();

        // when
        BetPlacementResult betPlacementResult = new BetPlacementResult(bet);

        // then
        assertThat(betPlacementResult.isError(), is(false));
        assertThat(betPlacementResult.getStatus(), is("OK"));
        assertThat(betPlacementResult.getBet(), is(bet));
        assertThat(betPlacementResult.getBettingRuleViolations(), nullValue());
    }
}
