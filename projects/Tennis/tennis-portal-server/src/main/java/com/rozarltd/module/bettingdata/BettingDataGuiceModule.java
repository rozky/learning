package com.rozarltd.module.bettingdata;

import com.google.inject.AbstractModule;
import com.rozarltd.module.betfairapi.service.BetfairAccountApi;
import com.rozarltd.module.bettingdata.service.BetfairUserBettingDataCollectorService;
import com.rozarltd.module.bettingdata.service.UserBettingDataCollectorService;
import com.rozarltd.repository.BetfairAccountStatementRepository;
import com.rozarltd.repository.DailyBettingDataRepository;

import javax.inject.Singleton;

public class BettingDataGuiceModule extends AbstractModule {
    private BetfairAccountApi accountService;
    private BetfairAccountStatementRepository accountStatementRepository;
    private DailyBettingDataRepository dailyBettingDataRepository;

    public void setAccountService(BetfairAccountApi accountService) {
        this.accountService = accountService;
    }

    public void setAccountStatementRepository(BetfairAccountStatementRepository accountStatementRepository) {
        this.accountStatementRepository = accountStatementRepository;
    }

    public void setDailyBettingDataRepository(DailyBettingDataRepository dailyBettingDataRepository) {
        this.dailyBettingDataRepository = dailyBettingDataRepository;
    }

    @Override
    protected void configure() {
        // injected external dependencies
        bind(BetfairAccountApi.class).toInstance(accountService);
        bind(BetfairAccountStatementRepository.class).toInstance(accountStatementRepository);
        bind(DailyBettingDataRepository.class).toInstance(dailyBettingDataRepository);

        // exposed beans
        bind(UserBettingDataCollectorService.class).to(BetfairUserBettingDataCollectorService.class).in(Singleton.class);

        // internal beans
    }
}
