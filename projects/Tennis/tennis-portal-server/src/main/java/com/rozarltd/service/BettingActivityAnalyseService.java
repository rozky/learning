package com.rozarltd.service;

import com.rozarltd.entity.analyse.DayBettingStats;
import com.rozarltd.entity.analyse.MarketBettingStats;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;

import java.util.Date;
import java.util.List;

public interface BettingActivityAnalyseService {
    List<DayBettingStats> analyseByDate(Date from, Date to);
    List<DayBettingStats> analyseByDate(List<AccountStatementRecord> statement);
    List<MarketBettingStats> analyseByMarket(List<AccountStatementRecord> statement);
}
