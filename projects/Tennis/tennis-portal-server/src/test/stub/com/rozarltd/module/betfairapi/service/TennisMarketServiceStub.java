package com.rozarltd.module.betfairapi.service;

import com.rozarltd.betting.service.TennisMarketService;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import org.apache.commons.lang.time.DateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TennisMarketServiceStub extends TennisMarketService {
    private List<BetfairMarket> stubbedMarkets = new ArrayList<BetfairMarket>();

    public TennisMarketServiceStub() {
        super(null, null);
    }

    public void clear() {
        stubbedMarkets = new ArrayList<BetfairMarket>();
    }

    public void addMarkets(BetfairMarket... markets) {
        stubbedMarkets.addAll(Arrays.asList(markets));
    }

    @Override
    public List<BetfairMarket> getTodayMarkets() {
        Date today = new Date();

        List<BetfairMarket> todayMarkets = new ArrayList<BetfairMarket>();
        for(BetfairMarket market: stubbedMarkets) {
            if(DateUtils.isSameDay(today, market.getStartAtDate())) {
                todayMarkets.add(market);
            }
        }

        return todayMarkets;
    }
}
