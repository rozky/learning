package com.rozarltd.module.bettingdata.service;

import com.rozarltd.account.BetfairUser;

import java.util.Date;

public interface UserBettingDataCollectorService {
    public void replicateAccountStatement(BetfairUser user, Date startDate);
    public void createDailyBettingSummaryReport(BetfairUser user, Date reportDay);
    public void createDailyBettingSummaryReport(BetfairUser user, Date start, Date end);
    public Date getTheLatestCompletedDailyBettingSummaryReport();


    // create day aggregates
    // aggregate by day/week/month
}
