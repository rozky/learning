package com.rozarltd.betting.portal.tennis.web.betfair.viewadapter;

import com.google.common.base.Function;
import com.googlecode.functionalcollections.FunctionalIterables;
import com.rozarltd.module.betfairapi.domain.market.BetfairRunner;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RunnerViewAdapter {
    private BetfairRunner runner;

    public static List<RunnerViewAdapter> transform(List<BetfairRunner> runners) {
        if(runners != null && runners.size() > 0) {
            return FunctionalIterables.make(runners).transform(new BetfairRunnerTransformFunction()).toList();
        }

        return new ArrayList<RunnerViewAdapter>();
    }

    public RunnerViewAdapter(BetfairRunner runner) {
        this.runner = runner;
    }

    public String runnerId() {
        return String.valueOf(runner.getRunnerId());
    }

    public String getRunnerName() {
        return runner.getRunnerName() != null ? runner.getRunnerName() : String.valueOf(runner.getRunnerId());
    }

    public double getBestBackPrice() {
        return runner.getBestBackPrice() != null ? runner.getBestBackPrice().getPrice() : 0d;
    }

    private static class BetfairRunnerTransformFunction implements Function<BetfairRunner, RunnerViewAdapter> {
        @Override
        public RunnerViewAdapter apply(@Nullable BetfairRunner runner) {
            return new RunnerViewAdapter(runner);
        }
    }
}
