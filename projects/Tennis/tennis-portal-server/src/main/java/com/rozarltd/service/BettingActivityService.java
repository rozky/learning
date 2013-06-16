package com.rozarltd.service;

import com.rozarltd.domain.BetResult;
import com.rozarltd.domain.Page;
import com.rozarltd.domain.analyse.BettingStatsCollection;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;

import java.util.Date;
import java.util.List;

/**
 * The service containing methods related to user's betting activity like bet history
 */
public interface BettingActivityService {

    /**
     * Get paginated bet results
     *
     * @param page the page to return
     * @return the list of bet results
     */
    List<BetResult> getBetResults(Page page);

    /**
     * Get paginated bets summary by day from current time till given days in the past
     * @param days the number of days to return
     * @return the betting stats for the requested period
     */
    BettingStatsCollection getLatestDaysBets(int days);

    List<AccountStatementRecord> getAccountStatement();
    List<AccountStatementRecord> getAccountStatement(Date from, Date to);
}
