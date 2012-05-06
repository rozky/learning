package com.rozarltd.module.bettingdata.service;

import com.rozarltd.account.User;

import java.util.Date;

public interface UserBettingDataCollectorService {
    public void replicateAccountStatement(User user, Date startDate);
    public void createDailyBettingSummaryReport(User user, Date reportDay);
    public void createDailyBettingSummaryReport(User user, Date start, Date end);
    public Date getTheLatestCompletedDailyBettingSummaryReport();
}
