package com.rozarltd.betfairapi.service;


import com.betfair.publicapi.exchange.BFExchangeService;
import com.betfair.publicapi.exchange.types.AccountStatementItem;
import com.betfair.publicapi.global.BFGlobalService_Service;
import com.rozarltd.betfairapi.domain.account.BetfairAccountStatementItem;
import com.rozarltd.betfairapi.internal.converter.AccountStatementItemConverter;
import com.rozarltd.betfairapi.internal.factory.BFExchangeAPIRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BetfairAccountStatementService implements AccountWalletService {

    private final BFExchangeService betfairExchangeService;
    private final BFExchangeAPIRequestFactory requestFactory;
    private final AccountStatementItemConverter accountStatementItemConverter;


    @Autowired
    public BetfairAccountStatementService(BFExchangeService betfairExchangeService, BFExchangeAPIRequestFactory requestFactory, AccountStatementItemConverter accountStatementItemConverter) {
        this.betfairExchangeService = betfairExchangeService;
        this.requestFactory = requestFactory;
        this.accountStatementItemConverter = accountStatementItemConverter;

        new BFGlobalService_Service().getBFGlobalService();
    }


    public List<BetfairAccountStatementItem> getStatement(int startRecordPosition, int recordCount) {
//        GetAccountStatementReq request = requestFactory.createGetAccountStatementRequestInstance(startRecordPosition, recordCount);
//        GetAccountStatementResp response = betfairExchangeService.getAccountStatement(request);
//        if(GetAccountStatementErrorEnum.OK == response.getErrorCode()) {
//            ArrayOfAccountStatementItem items = response.getItems();
//            if(items != null) {
//                return FunctionalIterables.make(items.getAccountStatementItem()).map(accountStatementItemConverter).toList();
//            }
//        }
        return new ArrayList<BetfairAccountStatementItem>();
    }

    public List<AccountStatementItem> getMainWalletStatement(int startRecordPosition, int recordCount) {
//        GetAccountStatementReq request = requestFactory.createGetAccountStatementRequestInstance(startRecordPosition, recordCount);
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
