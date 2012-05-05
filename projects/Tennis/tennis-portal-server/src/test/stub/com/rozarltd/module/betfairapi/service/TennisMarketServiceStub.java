package com.rozarltd.module.betfairapi.service;

import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.betting.service.TennisMarketService;
import org.apache.commons.lang.time.DateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TennisMarketServiceStub extends TennisMarketService {
    private List<BetfairMarket> stubbedMarkets = new ArrayList<BetfairMarket>();

    public TennisMarketServiceStub() {
        super(null, null, null);
    }

    public void clear() {
        stubbedMarkets = new ArrayList<BetfairMarket>();
    }

    public void addMarkets(BetfairMarket... markets) {
        stubbedMarkets.addAll(Arrays.asList(markets));
    }

    @Override
    public Set<BetfairMarket> getTodayMarkets() {
        Date today = new Date();

        Set<BetfairMarket> todayMarkets = new HashSet<BetfairMarket>();
        for(BetfairMarket market: stubbedMarkets) {
            if(DateUtils.isSameDay(today, market.getStartAtDate())) {
                todayMarkets.add(market);
            }
        }

        return todayMarkets;
    }
}
