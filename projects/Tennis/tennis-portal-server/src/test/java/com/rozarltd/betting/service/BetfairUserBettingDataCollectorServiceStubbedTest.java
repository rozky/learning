package com.rozarltd.betting.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rozarltd.account.User;
import com.rozarltd.betting.domain.stats.DailyBettingReport;
import com.rozarltd.module.BettingModule;
import com.rozarltd.repository.DailyBettingDataRepository;
import com.rozarltd.repository.DailyBettingDataRepositoryStub;
import com.rozarltd.util.java.lang.DateUtilities;
import org.apache.commons.lang.time.DateUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BetfairUserBettingDataCollectorServiceStubbedTest {
    private static DailyBettingDataRepositoryStub dailyBettingDataRepositoryStub;
    private static UserBettingDataCollectorService bettingDataCollectorService;

    @BeforeClass
    public static void beforeEach() {
        Injector injector = Guice.createInjector(new BettingModule());

        bettingDataCollectorService = injector.getInstance(UserBettingDataCollectorService.class);
        dailyBettingDataRepositoryStub = (DailyBettingDataRepositoryStub) injector.getInstance(DailyBettingDataRepository.class);
    }

    @Test
    public void shouldCreateDailyBettingReportForRangeOfDays() {
        // given
        Date startDate = new Date();
        Date endDate = DateUtils.addDays(startDate, 2);
        User user = new User("MichalR", "BetfairApiToken", "BetfairRestApiToken");

        // when
        bettingDataCollectorService.createDailyBettingSummaryReport(user, startDate, endDate);

        // then
        List<DailyBettingReport> savedObject = dailyBettingDataRepositoryStub.getSavedObject();
        assertThat(savedObject.size(), is(3));
        assertThat(savedObject.get(0).getDate(), is(DateUtilities.truncateToDay(startDate)));
        assertThat(savedObject.get(1).getDate(), is(DateUtilities.truncateAndAddDays(startDate, 1)));
        assertThat(savedObject.get(2).getDate(), is(DateUtilities.truncateAndAddDays(startDate, 2)));
    }
}