package com.rozarltd.module.betfairapi.domain.market;

import com.rozarltd.module.betfairapi.stub.domain.BetfairMarkets;
import com.rozarltd.module.betfairapi.stub.domain.EventHierarchyBuilder;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class BetfairMarketTest {

    @Test
    public void shouldReturnParentEventId() {
        // given
        BetfairMarket market = new BetfairMarket();
        market.setEventHierarchy(EventHierarchyBuilder.build(1,2,3,4));

        // when
        String parentEventId = market.getParentEventId();

        // then
        assertThat(parentEventId, is("4"));
    }

    @Test
    public void shouldNotReturnParentEventIdWhenEventHierarchyIsNotSet() {
        // given
        BetfairMarket market = new BetfairMarket();

        // when
        String parentEventId = market.getParentEventId();

        // then
        assertThat(parentEventId, Matchers.nullValue());
    }

    @Test
    public void shouldNotReturnParentEventIdWhenEventHierarchyIsBlank() {
        // given
        BetfairMarket market = new BetfairMarket();
        market.setEventHierarchy("");

        // when
        String parentEventId = market.getParentEventId();

        // then
        assertThat(parentEventId, Matchers.nullValue());
    }
    
    @Test
    public void shouldDetermineParentEventNameFromMarketMenuPath() {
        // given
        BetfairMarket market = BetfairMarkets.betfairMarket();

        // when
        String parentEventName = market.getParentEventName();

        // then
        assertThat(parentEventName, is("Djokovic vs Nadal"));
    }

    @Test
    public void shouldReturnNullAsParentEventNameWhenMarketMenuPathIsNull() {
        // given
        BetfairMarket market = BetfairMarkets.betfairMarket();
        market.setMenuPath(null);

        // when
        String parentEventName = market.getParentEventName();

        // then
        assertThat(parentEventName, nullValue());
    }

    @Test
    @Ignore
    // TODO - need to do something clever to make it work correctly at least for tennis markets
    public void shouldDetermineRootEventNameFromMarketMenuPath() {
        // given
        BetfairMarket market = BetfairMarkets.betfairMarket();

        // when
        String parentEventName = market.getRootEventName();

        // then
        assertThat(parentEventName, is("Wimbledon"));
    }

    @Test
    public void shouldReturnNullAsRootEventNameWhenMarketMenuPathIsNull() {
        // given
        BetfairMarket market = BetfairMarkets.betfairMarket();
        market.setMenuPath(null);

        // when
        String parentEventName = market.getRootEventName();

        // then
        assertThat(parentEventName, nullValue());
    }
}
