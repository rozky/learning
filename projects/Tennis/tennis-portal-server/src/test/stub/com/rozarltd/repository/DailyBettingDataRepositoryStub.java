package com.rozarltd.repository;

import com.rozarltd.betting.domain.stats.DailyBettingReport;

import java.util.ArrayList;
import java.util.List;

public class DailyBettingDataRepositoryStub implements DailyBettingDataRepository {
    List<DailyBettingReport> savedObject = new ArrayList<DailyBettingReport>();

    public List<DailyBettingReport> getSavedObject() {
        return savedObject;
    }

    @Override
    public DailyBettingReport findOne(Integer integer) {
        return null;
    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }

    @Override
    public Iterable<DailyBettingReport> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void delete(DailyBettingReport entity) {

    }

    @Override
    public void delete(Iterable<? extends DailyBettingReport> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends DailyBettingReport> S save(S s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <S extends DailyBettingReport> Iterable<S> save(Iterable<S> ses) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterable<DailyBettingReport> findAll(Iterable<Integer> integers) {
        return null;
    }
}
