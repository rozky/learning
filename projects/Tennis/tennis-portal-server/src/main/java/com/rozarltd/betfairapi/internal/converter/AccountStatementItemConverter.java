package com.rozarltd.betfairapi.internal.converter;


import com.betfair.publicapi.exchange.types.AccountStatementItem;
import com.google.common.base.Function;
import com.rozarltd.betfairapi.domain.account.BetfairAccountStatementItem;
import com.rozarltd.util.EnumUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountStatementItemConverter implements Function<AccountStatementItem, BetfairAccountStatementItem> {

    @Override
    public BetfairAccountStatementItem apply(AccountStatementItem item) {
        BetfairAccountStatementItem document = null;
        if (item != null) {
            document = new BetfairAccountStatementItem();
            document.setEventId(item.getEventId());
            document.setEventTypeId(item.getEventTypeId());
            document.setSelectionId(item.getSelectionId());
            document.setSelectionName(item.getSelectionName());
            document.setBetId(item.getBetId());
            document.setAmount(item.getAmount());
            document.setGrossBetAmount(item.getGrossBetAmount());
            document.setPrice(item.getAvgPrice());
            document.setStake(item.getBetSize());
            document.setBetType(EnumUtils.asString(item.getBetType()));
            document.setMarketName(item.getFullMarketName());
            document.setWinLost(EnumUtils.asString(item.getWinLose()));
            document.setAccountBalance(item.getAccountBalance());
            document.setTransactionType(EnumUtils.asString(item.getTransactionType()));
            document.setPlacedAt(item.getPlacedDate().getTime().getTime());
            document.setSettledAt(item.getStartDate().getTime().getTime());
        }

        return document;
    }
}
