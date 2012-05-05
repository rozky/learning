package com.rozarltd;

import com.rozarltd.betting.converter.CustomConversionServiceFactoryBean;
import com.rozarltd.module.atpwebsite.AtpWebsiteSpringContext;
import com.rozarltd.module.betfairapi.BetfairPublicApiSpringContext;
import com.rozarltd.module.betfairdata.BetfairDataSpringContext;
import com.rozarltd.module.betfairwebsite.BetfairWebsiteSpringContext;
import com.rozarltd.util.http.HttpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import({BetfairPublicApiSpringContext.class,
        AtpWebsiteSpringContext.class,
        BetfairWebsiteSpringContext.class,
        BetfairDataSpringContext.class})
@ImportResource({"classpath:/spring/external/cache-context.xml",
        "classpath:/spring/external/data-repository-context.xml",
//        "classpath:/spring/external/atp-website-module-context.xml",
//        "classpath:/spring/external/betfair-data-module-context.xml",
//        "classpath:/spring/betfairapi/external/betfairapi-context.xml",
//        "classpath:/spring/internal/server-context-internal.xml",
//        "classpath:/spring/external/betfairweb-module-context.xml"
})
@ComponentScan("com.rozarltd.betting.service")
// @PropertySource("classpath:/com/acme/app.properties")
public class ServerSpringContext {

    @Bean
    public CustomConversionServiceFactoryBean conversionService() {
        return new CustomConversionServiceFactoryBean();
    }

    @Bean
    public HttpTemplate httpTemplate() {
        return new HttpTemplate();
    }
}
