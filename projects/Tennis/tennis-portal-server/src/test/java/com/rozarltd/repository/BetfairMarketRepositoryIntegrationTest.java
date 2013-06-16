package com.rozarltd.repository;

import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.stub.domain.BetfairMarkets;
import de.flapdoodle.embedmongo.MongoDBRuntime;
import de.flapdoodle.embedmongo.MongodExecutable;
import de.flapdoodle.embedmongo.MongodProcess;
import de.flapdoodle.embedmongo.config.MongodConfig;
import de.flapdoodle.embedmongo.distribution.Version;
import de.flapdoodle.embedmongo.runtime.Network;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/repository-context.xml")
@Ignore
public class BetfairMarketRepositoryIntegrationTest {
    @Autowired
    private BetfairMarketRepository repository;

    private MongodExecutable mongodExe;
    private MongodProcess mongod;

    @Before
    public void beforeEach() throws Exception {
        MongoDBRuntime runtime = MongoDBRuntime.getDefaultInstance();
        mongodExe = runtime.prepare(new MongodConfig(Version.V2_0_1, 27017, Network.localhostIsIPv6()));
        mongod = mongodExe.start();
    }

    @After
    public void afterEach() throws Exception {
        mongod.stop();
        mongodExe.cleanup();
    }

    @Test
    public void shouldSaveBetfairMarket() {
        // given
        BetfairMarket market = BetfairMarkets.betfairMarket();

        // when
        repository.save(market);

        // then
        assertThat(repository.count(), is(1L));
    }

    @Test
    public void shouldFindAllMarketsBetweenTwoDates() {
        // given
        repository.save(BetfairMarkets.yesterdayMarket(1));
        repository.save(BetfairMarkets.todayMarket(2));
        repository.save(BetfairMarkets.tomorrowMarket(3));

        // TODO - I removed tested method as I don't need it at the moment (or at all)
//        // when
//        List<BetfairMarket> markets = repository.findByStartAtBetween(DateUtilities.today().getTime(), DateUtilities.tomorrow().getTime());
//
//        // then
//        assertThat(markets.size(), is(1));
//        assertThat(markets.get(0).getMarketId(), is(2));
    }
}
