package com.rozarltd.module.betfairapi.stub.domain;

import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.module.betfairapi.domain.account.statement.BetType;
import com.rozarltd.module.betfairapi.domain.account.statement.TransactionType;
import com.rozarltd.module.betfairapi.domain.account.statement.StatementRecordType;

import java.util.Date;

public class AccountStatementRecordFixture {
    public static final String FULL_MARKET_NAME = "Second Round Matches / Player A vs Player B / Match Odds";
    public static final String MARKET_NAME = "Match Odds";
    public static final long BET_ID = 123456789;
    public static final int SELECTION_A_ID = 1;
    public static final String SELECTION_A_NAME = "Player A";
    public static final int SELECTION_B_ID = 2;
    public static final String SELECTION_B_NAME = "Player B";
    public static final double BET_SIZE = 160.50d;
    public static final double BET_PRICE = 1.5d;
    public static final double WIN = (BET_PRICE - 1) * BET_SIZE;
    public static final double LOST = -1 * BET_SIZE;
    public static final double COMMISSION_RATE = 0.2d;
    public static final double COMMISSION_PAID = COMMISSION_RATE * WIN;
    public static final int EVENT_ID = 104589243;
    public static final int EVENT_TYPE_ID = 1;
    public static final BetType BET_TYPE = BetType.B;
    public static final long SETTLED_DATE = 1326048179000L;
    public static final double DEPOSIT = 4000;
    public static final double WITHDRAWAL = 1500;

    public static AccountStatementRecord winRecord(Date settledDate, double currentBalance, String fullMarketName) {
        AccountStatementRecord item = new AccountStatementRecord();
        item.setAccountBalance(currentBalance + WIN);
        item.setBetId(BET_ID);
        item.setBetType(BET_TYPE);
        item.setEventId(EVENT_ID);
        item.setEventTypeId(EVENT_TYPE_ID);
        item.setAmount(WIN);
        item.setFullMarketName(fullMarketName);
        item.setMarketName(MARKET_NAME);
        item.setPlacedAt(settledDate.getTime());
        item.setPrice(BET_PRICE);
        item.setSelectionId(SELECTION_A_ID);
        item.setSelectionName(SELECTION_A_NAME);
        item.setSettledAt(settledDate.getTime());
        item.setStake(BET_SIZE);
        item.setTransactionType(TransactionType.ACCOUNT_CREDIT);
        item.setWinLost(StatementRecordType.RESULT_WON);

        return item;
    }

    public static AccountStatementRecord winRecord(Date settledDate, double currentBalance) {
        return winRecord(settledDate, currentBalance, FULL_MARKET_NAME);
    }

    public static AccountStatementRecord lostRecord(Date settledDate, double currentBalance, String fullMarketName) {
        AccountStatementRecord item = winRecord(settledDate, currentBalance, fullMarketName);
        item.setAccountBalance(currentBalance + LOST);
        item.setAmount(LOST);
        item.setWinLost(StatementRecordType.RESULT_LOST);
        item.setTransactionType(TransactionType.ACCOUNT_DEBIT);

        return item;
    }

    public static AccountStatementRecord lostRecord(Date settledDate, double currentBalance) {
        return lostRecord(settledDate, currentBalance, FULL_MARKET_NAME);
    }

    public static AccountStatementRecord depositRecord(Date settledDate, double currentBalance) {
        AccountStatementRecord item = winRecord(settledDate, currentBalance);
        item.setMarketName("DEPOSIT");
        item.setAccountBalance(currentBalance + DEPOSIT);
        item.setGrossBetAmount(0);
        item.setAmount(DEPOSIT);
        item.setStake(0);
        item.setPrice(0);
        item.setWinLost(StatementRecordType.DEPOSIT);
        item.setTransactionType(TransactionType.ACCOUNT_CREDIT);

        return item;
    }

    public static AccountStatementRecord withdrawalRecord(Date settledDate, double currentBalance) {
        AccountStatementRecord item = winRecord(settledDate, currentBalance);
        item.setMarketName("WITHDRAWAL");
        item.setAccountBalance(currentBalance - WITHDRAWAL);
        item.setGrossBetAmount(0);
        item.setAmount(-1 * WITHDRAWAL);
        item.setStake(0);
        item.setPrice(0);
        item.setWinLost(StatementRecordType.WITHDRAWAL);
        item.setTransactionType(TransactionType.ACCOUNT_DEBIT);

        return item;
    }


    public static AccountStatementRecord commissionRecord(Date settledDate, double currentBalance, String fullMarketName) {
        AccountStatementRecord item = winRecord(settledDate, currentBalance, fullMarketName);
        item.setAccountBalance(currentBalance - COMMISSION_PAID);
        item.setGrossBetAmount(WIN);
        item.setAmount(-1 * COMMISSION_PAID);
        item.setStake(0);
        item.setPrice(0);
        item.setWinLost(StatementRecordType.COMMISSION);
        item.setTransactionType(TransactionType.ACCOUNT_DEBIT);

        return item;
    }

    public static AccountStatementRecord commissionRecord(Date settledDate, double currentBalance) {
        return commissionRecord(settledDate, currentBalance, FULL_MARKET_NAME);
    }
}
