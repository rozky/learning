package com.rozarltd.betting.service;

import com.google.common.base.Function;
import com.google.inject.Inject;
import com.googlecode.functionalcollections.FunctionalIterable;
import com.googlecode.functionalcollections.FunctionalIterables;
import com.rozarltd.betting.comparator.BetfairMarketByStartDateComparator;
import com.rozarltd.module.betfairapi.domain.BetfairEventId;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarketNameEnum;
import com.rozarltd.module.betfairapi.internal.filter.InPlayMarketFilter;
import com.rozarltd.module.betfairapi.internal.filter.MarketNameFilter;
import com.rozarltd.module.betfairapi.internal.filter.TodayMarketFilter;
import com.rozarltd.module.betfairapi.internal.function.MarketRefresher;
import com.rozarltd.module.betfairapi.service.BFExchangeApiService;
import com.rozarltd.domain.market.Market;
import com.rozarltd.domain.market.MarketRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

@Service
public class TennisMarketService implements MarketService {
    private static final Logger logger = LoggerFactory.getLogger(TennisMarketService.class);

    private static final int TENNIS_EVENT_ID = BetfairEventId.tennis.getId();

    private BFExchangeApiService marketService;
    private MarketRefresher marketRefresher;
    private ConversionService conversionService;

    @Autowired
    @Inject
    public TennisMarketService(BFExchangeApiService marketService,
                               MarketRefresher marketRefresher,
                               ConversionService conversionService) {
        this.marketService = marketService;
        this.marketRefresher = marketRefresher;
        this.conversionService = conversionService;
    }

    @Override
    public Set<BetfairMarket> getTodayMarkets() {
        Set<BetfairMarket> todayMarketsSorted = new TreeSet<BetfairMarket>(BetfairMarketByStartDateComparator.getInstance());

        List<BetfairMarket> markets = this.getMarkets(TENNIS_EVENT_ID, BetfairMarketNameEnum.matchOdds.getName());
        if (markets != null) {
            FunctionalIterable<BetfairMarket> todayMarkets = FunctionalIterables.make(markets).filter(new TodayMarketFilter());

            // load runner names and prices
            todayMarketsSorted.addAll(FunctionalIterables.make(todayMarkets).each(marketRefresher).toCollection());
        }

        return todayMarketsSorted;
    }

    @Override
    public Set<Market> getMatchOddMarkets() {
        // todo - add comparator to spring context
//        Set<Market> todayMarketsSorted = new TreeSet<Market>(DateComparator.getInstance());
        Set<Market> todayMarketsSorted = new HashSet<Market>();

        List<BetfairMarket> betfairMarkets = this.getMarkets(TENNIS_EVENT_ID, BetfairMarketNameEnum.matchOdds.getName());
        if (betfairMarkets != null && !betfairMarkets.isEmpty()) {

            // TODO: tomorrow: challenge: combine filtering and transformation into single step
            FunctionalIterable<Market> todayMarkets = FunctionalIterables.make(betfairMarkets)
                    .filter(new TodayMarketFilter())
                    .transform(new Function<BetfairMarket, Market>() {
                        @Override
                        public Market apply(BetfairMarket market) {
                            return conversionService.convert(market, Market.class);
                        }
                    });

//            for(BetfairMarket betfairMarket: )

            // load runner names and prices
            // todayMarketsSorted.addAll(FunctionalIterables.make(todayMarkets).each(marketRefresher).toCollection());

            todayMarketsSorted.addAll(todayMarkets.toCollection());
        }

        return todayMarketsSorted;
    }

    public Set<BetfairMarket> getMatchOddMarketsPrototype() {
        // todo - add comparator to spring context
        Set<BetfairMarket> todayMarketsSorted = new TreeSet<BetfairMarket>(BetfairMarketByStartDateComparator.getInstance());

        List<BetfairMarket> betfairApiMarkets = this.getMarkets(TENNIS_EVENT_ID, BetfairMarketNameEnum.matchOdds.getName());

        if (betfairApiMarkets != null && !betfairApiMarkets.isEmpty()) {

            // todo
            List<Market> markets = new ArrayList<Market>();
            conversionService.convert(betfairApiMarkets, markets.getClass());

            // TODO: tomorrow: challenge: combine filtering and transformation into single step
            FunctionalIterable<BetfairMarket> todayMarkets = FunctionalIterables.make(betfairApiMarkets).filter(new TodayMarketFilter());

            // load runner names and prices
            todayMarketsSorted.addAll(FunctionalIterables.make(todayMarkets).each(marketRefresher).toCollection());
        }

        return todayMarketsSorted;
    }

    @Override
    public Map<Integer, String> getRunnerNames() {
        Map<Integer, String> runnerNames = new HashMap<Integer, String>();
        Set<Market> markets = this.getMatchOddMarkets();
        if(markets != null && markets.size() > 0) {
            for(Market market: markets) {
                List<MarketRunner> runners = market.getRunners();
                if(runners != null && runners.size() > 0) {
                    for(MarketRunner runner: runners) {
                        runnerNames.put(runner.getRunnerId(), runner.getRunnerName());
                    }
                }
            }
        }
        return runnerNames;
    }

    public Map<String, List<BetfairMarket>> getMarkets(int eventId) {
        List<BetfairMarket> markets = marketService.getMarkets(eventId);

        Map<String, List<BetfairMarket>> result = new TreeMap<String, List<BetfairMarket>>();
        for (BetfairMarket market : markets) {
            String key = market.getMenuPath();
            List<BetfairMarket> eventMarkets = result.get(key);
            if (eventMarkets == null) {
                eventMarkets = new ArrayList<BetfairMarket>();
                result.put(key, eventMarkets);
            }

            eventMarkets.add(market);
        }

        return result;
    }

    @Override
    public List<BetfairMarket> getInPlayMarkets(int eventId) {
        List<BetfairMarket> markets = marketService.getMarkets(eventId);
        return FunctionalIterables.make(markets).filter(new InPlayMarketFilter()).toList();
    }

    private List<BetfairMarket> getMarkets(final int eventId, final String marketName) {
        List<BetfairMarket> markets = marketService.getMarkets(eventId);
        return FunctionalIterables.make(markets).filter(new MarketNameFilter(marketName)).toList();
    }

}
