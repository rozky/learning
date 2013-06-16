package com.rozarltd.entity.analyse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DayBettingStats {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private Date date;
    private double profit;
    private double lost;
    private double won;
    private double commission;
    private double startBankBalance;
    private double endBankBalance;
    private double deposit;
    private double withdrawal;
    private double stake;
    private int betCount;
    private int wonBetCount;
    private int lostBetCount;
    private int depositCount;
    private int withdrawalCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getLost() {
        return lost;
    }

    public void setLost(double lost) {
        this.lost = lost;
    }

    public double getWon() {
        return won;
    }

    public void setWon(double won) {
        this.won = won;
    }

    public int getBetCount() {
        return betCount;
    }

    public void setBetCount(int betCount) {
        this.betCount = betCount;
    }

    public double getStartBankBalance() {
        return startBankBalance;
    }

    public void setStartBankBalance(double startBankBalance) {
        this.startBankBalance = startBankBalance;
    }

    public double getEndBankBalance() {
        return endBankBalance;
    }

    public void setEndBankBalance(double endBankBalance) {
        this.endBankBalance = endBankBalance;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(double withdrawal) {
        this.withdrawal = withdrawal;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }

    public int getWonBetCount() {
        return wonBetCount;
    }

    public void setWonBetCount(int wonBetCount) {
        this.wonBetCount = wonBetCount;
    }

    public int getLostBetCount() {
        return lostBetCount;
    }

    public void setLostBetCount(int lostBetCount) {
        this.lostBetCount = lostBetCount;
    }

    public int getDepositCount() {
        return depositCount;
    }

    public void setDepositCount(int depositCount) {
        this.depositCount = depositCount;
    }

    public int getWithdrawalCount() {
        return withdrawalCount;
    }

    public void setWithdrawalCount(int withdrawalCount) {
        this.withdrawalCount = withdrawalCount;
    }

    public double getReturnOfInvestment() {
        return stake > 0 ? profit / stake : 0;
    }

    public static class Builder {
        private DayBettingStats record = new DayBettingStats();

        public Builder date(long date) {
            record.date = new Date(date);
            return this;
        }

        public Builder incrementWonAmount(Double amount) {
            if(amount != null) {
                record.won += amount;
                record.wonBetCount++;
                record.profit += amount;
            }
            return this;
        }

        public Builder incrementLostAmount(Double amount) {
            if(amount != null) {
                record.lost += amount;
                record.lostBetCount++;
                record.profit -= amount;
            }
            return this;
        }

        public Builder incrementCommissionAmount(Double amount) {
            if(amount != null) {
                record.commission += amount;
                record.profit -=amount;
            }
            return this;
        }

        public Builder incrementStake(Double amount) {
            if(amount != null) {
                record.stake += amount;
                record.betCount++;
            }
            return this;
        }

        public Builder incrementWithdrawal(Double amount) {
            if(amount != null) {
                record.withdrawal += amount;
                record.withdrawalCount++;
            }
            return this;
        }

        public Builder incrementDeposit(Double amount) {
            if(amount != null) {
                record.deposit += amount;
                record.depositCount++;
            }
            return this;
        }

        public DayBettingStats build() {
            return record;
        }
    }
}
