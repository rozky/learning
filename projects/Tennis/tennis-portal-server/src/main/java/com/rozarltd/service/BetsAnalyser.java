package com.rozarltd.service;

import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;

import java.util.List;

public interface BetsAnalyser<T> {
    List<T> analyse(List<AccountStatementRecord> bets);
}
