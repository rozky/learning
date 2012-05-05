package com.rozarltd.module.betfairapi.internal.mapper.betfair;

import com.betfair.publicapi.exchange.types.AccountStatementItem;
import com.google.common.base.Function;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.module.betfairapi.domain.account.statement.BetType;
import com.rozarltd.module.betfairapi.domain.account.statement.StatementRecordType;
import com.rozarltd.module.betfairapi.domain.account.statement.TransactionType;
import com.rozarltd.module.betfairapi.internal.mapper.ObjectTypeMapper;
import com.rozarltd.util.EnumUtils;


public class BFAccountStatementItemTypeMapper implements ObjectTypeMapper<AccountStatementItem, AccountStatementRecord>{
    private MapFunction mapFunction = new MapFunction();

    @Override
    public AccountStatementRecord mapFrom(AccountStatementItem item) {
        AccountStatementRecord statementItem = new AccountStatementRecord();
        statementItem.setEventId(item.getEventId());
        statementItem.setEventTypeId(item.getEventTypeId());
        statementItem.setSelectionId(item.getSelectionId());
        statementItem.setSelectionName(item.getSelectionName());
        statementItem.setBetId(item.getBetId());
        statementItem.setAmount(item.getAmount());
        statementItem.setGrossBetAmount(item.getGrossBetAmount());
        statementItem.setPrice(item.getAvgPrice());
        statementItem.setStake(item.getBetSize());
        statementItem.setBetType(EnumUtils.safeValueOf(item.getBetType(), BetType.class));
        statementItem.setMarketName(item.getMarketName());
        statementItem.setFullMarketName(item.getFullMarketName());
        statementItem.setWinLost(getRecordType(item));
        statementItem.setAccountBalance(item.getAccountBalance());
        statementItem.setTransactionType(EnumUtils.safeValueOf(item.getTransactionType(), TransactionType.class));
        statementItem.setPlacedAt(item.getPlacedDate().getTime().getTime());
        statementItem.setSettledAt(item.getSettledDate().getTime().getTime());

        return statementItem;
    }

    private StatementRecordType getRecordType(AccountStatementItem item) {
        StatementRecordType recordType = EnumUtils.safeValueOf(item.getWinLose(), StatementRecordType.class);
        if(StatementRecordType.RESULT_NOT_APPLICABLE.equals(recordType)
                && "DEPOSIT".equalsIgnoreCase(item.getMarketName())) {
            recordType = StatementRecordType.DEPOSIT;
        }
        else if(StatementRecordType.RESULT_NOT_APPLICABLE.equals(recordType)
                && "WITHDRAWAL".equalsIgnoreCase(item.getMarketName())) {
            recordType = StatementRecordType.WITHDRAWAL;
        }
        else if(StatementRecordType.RESULT_NOT_APPLICABLE.equals(recordType)) {
            recordType = StatementRecordType.COMMISSION;
        }

        return recordType;
    }

    @Override
    public Function<AccountStatementItem, AccountStatementRecord> mapFunction() {
        return mapFunction;
    }

    private class MapFunction implements Function<AccountStatementItem, AccountStatementRecord> {

        @Override
        public AccountStatementRecord apply(AccountStatementItem market) {
            return mapFrom(market);
        }
    }
}
