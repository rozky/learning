package com.rozarltd.betfairapi.stub.service;


import com.betfair.publicapi.exchange.types.AccountStatementItem;
import com.rozarltd.betfairapi.domain.account.BetfairAccountStatementItem;
import com.rozarltd.betfairapi.service.AccountWalletService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BetfairAccountStatementServiceStub implements AccountWalletService {

    @Override
    public List<BetfairAccountStatementItem> getStatement(int startRecordPosition, int recordCount) {
        List<BetfairAccountStatementItem> statementItems = new ArrayList<BetfairAccountStatementItem>();
        return null;
    }

    @Override
    public List<AccountStatementItem> getMainWalletStatement(int startRecordPosition, int recordCount) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
