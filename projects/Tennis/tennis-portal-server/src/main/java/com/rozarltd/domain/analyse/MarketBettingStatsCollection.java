package com.rozarltd.domain.analyse;

import com.rozarltd.entity.analyse.MarketBettingStats;
import org.apache.commons.lang.time.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MarketBettingStatsCollection {
    private List<MarketBettingStats> items;
    private Map<Integer, Double> profitByBetCount = null;
    private Map<Integer, Integer> countByBetCount = null;

    public MarketBettingStatsCollection(List<MarketBettingStats> items) {
        this.items = items;
        Collections.sort(this.items, new MarketBettingStats.DateComparator());
    }

    public List<MarketBettingStats> getItems() {
        return items;
    }

    public int getMarketCount() {
        return isEmpty() ? 0 : items.size();
    }

    public boolean isEmpty() {
        return items == null || items.isEmpty();
    }

    public int getWinningMarketCount() {
        int result = 0;
        for(MarketBettingStats marketStats: items) {
            if(marketStats.isWinningMarket()) {
                result++;
            }
        }
        return result;
    }

    public MarketBettingStatsCollection getLatestDayMarketStats() {
        List<MarketBettingStats> stats = new ArrayList<MarketBettingStats>();
        Date latestDate = null;
        for(MarketBettingStats marketStats: items) {
            if(stats.isEmpty()) {
                latestDate = DateUtils.truncate(marketStats.getDate(), Calendar.DATE);
                stats.add(marketStats);
            } else {
                if(marketStats.getDate().after(latestDate)) {
                    Date truncatedDate = DateUtils.truncate(marketStats.getDate(), Calendar.DATE);
                    if(truncatedDate.equals(latestDate)) {
                        stats.add(marketStats);
                    } else if(truncatedDate.after(latestDate)) {
                        latestDate = truncatedDate;
                        stats = new ArrayList<MarketBettingStats>();
                        stats.add(marketStats);
                    } else {
                        throw new IllegalStateException("should never happen");
                    }
                }
            }
        }

        return new MarketBettingStatsCollection(stats);
    }

    public int getLosingMarketCount() {
        int result = 0;
        for(MarketBettingStats marketStats: items) {
            if(!marketStats.isWinningMarket()) {
                result++;
            }
        }
        return result;
    }

    public Map<Integer, Double> getProfitByBetCount() {
        if(profitByBetCount == null) {

            profitByBetCount = new TreeMap<Integer, Double>();
            for(MarketBettingStats marketStats: items) {
                Double currentProfit = profitByBetCount.get(marketStats.getBetCount());
                if(currentProfit == null) {
                    currentProfit = 0d;
                }

                profitByBetCount.put(marketStats.getBetCount(), currentProfit + marketStats.getProfit());
            }
        }

        return profitByBetCount;
    }

    public Map<Integer, Integer> getCountByBetCount() {
        if(countByBetCount == null) {

            countByBetCount = new TreeMap<Integer, Integer>();
            for(MarketBettingStats marketStats: items) {
                Integer currentProfit = countByBetCount.get(marketStats.getBetCount());
                if(currentProfit == null) {
                    currentProfit = 0;
                }

                countByBetCount.put(marketStats.getBetCount(), currentProfit + 1);
            }
        }

        return countByBetCount;
    }

    public double getTotalProfitForWinningInitialBet() {
        double profit = 0;
        for(MarketBettingStats marketStats: items) {
            if(marketStats.isWinningInitialBet() && marketStats.getProfit() > 0) {
                profit += marketStats.getProfit();
            }
        }

        return profit;
    }

    public double getTotalLostForWinningInitialBet() {
        double lost = 0;
        for(MarketBettingStats marketStats: items) {
            if(marketStats.isWinningInitialBet() && marketStats.getProfit() < 0) {
                lost += marketStats.getProfit();
            }
        }
        return lost;
    }

    public double getTotalProfitForLosingInitialBet() {
        double profit = 0;
        for(MarketBettingStats marketStats: items) {
            if(!marketStats.isWinningInitialBet() && marketStats.getProfit() > 0) {
                profit += marketStats.getProfit();
            }
        }

        return profit;
    }

    public double getTotalLostForLosingInitialBet() {
        double lost = 0;
        for(MarketBettingStats marketStats: items) {
            if(!marketStats.isWinningInitialBet() && marketStats.getProfit() < 0) {
                lost += marketStats.getProfit();
            }
        }
        return lost;
    }

    public int getBetCountForWinningFirstBet() {
        int count = 0;
        for(MarketBettingStats marketStats: items) {
            if(marketStats.isWinningInitialBet()) {
                count++;
            }
        }

        return count;
    }

    public int getBetCountForLosingFirstBet() {
        int count = 0;
        for(MarketBettingStats marketStats: items) {
            if(!marketStats.isWinningInitialBet()) {
                count++;
            }
        }

        return count;
    }

    public int getWinningBetCount() {
        int count = 0;
        for(MarketBettingStats marketStats: items) {
            if(marketStats.getProfit() > 0) {
                count++;
            }
        }

        return count;
    }

    public int getLosingBetCount() {
        int count = 0;
        for(MarketBettingStats marketStats: items) {
            if(marketStats.getProfit() < 0) {
                count++;
            }
        }

        return count;
    }
}
