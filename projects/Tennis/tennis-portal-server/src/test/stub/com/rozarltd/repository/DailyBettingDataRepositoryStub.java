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
    public DailyBettingReport save(DailyBettingReport entity) {
        savedObject.add(entity);
        return entity;
    }

    @Override
    public Iterable<DailyBettingReport> save(Iterable<? extends DailyBettingReport> entities) {
        return null;
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
}
