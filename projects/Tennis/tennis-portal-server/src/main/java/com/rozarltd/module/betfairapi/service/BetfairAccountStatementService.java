package com.rozarltd.module.betfairapi.service;


import com.betfair.publicapi.exchange.BFExchangeService;
import com.betfair.publicapi.exchange.types.AccountStatementItem;
import com.betfair.publicapi.global.BFGlobalService_Service;
import com.google.inject.Inject;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.module.betfairapi.internal.factory.BFExchangeAPIRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BetfairAccountStatementService implements AccountWalletService {

    private final BFExchangeService betfairExchangeService;
    private final BFExchangeAPIRequestFactory requestFactory;


    @Autowired
    @Inject
    public BetfairAccountStatementService(BFExchangeService betfairExchangeService,
                                          BFExchangeAPIRequestFactory requestFactory) {
        this.betfairExchangeService = betfairExchangeService;
        this.requestFactory = requestFactory;

        new BFGlobalService_Service().getBFGlobalService();
    }


    public List<AccountStatementRecord> getStatement(int startRecordPosition, int recordCount) {
//        GetAccountStatementReq request = requestFactory.getAccountStatementRequest(startRecordPosition, recordCount);
//        GetAccountStatementResp response = betfairExchangeService.getAccountStatement(request);
//        if(GetAccountStatementErrorEnum.OK == response.getErrorCode()) {
//            ArrayOfAccountStatementItem items = response.getItems();
//            if(items != null) {
//                return FunctionalIterables.make(items.getAccountStatementItem()).map(accountStatementItemConverter).toList();
//            }
//        }
        return new ArrayList<AccountStatementRecord>();
    }

    public List<AccountStatementItem> getMainWalletStatement(int startRecordPosition, int recordCount) {
//        GetAccountStatementReq request = requestFactory.getAccountStatementRequest(startRecordPosition, recordCount);
//        GetAccountStatementResp response = betfairExchangeService.getAccountStatement(request);
//        if(GetAccountStatementErrorEnum.OK == response.getErrorCode()) {
//            ArrayOfAccountStatementItem items = response.getItems();
//            if(items != null && items.getAccountStatementItem() != null) {
//                return items.getAccountStatementItem();
//            }
//        }
        return new ArrayList<AccountStatementItem>();
    }
}
