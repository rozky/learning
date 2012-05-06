package com.rozarltd.module.bettingdata;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rozarltd.module.betfairapi.service.AccountService;
import com.rozarltd.module.bettingdata.service.UserBettingDataCollectorService;
import com.rozarltd.repository.AccountStatementRepository;
import com.rozarltd.repository.DailyBettingDataRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BettingDataSpringContext implements InitializingBean {
    @Autowired private AccountService accountService;
    @Autowired private AccountStatementRepository accountStatementRepository;
    @Autowired private DailyBettingDataRepository dailyBettingDataRepository;

    @Bean
    public UserBettingDataCollectorService userBettingDataCollectorService() {
        return moduleBeans.getInstance(UserBettingDataCollectorService.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        BettingDataGuiceModule module = new BettingDataGuiceModule();
        module.setAccountService(accountService);
        module.setAccountStatementRepository(accountStatementRepository);
        module.setDailyBettingDataRepository(dailyBettingDataRepository);

        moduleBeans = Guice.createInjector(module);
    }

    Injector moduleBeans;
}
