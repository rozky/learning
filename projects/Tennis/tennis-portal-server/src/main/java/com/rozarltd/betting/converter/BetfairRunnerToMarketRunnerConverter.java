package com.rozarltd.betting.converter;

import com.rozarltd.module.betfairapi.domain.market.BetfairRunner;
import com.rozarltd.domain.market.MarketRunner;
import com.rozarltd.domain.market.RunnerPrice;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

public class BetfairRunnerToMarketRunnerConverter implements Converter<BetfairRunner, MarketRunner> {

    private ConversionService conversionService;

    public BetfairRunnerToMarketRunnerConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public MarketRunner convert(BetfairRunner source) {
        RunnerPrice bestBackPrice = conversionService.convert(source.getBestBackPrice(), RunnerPrice.class);
        RunnerPrice bestLayPrice = conversionService.convert(source.getBestLayPrice(), RunnerPrice.class);

        return new MarketRunner(source.getRunnerId(), source.getRunnerName(), bestBackPrice, bestLayPrice);
    }
}
