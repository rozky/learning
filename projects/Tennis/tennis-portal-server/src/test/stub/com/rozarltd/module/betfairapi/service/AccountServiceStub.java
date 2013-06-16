package com.rozarltd.module.betfairapi.service;

import com.rozarltd.module.betfairapi.domain.account.BetfairWallet;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AccountServiceStub implements BetfairAccountApi {
    public static final String VALID_SESSION_TOKEN = "VALID_SESSION_TOKEN";

    @Override
    public String login(String username, String password) {
        throw new IllegalStateException("method not implemented");
    }

    @Override
    public BetfairWallet getAccountWallets(String sessionToken) {
        throw new IllegalStateException("method not implemented");
    }

    @Override
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Date day) {
        throw new IllegalStateException("method not implemented");
    }

    @Override
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Date startDate, Date endDate) {
        return new ArrayList<AccountStatementRecord>();
    }

    @Override
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Calendar startDate, Calendar endDate) {
        throw new IllegalStateException("method not implemented");
    }

    @Override
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Calendar startDate, Calendar endDate, int startRecordPosition, int recordCount) {
        throw new IllegalStateException("method not implemented");
    }
}
