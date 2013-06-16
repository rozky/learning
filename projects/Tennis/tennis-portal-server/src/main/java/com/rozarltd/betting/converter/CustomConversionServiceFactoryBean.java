package com.rozarltd.betting.converter;

import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;

public class CustomConversionServiceFactoryBean extends ConversionServiceFactoryBean {

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        ConversionService conversionService = this.getObject();
        ConverterRegistry converterRegistry = (ConverterRegistry) conversionService;

        converterRegistry.addConverter(new BetfairMarketToMarketAdapterConverter(conversionService));
        converterRegistry.addConverter(new BetfairRunnerToMarketRunnerConverter(conversionService));
        converterRegistry.addConverter(new BetfairRunnerPriceToRunnerPriceConverter());
    }
}
