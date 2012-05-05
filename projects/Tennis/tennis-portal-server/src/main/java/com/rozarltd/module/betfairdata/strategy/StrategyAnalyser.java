package com.rozarltd.module.betfairdata.strategy;

import com.rozarltd.module.betfairdata.parser.BetfairDataRow;

import java.util.Collection;
import java.util.List;

public class StrategyAnalyser implements BettingStrategyAnalyser {

    @Override
    public void analyse(List<MarketData> data, Strategy strategy, BettingStrategyResultBuilder resultBuilder) {

        // apply strategy and build the result
        resultBuilder.setStrategy(strategy);
        resultBuilder.addMarketCount(data.size());
        resultBuilder.addWonMarketCount(getWonCount(data, strategy));
        resultBuilder.addLostMarketCount(getLostCount(data, strategy));
        resultBuilder.addWonAmount(getWonAmount(data, strategy));
    }

    @Override
    public BettingStrategyResult analyse(List<MarketData> data, Strategy strategy) {

        BettingStrategyResultBuilder resultBuilder = new BettingStrategyResultBuilder();

        // apply strategy and build the result
        resultBuilder.setStrategy(strategy);
        resultBuilder.addMarketCount(data.size());
        resultBuilder.addWonMarketCount(getWonCount(data, strategy));
        resultBuilder.addLostMarketCount(getLostCount(data, strategy));
        resultBuilder.addWonAmount(getWonAmount(data, strategy));

        return resultBuilder.buildResult();
    }

    @Override
    public BettingStrategyResult processAndAnalyse(List<BetfairDataRow> data, Strategy strategy) {
        BettingStrategyResultBuilder resultBuilder = new BettingStrategyResultBuilder();

        processAndAnalyse(data, strategy, resultBuilder);

        return resultBuilder.buildResult();
    }

    @Override
    public void processAndAnalyse(List<BetfairDataRow> data, Strategy strategy, BettingStrategyResultBuilder resultBuilder) {
        // aggregate market data
        Collection<MarketData> markets = new BetfairDataProcessor().process(data).values();

        // apply strategy and build the result
        resultBuilder.setStrategy(strategy);
        resultBuilder.addMarketCount(markets.size());
        resultBuilder.addWonMarketCount(getWonCount(markets, strategy));
        resultBuilder.addLostMarketCount(getLostCount(markets, strategy));
        resultBuilder.addWonAmount(getWonAmount(markets, strategy));
    }

    private float getWonAmount(Collection<MarketData> markets, Strategy strategy) {
        float profit = 0f;
        if(markets != null && markets.size() > 0) {
            for(MarketData entry: markets) {
                if(strategy.isBetAllowed(entry) &&
                        strategy.isBetAllowed(entry.getWinningSelection())) {
                    profit += ((strategy.getOdds(entry.getWinningSelection()) - 1));
                }
            }
        }

        return profit;
    }

    private int getWonCount(Collection<MarketData> markets, Strategy strategy) {
        int count = 0;
        if(markets != null && markets.size() > 0) {
            for(MarketData entry: markets) {
                if(strategy.isBetAllowed(entry) &&
                        strategy.isBetAllowed(entry.getWinningSelection())) {
                    count++;
                }
            }
        }

        return count;
    }

    private int getLostCount(Collection<MarketData> markets, Strategy strategy) {
        int count = 0;
        if(markets != null && markets.size() > 0) {
            for(MarketData entry: markets) {
                if(strategy.isBetAllowed(entry) &&
                        strategy.isBetAllowed(entry.getLosingSelection())) {
                    count++;
                }
            }
        }

        return count;
    }
}
