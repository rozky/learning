package com.rozarltd.module.bettingdata;

import com.google.inject.AbstractModule;
import com.rozarltd.module.betfairapi.service.AccountService;
import com.rozarltd.module.bettingdata.service.BetfairUserBettingDataCollectorService;
import com.rozarltd.module.bettingdata.service.UserBettingDataCollectorService;
import com.rozarltd.repository.AccountStatementRepository;
import com.rozarltd.repository.DailyBettingDataRepository;

import javax.inject.Singleton;

public class BettingDataGuiceModule extends AbstractModule {
    private AccountService accountService;
    private AccountStatementRepository accountStatementRepository;
    private DailyBettingDataRepository dailyBettingDataRepository;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setAccountStatementRepository(AccountStatementRepository accountStatementRepository) {
        this.accountStatementRepository = accountStatementRepository;
    }

    public void setDailyBettingDataRepository(DailyBettingDataRepository dailyBettingDataRepository) {
        this.dailyBettingDataRepository = dailyBettingDataRepository;
    }

    @Override
    protected void configure() {
        // injected external dependencies
        bind(AccountService.class).toInstance(accountService);
        bind(AccountStatementRepository.class).toInstance(accountStatementRepository);
        bind(DailyBettingDataRepository.class).toInstance(dailyBettingDataRepository);

        // exposed beans
        bind(UserBettingDataCollectorService.class).to(BetfairUserBettingDataCollectorService.class).in(Singleton.class);

        // internal beans
    }
}
