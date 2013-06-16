package com.rozarltd.betting.service;

import com.google.inject.Inject;
import com.googlecode.functionalcollections.FunctionalIterables;
import com.googlecode.totallylazy.Sequences;
import com.rozarltd.betting.comparator.Comparators;
import com.rozarltd.betting.functional.BetfairMarketFunctions;
import com.rozarltd.betting.functional.Filters;
import com.rozarltd.domain.market.MarketAdapter;
import com.rozarltd.domain.market.MarketRunner;
import com.rozarltd.module.betfairapi.domain.BetfairEventId;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarketNameEnum;
import com.rozarltd.module.betfairapi.internal.filter.MarketNameFilter;
import com.rozarltd.module.betfairapi.service.BFExchangeApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TennisMarketService implements MarketService {
    private static final Logger logger = LoggerFactory.getLogger(TennisMarketService.class);

    private static final int TENNIS_EVENT_ID = BetfairEventId.tennis.getId();

    private BFExchangeApiService marketService;
    private BetfairMarketFunctions betfairMarketFunctions;

    @Inject
    public TennisMarketService(BFExchangeApiService marketService,
                               BetfairMarketFunctions betfairMarketFunctions) {
        this.marketService = marketService;
        this.betfairMarketFunctions = betfairMarketFunctions;
    }

    @Override
    public List<BetfairMarket> getTodayMarkets() {
        List<BetfairMarket> allMarketsNoPrices = this.getMarkets(BetfairMarketNameEnum.matchOdds);
        if (!CollectionUtils.isEmpty(allMarketsNoPrices)) {

            return Sequences.sequence(allMarketsNoPrices)
                    .filter(Filters.todayMarket())
                    .map(betfairMarketFunctions.addMarketPrices())
                    .sortBy(Comparators.sortByStartDate())
                    .toList();
        }

        return Collections.emptyList();
    }

    @Override
    public List<MarketAdapter> getMatchOddMarkets() {
        List<BetfairMarket> allMarketsNoPrices = this.getMarkets(BetfairMarketNameEnum.matchOdds);
        if (!CollectionUtils.isEmpty(allMarketsNoPrices)) {

            return Sequences.sequence(allMarketsNoPrices)
                    .filter(Filters.todayMarket())
                    .sortBy(Comparators.sortByStartDate())
                    .map(betfairMarketFunctions.addMarketPrices())
                    .map(betfairMarketFunctions.convertToMarketAdapter())
                    .toList();
        }

        return Collections.emptyList();
    }

    @Override
    public Map<Integer, String> getRunnerNames() {
        Map<Integer, String> runnerNames = new HashMap<Integer, String>();
        List<MarketAdapter> markets = this.getMatchOddMarkets();
        if(markets != null && markets.size() > 0) {
            for(MarketAdapter market: markets) {
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
    public List<BetfairMarket> getLiveMarkets(int eventId) {
        List<BetfairMarket> markets = this.getMarkets(BetfairMarketNameEnum.matchOdds);

         if (!CollectionUtils.isEmpty(markets)) {

            return Sequences.sequence(markets)
                    .filter(Filters.liveMarket())
                    .sortBy(Comparators.sortByStartDate())
                    .map(betfairMarketFunctions.addMarketPrices())
                    .toList();
        }

        return Collections.emptyList();
    }

    private List<BetfairMarket> getMarkets(final BetfairMarketNameEnum marketName) {
        List<BetfairMarket> markets = marketService.getMarkets(TENNIS_EVENT_ID);
        return FunctionalIterables.make(markets).filter(new MarketNameFilter(marketName.getName())).toList();
    }

}
