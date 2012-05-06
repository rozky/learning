package com.rozarltd.market;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rozarltd.betting.service.TennisMarketReplicationService;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.service.TennisMarketServiceStub;
import com.rozarltd.betting.service.TennisMarketService;
import com.rozarltd.module.betfairapi.stub.domain.BetfairMarkets;
import com.rozarltd.module.BettingModule;
import com.rozarltd.repository.BetfairMarketRepository;
import com.rozarltd.repository.BetfairMarketRepositoryStub;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TennisMarketReplicationServiceStubbedTest {

    private static TennisMarketReplicationService replicationService;
    private static BetfairMarketRepositoryStub marketRepositoryStub;
    private static TennisMarketServiceStub marketFacadeStub;

    private static BetfairMarket marketA;
    private static BetfairMarket marketB;

    @BeforeClass
    public static void beforeClass() {
        Injector injector = Guice.createInjector(new BettingModule());

        replicationService = injector.getInstance(TennisMarketReplicationService.class);
        marketRepositoryStub = (BetfairMarketRepositoryStub) injector.getInstance(BetfairMarketRepository.class);
        marketFacadeStub = (TennisMarketServiceStub) injector.getInstance(TennisMarketService.class);

        marketA = BetfairMarkets.todayMarket(1);
        marketB = BetfairMarkets.todayMarket(2);
    }

    @Before
    public void beforeEach() {
        marketRepositoryStub.clear();
    }

    @Test
    public void shouldReplicateTodayMarkets() {
        // given - there are 1 today market
        marketFacadeStub.addMarkets(marketA);

        // when
        replicationService.replicateTodayMarkets();

        // then
        assertThat(marketRepositoryStub.getStubbedMarkets().size(), is(1));
    }
    
    @Test
    public void shouldReplicateMarketThatWasAlreadyReplicated() {
        // given - the db already contains one of today markets
        marketRepositoryStub.addMarkets(marketA);

        // given - there are 2 today markets
        marketFacadeStub.addMarkets(marketA, marketB);

        // when
        replicationService.replicateTodayMarkets();

        // then
        assertThat(marketRepositoryStub.getStubbedMarkets().size(), is(2));
    }
}
