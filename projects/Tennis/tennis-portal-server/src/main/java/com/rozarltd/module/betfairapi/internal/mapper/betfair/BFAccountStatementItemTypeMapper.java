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
        AccountStatementRecord record = new AccountStatementRecord();
        record.setEventId(item.getEventId());
        record.setEventTypeId(item.getEventTypeId());
        record.setSelectionId(item.getSelectionId());
        record.setSelectionName(item.getSelectionName());
        record.setBetId(item.getBetId());
        record.setAmount(item.getAmount());
        record.setGrossBetAmount(item.getGrossBetAmount());
        record.setPrice(item.getAvgPrice());
        record.setStake(item.getBetSize());
        record.setBetType(EnumUtils.safeValueOf(item.getBetType(), BetType.class));
        record.setMarketName(item.getMarketName());
        record.setFullMarketName(item.getFullMarketName());
        record.setWinLost(getRecordType(item));
        record.setAccountBalance(item.getAccountBalance());
        record.setTransactionType(EnumUtils.safeValueOf(item.getTransactionType(), TransactionType.class));
        record.setPlacedAt(item.getPlacedDate().getTime().getTime());
        record.setSettledAt(item.getSettledDate().getTime().getTime());
        record.setId(record.getSettledAt() + ":" + record.getBetId() + ":" + record.getWinLost());

        return record;
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
