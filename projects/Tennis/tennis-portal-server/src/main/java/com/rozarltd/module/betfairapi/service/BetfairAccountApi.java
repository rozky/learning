package com.rozarltd.module.betfairapi.service;

import com.rozarltd.module.betfairapi.domain.account.BetfairWallet;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface BetfairAccountApi {
    public String login(String username, String password);
    public BetfairWallet getAccountWallets(String sessionToken);
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Date day);
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Date startDate, Date endDate);
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Calendar startDate, Calendar endDate);
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Calendar startDate, Calendar endDate, int startRecordPosition, int recordCount);
}
