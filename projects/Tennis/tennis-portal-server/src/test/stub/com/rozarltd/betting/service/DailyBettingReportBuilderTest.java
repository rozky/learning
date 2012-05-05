package com.rozarltd.betting.service;

import com.rozarltd.module.betfairapi.stub.domain.AccountStatementBuilder;
import com.rozarltd.betting.domain.stats.DailyBettingReport;
import com.rozarltd.betting.domain.stats.ReportStatus;
import org.apache.commons.lang.time.DateUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DailyBettingReportBuilderTest {
    private static final String FULL_MARKET_NAME_1 = "Market A";
    private static final String FULL_MARKET_NAME_2 = "Market B";
    private static final String FULL_MARKET_NAME_3 = "Market C";

    private static DailyBettingReportBuilder reportBuilder;

    @BeforeClass
    public static void beforeClass() {
        reportBuilder = new DailyBettingReportBuilder();
    }

    @Test
    public void shouldCountWonAndLostMarkets() {
        // given
        AccountStatementBuilder statementBuilder = new AccountStatementBuilder();
        statementBuilder.addWinMarket(FULL_MARKET_NAME_1);
        statementBuilder.addLostMarket(FULL_MARKET_NAME_2);
        statementBuilder.addWinMarket(FULL_MARKET_NAME_3);

        // when
        DailyBettingReport report = reportBuilder.build(statementBuilder.getDefaultSettleDate(), statementBuilder.build());

        // then
        assertThat(report.getMarkets(), is(3));
        assertThat(report.getWonMarkets(), is(2));
        assertThat(report.getLostMarkets(), is(1));
    }

    @Test
    public void shouldSetReportStatusToPartialWhenTheReportIsCreatedForTheCurrentDay() {
        // given
        AccountStatementBuilder statementBuilder = new AccountStatementBuilder();
        statementBuilder.addLostMarket(FULL_MARKET_NAME_1);

        // when
        DailyBettingReport report = reportBuilder.build(statementBuilder.getDefaultSettleDate(), statementBuilder.build());

        // then
        assertThat(report.getStatus(), is(ReportStatus.P));
    }

    @Test
    public void shouldSetReportStatusToCompletedWhenTheReportIsCreatedForAInThePast() {
        // given
        Date dayInThePast = DateUtils.addDays(new Date(), -1);
        AccountStatementBuilder statementBuilder = new AccountStatementBuilder(dayInThePast);
        statementBuilder.addLostMarket(FULL_MARKET_NAME_1);

        // when
        DailyBettingReport report = reportBuilder.build(dayInThePast, statementBuilder.build());

        // then
        assertThat(report.getStatus(), is(ReportStatus.C));
    }
}
