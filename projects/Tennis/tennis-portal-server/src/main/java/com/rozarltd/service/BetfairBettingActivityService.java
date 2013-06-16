package com.rozarltd.service;

import com.rozarltd.domain.BetResult;
import com.rozarltd.domain.Page;
import com.rozarltd.domain.analyse.BettingStatsCollection;
import com.rozarltd.entity.analyse.DayBettingStats;
import com.rozarltd.entity.analyse.MarketBettingStats;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.repository.BetfairAccountStatementRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Service providing methods for betting activity on the Betfair
 */
public class BetfairBettingActivityService implements BettingActivityService {

    @Autowired
    private BetfairAccountStatementRepository repository;

    @Autowired
    private BettingActivityAnalyseService bettingActivityAnalyseService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BetResult> getBetResults(Page page) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BettingStatsCollection getLatestDaysBets(int days) {
        Date now = new Date();
        Date today = DateUtils.truncate(now, Calendar.DATE);
        Iterable<AccountStatementRecord> statement =
                repository.findBySettledAtBetween(DateUtils.addDays(today, -days).getTime(), now.getTime());

        List<AccountStatementRecord> statementList =
                statement == null ? new ArrayList<AccountStatementRecord>() : (List<AccountStatementRecord>) statement;

        List<DayBettingStats> statsByDate = bettingActivityAnalyseService.analyseByDate(statementList);
        List<MarketBettingStats> statsByMarket = bettingActivityAnalyseService.analyseByMarket(statementList);
        return new BettingStatsCollection(statsByDate, statsByMarket);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountStatementRecord> getAccountStatement() {
        Iterable<AccountStatementRecord> everything = repository.findAll();
        return everything == null ? new ArrayList<AccountStatementRecord>() : (List<AccountStatementRecord>) everything;
    }

    @Override
    public List<AccountStatementRecord> getAccountStatement(Date from, Date to) {
        Iterable<AccountStatementRecord> result = repository.findBySettledAtBetween(from.getTime(), to.getTime());
        return result == null ? new ArrayList<AccountStatementRecord>() : (List<AccountStatementRecord>) result;
    }
}
