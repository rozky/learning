package com.rozarltd.betting.functional;

import com.googlecode.totallylazy.Callable1;
import com.rozarltd.domain.market.MarketAdapter;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import org.springframework.core.convert.ConversionService;

public class ConvertBetfairMarketToMarketAdapter implements Callable1<BetfairMarket, MarketAdapter> {
    private ConversionService conversionService;

    public ConvertBetfairMarketToMarketAdapter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public MarketAdapter call(BetfairMarket betfairMarket) throws Exception {
        return conversionService.convert(betfairMarket, MarketAdapter.class);
    }
}
