package com.rozarltd.entity.analyse;

import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.module.betfairapi.domain.account.statement.StatementRecordType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketBettingStats {
    private String marketName;
    private String finalSelectionName;
    private String initialSelectionName;
    private Double initialStake;
    private double initialBetProfit;
    private double finalStake;
    private Double maxExposure;
    private double profit;
    private int betCount;
    private Date date; //
    private String sport;
    private String marketType;
    private long eventId;
    private double totalStake;

    public double getTotalStake() {
        return totalStake;
    }

    public long getEventId() {
        return eventId;
    }

    public double getInitialStake() {
        return initialStake;
    }

    public double getFinalStake() {
        return finalStake;
    }

    public double getProfit() {
        return profit;
    }

    public int getBetCount() {
        return betCount;
    }

    public Date getDate() {
        return date;
    }

    public String getMarketName() {
        return marketName;
    }

    public String getSport() {
        return sport;
    }

    public String getMarketType() {
        return marketType;
    }

    public String getFinalSelectionName() {
        return finalSelectionName;
    }

    public String getInitialSelectionName() {
        return initialSelectionName;
    }

    public boolean isWinningInitialBet() {
        return initialBetProfit > 0;
    }

    public boolean isWinningMarket() {
        return profit > 0;
    }

    public double getReturnOfInvestment() {
        return totalStake > 0 ? profit / totalStake : 0;
    }

    // initial selection
    // winning selection
    // selection count
    // selection changed count


    public static class Builder {
        private static final Comparator<AccountStatementRecord> comparator = new AccountStatementRecord.PlacedDateComparator();
//        private Set<AccountStatementRecord> bets = new TreeSet<AccountStatementRecord>(comparator);
        private List<AccountStatementRecord> bets = new ArrayList<AccountStatementRecord>();
        private MarketBettingStats result = new MarketBettingStats();

        public Builder addBet(AccountStatementRecord statementRecord) {
            bets.add(statementRecord);
            result.date = new Date(statementRecord.getPlacedAt());
            result.marketName = statementRecord.getFullMarketName();
            result.eventId = statementRecord.getEventId();
            if(statementRecord.isBet()) {
                result.betCount++;
            }

            return this;
        }

        public MarketBettingStats build() {
            Collections.sort(bets, comparator);
            Map<String, Double> profitBySelection = new HashMap<String, Double>();
            for (AccountStatementRecord bet: bets) {
                result.totalStake += bet.getLiability();
                updateInitialBetIfApplicable(bet);
                updateProfit(bet);

                if(StatementRecordType.RESULT_WON.equals(bet.getWinLost())
                        || StatementRecordType.RESULT_LOST.equals(bet.getWinLost())) {

                    if(!profitBySelection.containsKey(bet.getSelectionName())) {
                        profitBySelection.put(bet.getSelectionName(), Math.abs(bet.getLiability()));
                    } else {
                        profitBySelection.put(bet.getSelectionName(), profitBySelection.get(bet.getSelectionName()) + Math.abs(bet.getLiability()));
                    }
                }
            }

            result.finalSelectionName = findFinalSelectionName(profitBySelection);

            return result;
        }

        private void updateInitialBetIfApplicable(AccountStatementRecord bet) {
            if(result.initialStake == null) {
                result.initialBetProfit = bet.getAmount();
                result.initialStake = bet.getLiability();
                result.initialSelectionName = bet.getSelectionName();
            }
        }

        private void updateProfit(AccountStatementRecord bet) {
            if(StatementRecordType.RESULT_WON.equals(bet.getWinLost())
                    || StatementRecordType.RESULT_LOST.equals(bet.getWinLost())
                    || StatementRecordType.COMMISSION.equals(bet.getWinLost())) {
                result.profit += bet.getAmount();
            }
        }

        private String findFinalSelectionName(Map<String, Double> profitBySelection) {
            Map.Entry<String,Double> finalSelection = null;
            for(Map.Entry<String,Double> entry: profitBySelection.entrySet()) {
                if(finalSelection == null) {
                    finalSelection = entry;
                } else if(finalSelection.getValue() < entry.getValue()) {
                    finalSelection = entry;
                }
            }

            return finalSelection != null ? finalSelection.getKey() : null;
        }
    }

    public static class DateComparator implements Comparator<MarketBettingStats> {

        @Override
        public int compare(MarketBettingStats left, MarketBettingStats right) {
            return -1 * left.getDate().compareTo(right.getDate());
        }
    }
}
