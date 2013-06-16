package com.rozarltd.module.betfairapi.domain.account.statement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Comparator;
import java.util.Date;

@Entity
@Table(name = "ACCOUNT_STATEMENT_RECORD")
public class AccountStatementRecord {
    @Id
    private String id;

    @Column(name = "EVENT_ID")
    private int eventId;

    @Column(name = "EVENT_TYPE_ID")
    private int eventTypeId;

    @Column(name = "BET_ID")
    private long betId;

    @Column(name = "FULL_MARKET_NAME")
    private String fullMarketName;

    @Column(name = "MARKET_NAME")
    private String marketName;

    @Column(name = "SELECTION_ID")
    private int selectionId;

    @Column(name = "SELECTION_NAME")
    private String selectionName;
    private double stake;
    private double price;
    private double amount;

    @Column(name = "GROSS_BET_AMOUNT")
    private double grossBetAmount;

    @Column(name = "ACCOUNT_BALANCE")
    private double accountBalance;

    @Column(name = "WIN_LOST")
    @Enumerated(value = EnumType.STRING)
    private StatementRecordType winLost;

    @Column(name = "BET_TYPE")
    @Enumerated(value = EnumType.STRING)
    private BetType betType;

    @Column(name = "TRANSACTION_TYPE")
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "PLACED_AT")
    private long placedAt;

    @Column(name = "SETTLED_AT")
    private long settledAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCommissionRecord() {
        return StatementRecordType.COMMISSION.equals(winLost);
    }

    public boolean isBet() {
        return StatementRecordType.RESULT_LOST.equals(winLost) || StatementRecordType.RESULT_WON.equals(winLost);
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public long getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public long getBetId() {
        return betId;
    }

    public void setBetId(long betId) {
        this.betId = betId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public int getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(int selectionId) {
        this.selectionId = selectionId;
    }

    public String getSelectionName() {
        return selectionName;
    }

    public void setSelectionName(String selectionName) {
        this.selectionName = selectionName;
    }

    public double getStake() {
        return stake;
    }

    public double getLiability() {
        if(BetType.B.equals(betType)) {
            return stake;
        } else if(BetType.L.equals(betType)) {
            return stake * price;
        }

        return 0;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getGrossBetAmount() {
        return grossBetAmount;
    }

    public void setGrossBetAmount(double grossBetAmount) {
        this.grossBetAmount = grossBetAmount;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BetType getBetType() {
        return betType;
    }

    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public long getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(long placedAt) {
        this.placedAt = placedAt;
    }

    public long getSettledAt() {
        return settledAt;
    }

    public void setSettledAt(long settledAt) {
        this.settledAt = settledAt;
    }

    public StatementRecordType getWinLost() {
        return winLost;
    }

    public void setWinLost(StatementRecordType winLost) {
        this.winLost = winLost;
    }

    public String getFullMarketName() {
        return fullMarketName;
    }

    public void setFullMarketName(String fullMarketName) {
        this.fullMarketName = fullMarketName;
    }

    public Double getStakeIfApplicable() {
        return StatementRecordType.RESULT_LOST.equals(winLost) || StatementRecordType.RESULT_WON.equals(winLost) ? stake : null;
    }

    public Double getWonAmountIfApplicable() {
        return StatementRecordType.RESULT_WON.equals(winLost) ? Math.abs(amount) : null;
    }

    public Double getLostAmountIfApplicable() {
        return StatementRecordType.RESULT_LOST.equals(winLost) ? Math.abs(amount) : null;
    }

    public Double getCommissionAmountIfApplicable() {
        return StatementRecordType.COMMISSION.equals(winLost) ? Math.abs(amount) : null;
    }

    public Double getDepositAmountIfApplicable() {
        return StatementRecordType.DEPOSIT.equals(winLost) ? Math.abs(amount) : null;
    }

    public Double getWithdrawalAmountIfApplicable() {
        return StatementRecordType.WITHDRAWAL.equals(winLost) ? Math.abs(amount) : null;
    }

    /**
     * The builder for the {@link AccountStatementRecord}
     */
    public static class Builder {
        private AccountStatementRecord record = new AccountStatementRecord();

        public Builder id(String id) {
            record.setId(id);
            return this;
        }

        public Builder betId(long id) {
            record.setBetId(id);
            return this;
        }

        public Builder settledDate(Date date) {
            record.setSettledAt(date.getTime());
            return this;
        }

        public AccountStatementRecord build() {
            return record;
        }
    }

    /**
     * Compare two instance of the {@link AccountStatementRecord} by comparing their placed data.
     * Order is ascending while records with null dates are placed at the end.
     */
    public static class PlacedDateComparator implements Comparator<AccountStatementRecord> {

        @Override
        public int compare(AccountStatementRecord left, AccountStatementRecord right) {
            long leftValue = left != null && left.getPlacedAt() != 0 ? left.getPlacedAt() : Long.MAX_VALUE;
            long rightValue = right != null && right.getPlacedAt() != 0 ? right.getPlacedAt() : Long.MAX_VALUE;
            return leftValue == rightValue ? 0 : (leftValue > rightValue ? 1 : -1);
        }
    }
}
