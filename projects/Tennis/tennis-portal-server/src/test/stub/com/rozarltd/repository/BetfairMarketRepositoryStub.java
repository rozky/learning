package com.rozarltd.repository;

import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BetfairMarketRepositoryStub implements BetfairMarketRepository {
    private List<BetfairMarket> stubbedMarkets = new ArrayList<BetfairMarket>();

    public void clear() {
        stubbedMarkets = new ArrayList<BetfairMarket>();
    }

    public void addMarkets(BetfairMarket... markets) {
        stubbedMarkets.addAll(Arrays.asList(markets));
    }

    public List<BetfairMarket> getStubbedMarkets() {
        return stubbedMarkets;
    }

    @Override
    public List<BetfairMarket> findMarkets(long startDate, long endDate) {
        List<BetfairMarket> markets = new ArrayList<BetfairMarket>();
        for(BetfairMarket market: stubbedMarkets) {
            if(new Date(startDate).before(market.getStartAtDate()) && new Date(endDate).after(market.getStartAtDate())) {
                markets.add(market);
            }
        }
        return markets;
    }

    @Override
    public BetfairMarket save(BetfairMarket entity) {
        stubbedMarkets.add(entity);
        return entity;
    }

    @Override
    public Iterable<BetfairMarket> save(Iterable<? extends BetfairMarket> entities) {
        throw new IllegalStateException("not supported method");
    }

    @Override
    public BetfairMarket findOne(Integer integer) {
        throw new IllegalStateException("not supported method");
    }

    @Override
    public boolean exists(Integer integer) {
        throw new IllegalStateException("not supported method");
    }

    @Override
    public Iterable<BetfairMarket> findAll() {
        throw new IllegalStateException("not supported method");
    }

    @Override
    public long count() {
        throw new IllegalStateException("not supported method");
    }

    @Override
    public void delete(Integer integer) {
        throw new IllegalStateException("not supported method");
    }

    @Override
    public void delete(BetfairMarket entity) {
        throw new IllegalStateException("not supported method");
    }

    @Override
    public void delete(Iterable<? extends BetfairMarket> entities) {
        throw new IllegalStateException("not supported method");
    }

    @Override
    public void deleteAll() {
        clear();
    }
}
