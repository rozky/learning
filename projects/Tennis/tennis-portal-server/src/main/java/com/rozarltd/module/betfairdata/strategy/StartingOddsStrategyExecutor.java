package com.rozarltd.module.betfairdata.strategy;

import com.rozarltd.module.betfairdata.repository.MarketDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.List;

@Component
public class StartingOddsStrategyExecutor {

    private MarketDataRepository marketDataRepository;

    @Autowired
    public StartingOddsStrategyExecutor(MarketDataRepository marketDataRepository) {
        this.marketDataRepository = marketDataRepository;
    }

    public void execute() {
        StopWatch watch = new StopWatch();
        watch.start();
        List data = (List) marketDataRepository.findAll();
        watch.stop();
        System.out.println("Loading time: " + watch.getTotalTimeSeconds());

        for (float maxOdds = 1.1f; maxOdds < 2.01; maxOdds += 0.1) {
            Strategy bettingStrategy = new StartingOddsStrategy(maxOdds);
            BettingStrategyResult strategyResult = new StrategyAnalyser().analyse(data, bettingStrategy);
            System.out.println("result: " + strategyResult);
        }
    }
}
