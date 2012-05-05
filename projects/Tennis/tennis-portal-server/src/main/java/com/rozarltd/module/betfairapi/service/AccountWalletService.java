package com.rozarltd.module.betfairapi.service;


import com.betfair.publicapi.exchange.types.AccountStatementItem;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;

import java.util.List;

public interface AccountWalletService {
    public List<AccountStatementRecord> getStatement(int startRecordPosition, int recordCount);
    public List<AccountStatementItem> getMainWalletStatement(int startRecordPosition, int recordCount);
}
