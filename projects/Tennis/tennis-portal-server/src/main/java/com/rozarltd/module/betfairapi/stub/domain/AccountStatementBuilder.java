package com.rozarltd.module.betfairapi.stub.domain;

import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.rozarltd.module.betfairapi.stub.domain.AccountStatementRecordFixture.*;

public class AccountStatementBuilder {
    public static final double DEFAULT_BALANCE = 500;

    private double balance;
    private Date defaultSettleDate = new Date();
    private LinkedList<AccountStatementRecord> statement = new LinkedList<AccountStatementRecord>();

    public AccountStatementBuilder() {
        this(DEFAULT_BALANCE);
    }

    public AccountStatementBuilder(Date defaultSettleDate) {
        this(DEFAULT_BALANCE);
        this.defaultSettleDate = defaultSettleDate;
    }

    public AccountStatementBuilder(double balance) {
        this.balance = balance;
    }

    public void addLostRecord(Date settledDate) {
        addRecord(lostRecord(settledDate, balance));
    }

    public void addLostMarket(String fullMarketName) {
        addRecord(lostRecord(defaultSettleDate, balance, fullMarketName));
        addRecord(winRecord(defaultSettleDate, balance, fullMarketName));
    }

    public void addWinRecord(Date settledDate) {
        addRecord(winRecord(settledDate, balance));
    }

    public void addWinMarket(String fullMarketName) {
        addRecord(commissionRecord(defaultSettleDate, balance, fullMarketName));
        addRecord(winRecord(defaultSettleDate, balance, fullMarketName));
        addRecord(lostRecord(defaultSettleDate, balance, fullMarketName));
        addRecord(winRecord(defaultSettleDate, balance, fullMarketName));
    }

    public void addCommissionRecord(Date settledDate) {
        addRecord(commissionRecord(settledDate, balance));
    }

    public void addDepositRecord(Date settledDate) {
        addRecord(depositRecord(settledDate, balance));
    }

    public void addWithdrawalRecord(Date settledDate) {
        addRecord(withdrawalRecord(settledDate, balance));
    }

    public List<AccountStatementRecord> build() {
        return statement;
    }

    public double getBalance() {
        return balance;
    }

    public Date getDefaultSettleDate() {
        return defaultSettleDate;
    }

    private void addRecord(AccountStatementRecord record) {
        statement.addFirst(record);
        balance = record.getAccountBalance();
    }
}
