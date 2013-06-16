package com.rozarltd.module;

import com.google.inject.AbstractModule;
import com.rozarltd.betting.service.TennisMarketService;
import com.rozarltd.module.betfairapi.service.AccountServiceStub;
import com.rozarltd.module.betfairapi.service.BetfairAccountApi;
import com.rozarltd.module.betfairapi.service.TennisMarketServiceStub;
import com.rozarltd.module.bettingdata.service.BetfairUserBettingDataCollectorService;
import com.rozarltd.module.bettingdata.service.UserBettingDataCollectorService;
import com.rozarltd.repository.BetfairMarketRepository;
import com.rozarltd.repository.BetfairMarketRepositoryStub;
import com.rozarltd.repository.DailyBettingDataRepository;
import com.rozarltd.repository.DailyBettingDataRepositoryStub;

import javax.inject.Singleton;

public class BettingModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserBettingDataCollectorService.class).to(BetfairUserBettingDataCollectorService.class).in(Singleton.class);

        // stubs
        bind(BetfairAccountApi.class).to(AccountServiceStub.class).in(Singleton.class);

        bind(TennisMarketService.class).to(TennisMarketServiceStub.class).in(Singleton.class);

        // stub - repositories
//        bind(BetfairAccountStatementRepository.class).to(AccountStatementRepositoryStub.class).in(Singleton.class);
        bind(DailyBettingDataRepository.class).to(DailyBettingDataRepositoryStub.class).in(Singleton.class);
        bind(BetfairMarketRepository.class).to(BetfairMarketRepositoryStub.class).in(Singleton.class);
    }
}
