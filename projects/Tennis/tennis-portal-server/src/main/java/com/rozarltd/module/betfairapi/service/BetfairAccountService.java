package com.rozarltd.module.betfairapi.service;


import com.betfair.publicapi.exchange.BFExchangeService;
import com.betfair.publicapi.exchange.types.AccountStatementItem;
import com.betfair.publicapi.exchange.types.ArrayOfAccountStatementItem;
import com.betfair.publicapi.exchange.types.GetAccountFundsErrorEnum;
import com.betfair.publicapi.exchange.types.GetAccountFundsReq;
import com.betfair.publicapi.exchange.types.GetAccountFundsResp;
import com.betfair.publicapi.exchange.types.GetAccountStatementErrorEnum;
import com.betfair.publicapi.exchange.types.GetAccountStatementReq;
import com.betfair.publicapi.exchange.types.GetAccountStatementResp;
import com.betfair.publicapi.global.BFGlobalService;
import com.betfair.publicapi.global.types.LoginErrorEnum;
import com.betfair.publicapi.global.types.LoginReq;
import com.betfair.publicapi.global.types.LoginResp;
import com.google.inject.Inject;
import com.googlecode.functionalcollections.FunctionalIterables;
import com.rozarltd.module.betfairapi.domain.account.Wallet;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.module.betfairapi.internal.factory.BFExchangeAPIRequestFactory;
import com.rozarltd.module.betfairapi.internal.factory.GlobalAPIRequestFactory;
import com.rozarltd.module.betfairapi.internal.mapper.ObjectTypeMapper;
import com.rozarltd.module.betfairapi.internal.mapper.ObjectTypeMapperManager;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BetfairAccountService implements AccountService {
    private final BFGlobalService betfairGlobalService;
    private final GlobalAPIRequestFactory betfairGlobalApiRequestFactory;
    private final BFExchangeService betfairExchangeService;
    private final BFExchangeAPIRequestFactory betfairExchangeApiRequestFactory;
    private final ObjectTypeMapperManager typeMapperManager;

    @Autowired
    @Inject
    public BetfairAccountService(BFGlobalService betfairGlobalService,
                                 GlobalAPIRequestFactory betfairGlobalApiRequestFactory,
                                 BFExchangeService betfairExchangeService,
                                 BFExchangeAPIRequestFactory betfairExchangeApiRequestFactory,
                                 ObjectTypeMapperManager typeMapperManager) {
        this.betfairGlobalService = betfairGlobalService;
        this.betfairGlobalApiRequestFactory = betfairGlobalApiRequestFactory;
        this.betfairExchangeService = betfairExchangeService;
        this.betfairExchangeApiRequestFactory = betfairExchangeApiRequestFactory;
        this.typeMapperManager = typeMapperManager;
    }

    public String login(String username, String password) {
        LoginReq request = betfairGlobalApiRequestFactory.createLoginRequest(username, password);
        LoginResp response = betfairGlobalService.login(request);

        if(LoginErrorEnum.OK == response.getErrorCode()) {
            return response.getHeader().getSessionToken();
        }
        return null;
    }

    @Override
    public Wallet getAccountWallets(String sessionToken) {
        GetAccountFundsReq request = betfairExchangeApiRequestFactory.createGetAccountFundsRequest(sessionToken);
        GetAccountFundsResp response = betfairExchangeService.getAccountFunds(request);

        if(GetAccountFundsErrorEnum.OK.equals(response.getErrorCode())) {

            Wallet wallet = new Wallet(Wallet.UK_WALLET_NAME, true);
            wallet.setBalance(response.getBalance());
            wallet.setAvailableBalance(response.getAvailBalance());
            wallet.setExposure(response.getExposure());

            return wallet;
        }

        return new Wallet(Wallet.UK_WALLET_NAME, false);
    }

    @Override
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Date day) {
        Date beginOfDay = DateUtils.truncate(day, Calendar.DATE);
        Date endDate = DateUtils.addHours(beginOfDay, 24);

        return getWalletStatement(sessionToken, beginOfDay, endDate);
    }

    @Override
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Date startDate, Date endDate) {
        return getWalletStatement(sessionToken, DateUtils.toCalendar(startDate), DateUtils.toCalendar(endDate));
    }

    @Override
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Calendar startDate, Calendar endDate) {
        return getWalletStatement(sessionToken, startDate, endDate, 0, 2000);
    }

    @Override
    public List<AccountStatementRecord> getWalletStatement(String sessionToken, Calendar startDate, Calendar endDate,
                                                                int startRecordPosition, int recordCount) {

        GetAccountStatementReq request = betfairExchangeApiRequestFactory
                .getAccountStatementRequest(sessionToken, startDate, endDate, startRecordPosition, recordCount);

        GetAccountStatementResp response = betfairExchangeService.getAccountStatement(request);

        if(GetAccountStatementErrorEnum.OK == response.getErrorCode()) {
            ArrayOfAccountStatementItem items = response.getItems();
            if(items != null) {
                ObjectTypeMapper<AccountStatementItem,AccountStatementRecord> mapper =
                    typeMapperManager.getMapper(AccountStatementItem.class, AccountStatementRecord.class);

                return FunctionalIterables.make(items.getAccountStatementItem()).map(mapper.mapFunction()).toList();
            }
        }

        return new ArrayList<AccountStatementRecord>();
    }
}
