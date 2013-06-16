package com.rozarltd.betting.service;

import com.rozarltd.account.BetfairUser;
import com.rozarltd.module.bettingdata.service.BetfairUserBettingDataCollectorService;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/external/server-module-context.xml")
@Ignore
public class BetfairUserBettingDataCollectorServiceIntegrationTest {
    @Autowired private BetfairUserBettingDataCollectorService bettingDataService;
    private BetfairUser user = new BetfairUser("michal", "FRO7qQAQMGE+PjizvML0BX6JA3UHVTX8FE6MmHrG5EQ=", null);

    @Test
    public void shouldReplicateAccountStatement() {
        // given
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.set(Calendar.MONTH, Calendar.MARCH);
        startDateCalendar.set(Calendar.DAY_OF_MONTH, 6);
        startDateCalendar = DateUtils.truncate(Calendar.getInstance(), Calendar.DATE);

        // when
        bettingDataService.replicateAccountStatement(user, startDateCalendar.getTime());

        // then
    }

    @Test
    public void shouldCreateDailyBettingData() {
        // given
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.set(Calendar.MONTH, Calendar.MARCH);
        startDateCalendar.set(Calendar.DAY_OF_MONTH, 6);
        startDateCalendar = DateUtils.truncate(startDateCalendar, Calendar.DATE);

        // when
        bettingDataService.createDailyBettingSummaryReport(user, startDateCalendar.getTime());
        // then
    }
}
