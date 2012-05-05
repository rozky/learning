package com.rozarltd.betting.service;


import com.betfair.publicapi.exchange.types.AccountStatementEnum;
import com.betfair.publicapi.exchange.types.AccountStatementItem;
import com.rozarltd.module.betfairapi.service.AccountWalletService;
import com.rozarltd.betting.domain.account.AccountStatementRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountFinanceService {
    private AccountWalletService accountWalletService;

    @Autowired
    public AccountFinanceService(AccountWalletService accountWalletService) {
        this.accountWalletService = accountWalletService;
    }

    public List<AccountStatementRecord> getAccountStatement(int startRecordPosition, int recordCount) {
        List<AccountStatementItem> betfairStatement = accountWalletService.getMainWalletStatement(startRecordPosition, recordCount);
        List<AccountStatementRecord> resultAccountStatement = new ArrayList<AccountStatementRecord>();
        AccountStatementRecord currentRecord = null;

        if(betfairStatement != null && betfairStatement.size() > 0) {
            for (AccountStatementItem item: betfairStatement) {
                if(AccountStatementEnum.RESULT_NOT_APPLICABLE.equals(item.getWinLose())) {
                    resultAccountStatement.add(createStatementRecordForWinningBet(item));
                    currentRecord = null;
                }
                else if(AccountStatementEnum.RESULT_LOST.equals(item.getWinLose())) {
                    if(currentRecord == null) {
                        currentRecord = createStatementRecordForLosingBet(item);
                    }
                    else if(currentRecord.getEventId() == item.getEventId()) {
                        merge(currentRecord, item);
                    }
                    else {
                        resultAccountStatement.add(currentRecord);
                        currentRecord = createStatementRecordForLosingBet(item);
                    }

                }
                else if(AccountStatementEnum.RESULT_WON.equals(item.getWinLose())) {
                    currentRecord = null;
                }
            }
        }
        return resultAccountStatement;
    }

    private AccountStatementRecord createStatementRecordForWinningBet(AccountStatementItem item) {
        AccountStatementRecord record = new AccountStatementRecord();
        record.setEventId(item.getEventId());
        record.setMarketName(item.getFullMarketName());
        record.setAmount(item.getGrossBetAmount());
        // todo - calculate
        // record.setStake();
        record.setAccountBalance(item.getAccountBalance());

        return record;
    }


    private AccountStatementRecord createStatementRecordForLosingBet(AccountStatementItem item) {
        AccountStatementRecord record = new AccountStatementRecord();
        record.setEventId(item.getEventId());
        record.setMarketName(item.getFullMarketName());
        record.setAmount(item.getAmount());
        record.setStake(item.getBetSize());
        record.setAccountBalance(item.getAccountBalance());

        return record;
    }

    private AccountStatementRecord merge(AccountStatementRecord record, AccountStatementItem item) {
        return record;
    }
}
