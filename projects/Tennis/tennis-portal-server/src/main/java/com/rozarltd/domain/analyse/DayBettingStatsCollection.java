package com.rozarltd.domain.analyse;

import com.rozarltd.domain.KeyValue;
import com.rozarltd.entity.analyse.DayBettingStats;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayBettingStatsCollection {
    private List<DayBettingStats> items;
    private DayBettingStats latestDayStats;

    // populated by analyseData method
    private int totalDays;
    private int profitableDays;
    private int betCount;
    private int wonBetCount;
    private int lostBetCount;
    private double totalProfit;
    private double totalStake;
    private double totalWonAmount;
    private double totalLostAmount;

    /**
     * Max profit produced on a single day
     */
    private double maxProfit;

    /**
     * Max lost produced on a single day
     */
    private double maxLost;

    /**
     * Max total lost calculated only on lost bets on a single day
     */
    private double maxLostAmount = -1;

    public DayBettingStatsCollection(List<DayBettingStats> items) {
        this.items = items;
        analyseData();
    }

    public List<DayBettingStats> getItems() {
        return items;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public int getProfitableDays() {
        return profitableDays;
    }

    public DayBettingStats getLatestDayStats() {
        if(latestDayStats != null) {
            return latestDayStats;
        }

        if(!isEmpty()) {
            for (DayBettingStats dayStats : items) {
                if(latestDayStats == null) {
                    latestDayStats = dayStats;
                } else if(dayStats.getDate().after(latestDayStats.getDate())) {
                    latestDayStats = dayStats;
                }
            }
        } else {
            latestDayStats = new DayBettingStats();
        }

        return latestDayStats;
    }

    public double getWonAmountTotal() {
        return totalWonAmount;
    }

    public int getBetCount() {
        return betCount;
    }

    public double getLostAmountTotal() {
        return totalLostAmount;
    }

    public double getCommissionAmountTotal() {
        double result = 0;
        if(!isEmpty()) {
            for (DayBettingStats dayStats : items) {
                result += dayStats.getCommission();
            }
        }

        return result;
    }

    public double getStakeAmountTotal() {
        return totalStake;
    }

    public double getWonAmountMax() {
        double result = 0;
        if(!isEmpty()) {
            for (DayBettingStats dayStats : items) {
                if(dayStats.getWon() > result) {
                    result = dayStats.getWon();
                }
            }
        }

        return result;
    }

    public double getLostAmountMax() {
        return maxLostAmount;
    }

    public double getProfitTotal() {
        return totalProfit;
    }

    public double getReturnOfInvestment() {
        return isEmpty() ? 0 : totalProfit / totalStake;
    }

    public int getDaysInProfit() {
        return profitableDays;
    }

    public int getDaysInLost() {
        return totalDays - profitableDays;
    }


    public double getProfitMax() {
        return maxProfit;
    }

    public double getLostMax() {
        return Math.abs(maxLost);
    }

    public int getWonBetCount() {
        return wonBetCount;
    }

    public int getLostBetCount() {
        return lostBetCount;
    }

    public List<KeyValue<Date, Double>> getProfitEvolution() {
        List<KeyValue<Date, Double>> result = new ArrayList<KeyValue<Date, Double>>();
        double currentProfit = 0;
        if(!isEmpty()) {
            for (DayBettingStats dayStats : items) {
                currentProfit += dayStats.getProfit();
                result.add(new KeyValue<Date, Double>(dayStats.getDate(), currentProfit));
            }
        }

        return result;
    }

    public List<KeyValue<Date, Integer>> getDaysInProfitEvolution() {
        List<KeyValue<Date, Integer>> result = new ArrayList<KeyValue<Date, Integer>>();
        double currentProfit = 0;
        int wonCount = 0;
        int lostCount = 0;
        if(!isEmpty()) {
            for (DayBettingStats dayStats : items) {
                if(dayStats.getProfit() > 0) {
                    wonCount++;
                } else if (dayStats.getProfit() < 0) {
                    lostCount++;
                }
                currentProfit += dayStats.getProfit();
                result.add(new KeyValue<Date, Integer>(dayStats.getDate(), (wonCount * 100) / (wonCount + lostCount) ));
            }
        }

        return result;
    }

    private void analyseData() {
        if(!isEmpty()) {
            totalDays = items.size();
            for (DayBettingStats dayStats : items) {
                totalStake += dayStats.getStake();
                totalWonAmount += dayStats.getWon();
                totalLostAmount += dayStats.getLost();
                betCount += dayStats.getBetCount();
                totalProfit += dayStats.getProfit();
                wonBetCount += dayStats.getWonBetCount();
                lostBetCount += dayStats.getLostBetCount();
                updateProfitableDays(dayStats);
                updateMaxProfit(dayStats);
                updateMaxLost(dayStats);
                updateMaxLostAmount(dayStats);
            }
        }
    }

    private void updateProfitableDays(DayBettingStats dayStats) {
        if(dayStats != null && dayStats.getProfit() > 0) {
            profitableDays++;
        }
    }

    private void updateMaxProfit(DayBettingStats dayStats) {
        if(dayStats.getProfit() > maxProfit) {
            maxProfit = dayStats.getProfit();
        }
    }

    public void updateMaxLost(DayBettingStats dayStats) {
        if(dayStats.getProfit() < maxLost) {
            maxLost = dayStats.getProfit();
        }
    }

    public void updateMaxLostAmount(DayBettingStats dayStats) {
        if(dayStats.getLost() > maxLostAmount) {
            maxLostAmount = dayStats.getLost();
        }
    }

    private boolean isEmpty() {
        return items == null || items.isEmpty();
    }
}
