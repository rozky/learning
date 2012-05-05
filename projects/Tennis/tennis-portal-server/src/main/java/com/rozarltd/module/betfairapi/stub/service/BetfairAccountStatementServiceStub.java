package com.rozarltd.module.betfairapi.stub.service;


import com.betfair.publicapi.exchange.types.AccountStatementItem;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.module.betfairapi.service.AccountWalletService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BetfairAccountStatementServiceStub implements AccountWalletService {

    @Override
    public List<AccountStatementRecord> getStatement(int startRecordPosition, int recordCount) {
        List<AccountStatementRecord> statementItems = new ArrayList<AccountStatementRecord>();
        return null;
    }

    @Override
    public List<AccountStatementItem> getMainWalletStatement(int startRecordPosition, int recordCount) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
