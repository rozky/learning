package com.rozarltd.module.bettingdata.service;

import com.google.inject.Inject;
import com.rozarltd.account.BetfairUser;
import com.rozarltd.betting.domain.stats.DailyBettingReport;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.module.betfairapi.service.BetfairAccountApi;
import com.rozarltd.repository.BetfairAccountStatementRepository;
import com.rozarltd.repository.DailyBettingDataRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Component
public class BetfairUserBettingDataCollectorService implements UserBettingDataCollectorService {
    private BetfairAccountApi accountService;
    private BetfairAccountStatementRepository accountStatementRepository;
    private DailyBettingDataRepository dailyBettingDataRepository;

    @Autowired
    @Inject
    public BetfairUserBettingDataCollectorService(BetfairAccountApi accountService,
                                                  BetfairAccountStatementRepository accountStatementRepository,
                                                  DailyBettingDataRepository dailyBettingDataRepository) {
        this.accountService = accountService;
        this.accountStatementRepository = accountStatementRepository;
        this.dailyBettingDataRepository = dailyBettingDataRepository;
    }

    public void replicateAccountStatement(BetfairUser user, Date startDate) {

        List<AccountStatementRecord> statementRecords =
                accountService.getWalletStatement(user.getPublicApiToken(), startDate, new Date());

        if (statementRecords != null && statementRecords.size() > 0) {
            for (AccountStatementRecord record : statementRecords) {
                accountStatementRepository.save(record);
            }
        }
    }

    public void createDailyBettingSummaryReport(BetfairUser user, Date reportDay) {
        List<AccountStatementRecord> statement = accountService.getWalletStatement(user.getPublicApiToken(), reportDay);
        DailyBettingReport dailyBettingData =  new DailyBettingReportBuilder().build(reportDay, statement);

        dailyBettingDataRepository.save(dailyBettingData);
    }

    public void createDailyBettingSummaryReport(BetfairUser user, Date start, Date end) {
        Date endDate = DateUtils.addDays(DateUtils.truncate(end, Calendar.DATE), 1);

        List<AccountStatementRecord> statement =
                accountService.getWalletStatement(user.getPublicApiToken(),
                        DateUtils.truncate(start, Calendar.DATE), endDate);

        Date current = start;
        while (current.before(endDate)) {

            DailyBettingReport dailyBettingData =  new DailyBettingReportBuilder().build(current, statement);

            dailyBettingDataRepository.save(dailyBettingData);

            current = DateUtils.addDays(current, 1);
        }
    }

    @Override
    public Date getTheLatestCompletedDailyBettingSummaryReport() {
        return null;// todo
    }
}
