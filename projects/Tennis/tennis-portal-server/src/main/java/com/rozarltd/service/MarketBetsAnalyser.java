package com.rozarltd.service;

import com.rozarltd.entity.analyse.MarketBettingStats;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketBetsAnalyser implements BetsAnalyser<MarketBettingStats> {

    @Override
    public List<MarketBettingStats> analyse(List<AccountStatementRecord> bets) {
        Map<Long, MarketBettingStats.Builder> builders = new HashMap<Long, MarketBettingStats.Builder>();
        List<MarketBettingStats> result = new ArrayList<MarketBettingStats>();

        if(bets != null && !bets.isEmpty()) {
            for(AccountStatementRecord bet: bets) {
                MarketBettingStats.Builder builder = getBuilder(builders, bet);

                builder.addBet(bet);
            }

            // todo - do i want to order it ?
            for(MarketBettingStats.Builder builder: builders.values()) {
                result.add(builder.build());
            }
        }

        return result;
    }

    private MarketBettingStats.Builder getBuilder(Map<Long, MarketBettingStats.Builder> builders, AccountStatementRecord bet) {
        MarketBettingStats.Builder builder = builders.get(bet.getEventId());
        if(builder == null) {
            builder = new MarketBettingStats.Builder();
            builders.put(bet.getEventId(), builder);
        }
        return builder;
    }
}
