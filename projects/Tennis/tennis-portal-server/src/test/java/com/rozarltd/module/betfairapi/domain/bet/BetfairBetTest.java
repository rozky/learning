package com.rozarltd.module.betfairapi.domain.bet;

import org.junit.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BetfairBetTest {

    @Test
    public void should() {
        NumberFormat format = DecimalFormat.getInstance();
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);
        System.out.println(format.format(234.1));
    }

    @Test
    public void shouldCorrectlyDetermineUnmatchedBetState() {
        // given
        BetfairBet bet = new BetfairBet();
        bet.setBetStatus("OK");
        bet.setMatchedSize(0d);

        // when
        String betState = bet.getBetState();

        // then
        assertThat(betState, is("UNMATCHED"));
    }

    @Test
    public void shouldCorrectlyDetermineMatchedBetState() {
        // given
        BetfairBet bet = new BetfairBet();
        bet.setBetStatus("OK");
        bet.setMatchedSize(10.34);
        bet.setSize(10.34);

        // when
        String betState = bet.getBetState();

        // then
        assertThat(betState, is("MATCHED"));
    }

    @Test
    public void shouldCorrectlyDeterminePartiallyMatchedBetState() {
        // given
        BetfairBet bet = new BetfairBet();
        bet.setBetStatus("OK");
        bet.setMatchedSize(20.43);
        bet.setSize(34.12);

        // when
        String betState = bet.getBetState();

        // then
        assertThat(betState, is("PART_MATCHED"));
    }

    @Test
    public void shouldCorrectlyDetermineFailedBetState() {
        // given
        BetfairBet bet = new BetfairBet();
        bet.setBetStatus("NOT_OK");

        // when
        String betState = bet.getBetState();

        // then
        assertThat(betState, is("FAILED"));
    }
}
