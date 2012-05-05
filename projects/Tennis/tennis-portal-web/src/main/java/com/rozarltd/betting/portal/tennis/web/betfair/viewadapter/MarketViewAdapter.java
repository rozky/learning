package com.rozarltd.betting.portal.tennis.web.betfair.viewadapter;

import com.google.common.base.Function;
import com.googlecode.functionalcollections.FunctionalIterables;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MarketViewAdapter {
    private BetfairMarket market;

    public static Set<MarketViewAdapter> transform(Set<BetfairMarket> markets) {
        if(markets != null && markets.size() > 0) {
            return FunctionalIterables.make(markets).transform(new BetfairMarketTransformFunction()).toSet();
        }

        return new HashSet<MarketViewAdapter>();
    }

    public MarketViewAdapter(BetfairMarket market) {
        this.market = market;
    }

    public String getType() {
        if(market.isInPlay()) {
            return "live-market";
        }
        else if(market.isTodayMarket()) {
            return "today-market";
        }

        return "";
    }

    public String getMarketId() {
        return String.valueOf(this.market.getMarketId());
    }

    public List<RunnerViewAdapter> getRunners() {
        return RunnerViewAdapter.transform(this.market.getRunners());
    }

    private static class BetfairMarketTransformFunction implements Function<BetfairMarket, MarketViewAdapter> {
        @Override
        public MarketViewAdapter apply(@Nullable BetfairMarket market) {
            return new MarketViewAdapter(market);
        }
    }
}
