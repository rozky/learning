package com.rozarltd.betting.functional;

import com.google.inject.Inject;
import com.googlecode.totallylazy.Callable1;
import com.rozarltd.domain.market.MarketAdapter;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.service.BFRestApiFacade;
import org.springframework.core.convert.ConversionService;

public class BetfairMarketFunctions {
    private BFRestApiFacade restApiFacade;
    private ConversionService conversionService;

    @Inject
    public BetfairMarketFunctions(BFRestApiFacade restApiFacade, ConversionService conversionService) {
        this.restApiFacade = restApiFacade;
        this.conversionService = conversionService;
    }

    public Callable1<BetfairMarket, BetfairMarket> addMarketPrices() {
        return new AddMarketPrices(restApiFacade);
    }

    public Callable1<BetfairMarket, MarketAdapter> convertToMarketAdapter() {
        return new ConvertBetfairMarketToMarketAdapter(conversionService);
    }
}
