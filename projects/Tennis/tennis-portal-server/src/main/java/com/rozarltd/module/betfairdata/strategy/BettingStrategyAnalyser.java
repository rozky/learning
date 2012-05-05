package com.rozarltd.module.betfairdata.strategy;

import com.rozarltd.module.betfairdata.parser.BetfairDataRow;

import java.util.List;

public interface BettingStrategyAnalyser {
    public BettingStrategyResult processAndAnalyse(List<BetfairDataRow> data, Strategy strategy);
    public void processAndAnalyse(List<BetfairDataRow> data, Strategy strategy, BettingStrategyResultBuilder resultBuilder);
    public void analyse(List<MarketData> data, Strategy strategy, BettingStrategyResultBuilder resultBuilder);
    public BettingStrategyResult analyse(List<MarketData> data, Strategy strategy);
}
