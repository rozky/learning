package com.rozarltd;

import com.rozarltd.application.ApplicationProperties;
import com.rozarltd.betting.BettingSpringContext;
import com.rozarltd.betting.converter.CustomConversionServiceFactoryBean;
import com.rozarltd.module.atpwebsite.AtpWebsiteSpringContext;
import com.rozarltd.module.betfairapi.BetfairPublicApiSpringContext;
import com.rozarltd.module.betfairdata.BetfairDataSpringContext;
import com.rozarltd.module.betfairrestapi.spring.BetfairRestApiSpringContext;
import com.rozarltd.module.betfairwebsite.BetfairWebsiteSpringContext;
import com.rozarltd.config.HsqlPersistenceConfig;
import com.rozarltd.module.bettingdata.BettingDataSpringContext;
import com.rozarltd.util.http.HttpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

// TODO - add
@Configuration
@Import({HsqlPersistenceConfig.class,
        BetfairPublicApiSpringContext.class,
        BetfairRestApiSpringContext.class,
        BettingSpringContext.class,
        AtpWebsiteSpringContext.class,
        BetfairWebsiteSpringContext.class,
        BetfairDataSpringContext.class,
        BettingDataSpringContext.class})
@ImportResource({"classpath:/spring/external/cache-context.xml",
        "classpath:/spring/external/data-repository-context.xml" // TODO - switch to HSQL
//        "classpath:/spring/external/atp-website-module-context.xml",
//        "classpath:/spring/external/betfair-data-module-context.xml",
//        "classpath:/spring/betfairapi/external/betfairapi-context.xml",
//        "classpath:/spring/internal/server-context-internal.xml",
//        "classpath:/spring/external/betfairweb-module-context.xml"
})
//@ComponentScan("com.rozarltd.betting.service")
// @PropertySource("classpath:/com/acme/app.properties")
@PropertySource("file:${user.home}/betting.properties")
public class ServerSpringContext {

    @Autowired private Environment environment;

    @Bean
    public ApplicationProperties applicationProperties() {
        return new ApplicationProperties(environment);
    }

    @Bean
    public CustomConversionServiceFactoryBean conversionService() {
        return new CustomConversionServiceFactoryBean();
    }

    @Bean
    public HttpTemplate httpTemplate() {
        return new HttpTemplate();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
