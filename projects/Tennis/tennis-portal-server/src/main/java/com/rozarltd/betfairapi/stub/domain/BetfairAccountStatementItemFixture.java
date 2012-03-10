package com.rozarltd.betfairapi.stub.domain;

import com.rozarltd.betfairapi.domain.account.BetfairAccountStatementItem;

public class BetfairAccountStatementItemFixture {
    public static BetfairAccountStatementItem create() {
        BetfairAccountStatementItem item = new BetfairAccountStatementItem();
        item.setBetId(123456789);
        item.setBetType("B"); // L
        item.setEventId(104589243);
        item.setEventTypeId(1);
        item.setAmount(300);
        item.setAccountBalance(3500.50);
        item.setGrossBetAmount(65.5);
        item.setMarketName("Nadal vs Federer");
        item.setPlacedAt(1326048179000L);
        item.setPrice(12.00); // 0.0
        item.setSelectionId(11);
        item.setSelectionName("Nadal");
        item.setSettledAt(1326067179000L);
        item.setStake(80.0);
        item.setTransactionType("ACCOUNT_CREDIT"); // ACCOUNT_DEBIT
        item.setWinLost("RESULT_WON"); // RESULT_NOT_APPLICABLE

        return item;
    }
}
