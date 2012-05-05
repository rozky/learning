package com.rozarltd.betting.service;

import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.module.betfairapi.stub.domain.AccountStatementBuilder;
import com.rozarltd.module.betfairapi.stub.domain.AccountStatementRecordFixture;
import com.rozarltd.betting.domain.stats.DailyBettingReport;
import com.rozarltd.util.java.lang.DoubleUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DailyBettingDataBuilderTest {
    private DailyBettingReportBuilder dailyBettingReportBuilder;

    private static double startBalance = 5000;
    private static Date testedDay;
    private static Date previousDate;
    private static Date nextDate;
    private static List<AccountStatementRecord> statement;
    private static double startBalanceForTestDay;
    private static double endBalanceForTestDay;

    @BeforeClass
    public static void beforeClass() {
        testedDay = new Date();
        previousDate = DateUtils.addDays(testedDay, -1);
        nextDate = DateUtils.addDays(testedDay, +1);

        buildWalletStatement();
    }

    @Before
    public void beforeEach() {
        dailyBettingReportBuilder = new DailyBettingReportBuilder();
    }

    @Test
    public void shouldReturnNotNullDailyBettingDataWhenNoBetsHaveBeenPlacedForGivenDay() {
        // when
        DailyBettingReport dailyBettingData = dailyBettingReportBuilder.build(testedDay, null);


        // then
        assertThat(dailyBettingData.getPlacedBets(), is(0));
    }

    @Test
    public void shouldBuildDailyBettingDate() {

        // when
        DailyBettingReport dailyBettingData = dailyBettingReportBuilder.build(testedDay, statement);

        // then - should count bets placed during the tested date
        int betWon = 1;
        int betLost = 1;
        int betPlaced = betWon + betLost;
        double expectedDeposit = AccountStatementRecordFixture.DEPOSIT;
        double expectedWithdrawal = AccountStatementRecordFixture.WITHDRAWAL;

        assertThat("Incorrect day", dailyBettingData.getDate(), is(DateUtils.truncate(testedDay, Calendar.DATE)));
        assertThat("Incorrect bet count", dailyBettingData.getPlacedBets(), is(betPlaced));
        assertThat("Incorrect won bet count", dailyBettingData.getWonBets(), is(betWon));
        assertThat("Incorrect lost bet count", dailyBettingData.getLostBets(), is(betLost));
        assertThat("Incorrect amount placed", dailyBettingData.getTotalAmountPlaced(), is(betPlaced * AccountStatementRecordFixture.BET_SIZE));
        assertThat("Incorrect commission paid", dailyBettingData.getCommissionPaid(), is(betWon * AccountStatementRecordFixture.COMMISSION_PAID));
        assertThat("Incorrect start balance", dailyBettingData.getStartBalance(), is(startBalanceForTestDay));
        assertThat("Incorrect end balance", dailyBettingData.getEndBalance(), is(endBalanceForTestDay));
        assertThat("Incorrect deposit amount", dailyBettingData.getDeposits(), is(expectedDeposit));
        assertThat("Incorrect withdrawal amount", dailyBettingData.getWithdrawals(), is(expectedWithdrawal));
        assertThat("Incorrect profit", dailyBettingData.getProfit(),
                is(DoubleUtils.valueOf(endBalanceForTestDay - startBalanceForTestDay - expectedDeposit + expectedWithdrawal, 2)));

    }

    @Test
    public void shouldAddMidnightBetToNextDay() {
        // given
        AccountStatementBuilder statementBuilder = new AccountStatementBuilder(0);
        statementBuilder.addLostRecord(testedDay);
        statementBuilder.addWinRecord(DateUtils.truncate(nextDate, Calendar.DATE));    // midnight

        // when
        DailyBettingReport dailyBettingData = dailyBettingReportBuilder.build(testedDay, statementBuilder.build());

        // then
        assertThat("Incorrect placed bet count", dailyBettingData.getPlacedBets(), is(1));
        assertThat("Incorrect lost bet count", dailyBettingData.getLostBets(), is(1));
    }

    private static void buildWalletStatement() {
        AccountStatementBuilder statementBuilder = new AccountStatementBuilder(startBalance);
        statementBuilder.addWinRecord(previousDate);
        statementBuilder.addCommissionRecord(previousDate);
        startBalanceForTestDay = statementBuilder.getBalance();
        statementBuilder.addDepositRecord(testedDay);
        statementBuilder.addLostRecord(testedDay);
        statementBuilder.addWithdrawalRecord(testedDay);
        statementBuilder.addWinRecord(testedDay);
        statementBuilder.addCommissionRecord(testedDay);
        endBalanceForTestDay = statementBuilder.getBalance();
        statementBuilder.addWinRecord(nextDate);
        statementBuilder.addCommissionRecord(nextDate);

        statement = statementBuilder.build();
    }

}
