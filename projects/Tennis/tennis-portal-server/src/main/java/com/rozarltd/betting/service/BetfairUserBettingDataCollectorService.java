package com.rozarltd.betting.service;

import com.google.inject.Inject;
import com.rozarltd.account.User;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.module.betfairapi.service.AccountService;
import com.rozarltd.betting.domain.stats.DailyBettingReport;
import com.rozarltd.repository.AccountStatementRepository;
import com.rozarltd.repository.DailyBettingDataRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Component
public class BetfairUserBettingDataCollectorService implements UserBettingDataCollectorService {
    private AccountService accountService;
    private AccountStatementRepository accountStatementRepository;
    private DailyBettingDataRepository dailyBettingDataRepository;

    @Autowired
    @Inject
    public BetfairUserBettingDataCollectorService(AccountService accountService,
                                                  AccountStatementRepository accountStatementRepository,
                                                  DailyBettingDataRepository dailyBettingDataRepository) {
        this.accountService = accountService;
        this.accountStatementRepository = accountStatementRepository;
        this.dailyBettingDataRepository = dailyBettingDataRepository;
    }

    public void replicateAccountStatement(User user, Date startDate) {

        List<AccountStatementRecord> statementRecords =
                accountService.getWalletStatement(user.getBetfairPublicApiToken(), startDate, new Date());

        if (statementRecords != null && statementRecords.size() > 0) {
            for (AccountStatementRecord record : statementRecords) {
                accountStatementRepository.save(record);
            }
        }
    }

    public void createDailyBettingSummaryReport(User user, Date reportDay) {
        List<AccountStatementRecord> statement = accountService.getWalletStatement(user.getBetfairPublicApiToken(), reportDay);
        DailyBettingReport dailyBettingData =  new DailyBettingReportBuilder().build(reportDay, statement);

        dailyBettingDataRepository.save(dailyBettingData);
    }

    public void createDailyBettingSummaryReport(User user, Date start, Date end) {
        Date endDate = DateUtils.addDays(DateUtils.truncate(end, Calendar.DATE), 1);

        List<AccountStatementRecord> statement =
                accountService.getWalletStatement(user.getBetfairPublicApiToken(),
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
