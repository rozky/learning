package com.rozarltd.module.betfairapi.stub.service;

import com.rozarltd.module.betfairapi.domain.account.BetfairWallet;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.module.betfairapi.service.BetfairAccountApi;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BetfairAccountServiceStub implements BetfairAccountApi {

    @Override
    public String login(String username, String password) {
        return "valid-session-token";
    }

    @Override
    public BetfairWallet getAccountWallets(String sessionToken) {
        return new BetfairWallet("UK", true);
    }

    @Override
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Date day) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Calendar startDate, Calendar endDate, int startRecordPosition, int recordCount) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Calendar startDate, Calendar endDate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Date startDate, Date endDate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
