package com.rozarltd.betfairapi.service;

import com.google.common.base.Predicate;
import com.googlecode.functionalcollections.FunctionalIterables;
import com.rozarltd.betfairapi.domain.BetfairEventId;
import com.rozarltd.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.betfairapi.domain.market.BetfairMarketNameEnum;
import com.rozarltd.betfairapi.domain.market.BetfairRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

@Service
public class BetfairMarketFacade implements MarketFacade {
    private static final Logger logger = LoggerFactory.getLogger(BetfairMarketFacade.class);

    private BFExchangeApiService marketService;
    private BFRestApiFacade restApiFacade;

    private Filter refreshMarketFilter = new RefreshMarketFilter();

    @Autowired
    public BetfairMarketFacade(BFExchangeApiService marketService,
                               BFRestApiFacade restApiFacade) {
        this.marketService = marketService;
        this.restApiFacade = restApiFacade;
    }

    @Override
    public Set<BetfairMarket> getMatchOddMarkets(int rootEventId) {
        Set<BetfairMarket> methodOutput = new TreeSet<BetfairMarket>(BetfairMarketByStartDateComparator.getInstance());

        List<BetfairMarket> markets = this.getMarkets(rootEventId, BetfairMarketNameEnum.matchOdds.getName());
        if (markets != null) {

            // load runners
            long updateTimeThreshold = getOneDayFromNowTimeInMilliseconds();

            for (BetfairMarket market : markets) {
                if(market.getRunners() == null || market.getStartAt() < updateTimeThreshold) {
                    refreshMarket(market);
                }

                if(!market.isClosed()) {
                    methodOutput.add(market);
                }
            }
        }

        return methodOutput;
    }

    @Override
    public Map<Integer, String> getRunnerNames() {
        Map<Integer, String> runnerNames = new HashMap<Integer, String>();
        Set<BetfairMarket> markets = this.getMatchOddMarkets(BetfairEventId.tennis.getId());
        if(markets != null && markets.size() > 0) {
            for(BetfairMarket market: markets) {
                List<BetfairRunner> runners = market.getRunners();
                if(runners != null && runners.size() > 0) {
                    for(BetfairRunner runner: runners) {
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
    public List<BetfairMarket> getMarkets(final int eventId, final String marketName) {
        List<BetfairMarket> markets = marketService.getMarkets(eventId);

        return FunctionalIterables.make(markets).filter(new Predicate<BetfairMarket>() {
            @Override
            public boolean apply(BetfairMarket market) {
                return marketName == null || marketName.equals(market.getMarketName());
            }
        }).toList();
    }

    @Override
    public List<BetfairMarket> getInPlayMarkets(int eventId) {
        List<BetfairMarket> markets = marketService.getMarkets(eventId);

        return FunctionalIterables.make(markets).filter(new Predicate<BetfairMarket>() {
            @Override
            public boolean apply(BetfairMarket market) {
                return market.isInPlay();
            }
        }).toList();

    }

    public List<BetfairMarket> getInPlayMarkets(final int eventId, final BetfairMarketNameEnum marketName) {
        List<BetfairMarket> markets = marketService.getMarkets(eventId);

        return FunctionalIterables.make(markets).filter(new Predicate<BetfairMarket>() {
            @Override
            public boolean apply(BetfairMarket market) {
                return market.isInPlay() && marketName.getName().equals(market.getMarketName());
            }
        }).toList();
    }

    private static class BetfairMarketByStartDateComparator implements Comparator<BetfairMarket> {
        public static Comparator<BetfairMarket> instance = new BetfairMarketByStartDateComparator();

        public static Comparator<BetfairMarket> getInstance() {
            return instance;
        }

        @Override
        public int compare(BetfairMarket o1, BetfairMarket o2) {
            return o1.getStartAt() == o2.getStartAt() ? 0 : (o1.getStartAt() > o2.getStartAt() ? 1 : -1);
        }
    }

    private void refreshMarket(BetfairMarket market) {
        BetfairMarket currentMarket = restApiFacade.getMarket(market.getMarketId());
        market.setMarketStatus(currentMarket.getMarketStatus());
        if(!market.isClosed()) {
            List<BetfairRunner> runners = currentMarket.getRunners();
            if(runners != null && runners.size() > 0) {
                market.setRunners(runners);
            }
        }
    }

    private long getOneDayFromNowTimeInMilliseconds() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);

        return calendar.getTimeInMillis();
    }

    public interface Filter<T> {
        public boolean doFilter(T item);
    }

    public class RefreshMarketFilter implements Filter<BetfairMarket> {

        @Override
        public boolean doFilter(BetfairMarket item) {
            return false;
        }
    }
}
