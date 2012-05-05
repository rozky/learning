package com.rozarltd.betting.converter;

import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.domain.market.BetfairRunner;
import com.rozarltd.domain.market.Market;
import com.rozarltd.domain.market.MarketRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class BetfairMarketToMarketConverter implements Converter<BetfairMarket, Market> {
    private ConversionService conversionService;

    public BetfairMarketToMarketConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Market convert(BetfairMarket source) {

        List<BetfairRunner> sourceRunners = source.getRunners();

        TypeDescriptor sourceTypeDescriptor = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(BetfairRunner.class));
        TypeDescriptor targetTypeDescriptor = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(MarketRunner.class));
        List<MarketRunner> targetRunners = (List<MarketRunner>) conversionService.convert(sourceRunners, sourceTypeDescriptor, targetTypeDescriptor);


//        List<MarketRunner> targetRunners = null;
//        if(sourceRunners != null && !sourceRunners.isEmpty()) {
//            targetRunners = new ArrayList<MarketRunner>();
//            for(BetfairRunner sourceRunner: sourceRunners) {
//                targetRunners.add(conversionService.convert(sourceRunner, MarketRunner.class));
//            }
//        }

        return new Market(source.getMarketId(), source.getMarketName(), targetRunners);
    }
}
