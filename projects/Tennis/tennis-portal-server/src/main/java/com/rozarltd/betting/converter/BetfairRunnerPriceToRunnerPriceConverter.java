package com.rozarltd.betting.converter;

import com.rozarltd.module.betfairapi.domain.market.BetfairRunnerPrice;
import com.rozarltd.domain.market.RunnerPrice;
import org.springframework.core.convert.converter.Converter;

public class BetfairRunnerPriceToRunnerPriceConverter implements Converter<BetfairRunnerPrice, RunnerPrice> {

    @Override
    public RunnerPrice convert(BetfairRunnerPrice source) {
        double amount = source.isBackPrice() ? source.getTotalAvailableToBack() : source.getTotalAvailableToLay();

        return new RunnerPrice(source.getPrice(), amount);
    }
}
