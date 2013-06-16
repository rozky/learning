package com.rozarltd.betting.converter;

import com.rozarltd.domain.market.MarketAdapter;
import com.rozarltd.domain.market.MarketRunner;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.domain.market.BetfairRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

// todo - I will replace it with BetfairMarketToMarketAdapterConverter
public class BetfairMarketToMarketAdapterConverter implements Converter<BetfairMarket, MarketAdapter> {
    private ConversionService conversionService;

    public BetfairMarketToMarketAdapterConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public MarketAdapter convert(BetfairMarket source) {

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

        MarketAdapter marketAdapter = new MarketAdapter(source.getMarketId(), source.getMarketName(), targetRunners);
        marketAdapter.setStartAtDate(source.getStartAtDate());
        marketAdapter.setBetfairMarket(source);
        return marketAdapter;
    }
}
