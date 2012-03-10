package com.rozarltd.betfairapi.service;


import com.betfair.publicapi.exchange.types.AccountStatementItem;
import com.rozarltd.betfairapi.domain.account.BetfairAccountStatementItem;

import java.util.List;

public interface AccountWalletService {
    public List<BetfairAccountStatementItem> getStatement(int startRecordPosition, int recordCount);
    public List<AccountStatementItem> getMainWalletStatement(int startRecordPosition, int recordCount);
}
