package com.rozarltd.domain.analyse;

import com.rozarltd.entity.analyse.DayBettingStats;
import com.rozarltd.entity.analyse.MarketBettingStats;

import java.util.List;

public class BettingStatsCollection {
    private DayBettingStatsCollection statsByDate;
    private MarketBettingStatsCollection statsByMarket;

    public BettingStatsCollection(DayBettingStatsCollection statsByDate,
                                  MarketBettingStatsCollection statsByMarket) {
        this.statsByDate = statsByDate;
        this.statsByMarket = statsByMarket;
    }

    public BettingStatsCollection(List<DayBettingStats> statsByDate,
                                  List<MarketBettingStats> statsByMarket) {
        if(statsByDate != null) {
            this.statsByDate = new DayBettingStatsCollection(statsByDate);
        }

        if(statsByMarket != null) {
            this.statsByMarket = new MarketBettingStatsCollection(statsByMarket);
        }
    }

    public DayBettingStatsCollection getStatsByDate() {
        return statsByDate;
    }

    public MarketBettingStatsCollection getStatsByMarket() {
        return statsByMarket;
    }
}
