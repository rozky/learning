package com.rozarltd.repository;

import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;

public class AccountStatementRepositoryStub implements AccountStatementRepository {
    @Override
    public AccountStatementRecord save(AccountStatementRecord entity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterable<AccountStatementRecord> save(Iterable<? extends AccountStatementRecord> entities) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AccountStatementRecord findOne(Integer integer) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean exists(Integer integer) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterable<AccountStatementRecord> findAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long count() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Integer integer) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(AccountStatementRecord entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Iterable<? extends AccountStatementRecord> entities) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteAll() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
