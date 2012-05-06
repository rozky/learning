package com.rozarltd.betting;

import com.rozarltd.module.betfairapi.BetfairPublicApiSpringContext;
import com.rozarltd.module.betfairrestapi.spring.BetfairRestApiStandaloneSpringContext;
import com.rozarltd.betting.converter.CustomConversionServiceFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import({BettingSpringContext.class,
        BetfairRestApiStandaloneSpringContext.class,
        BetfairPublicApiSpringContext.class})
@ImportResource("classpath:/spring/external/data-repository-context.xml")
public class BettingStandaloneSpringContext {

    @Bean
    public CustomConversionServiceFactoryBean conversionService() {
        return new CustomConversionServiceFactoryBean();
    }
}
