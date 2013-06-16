package com.rozarltd.config;

import com.rozarltd.service.BetfairBettingActivityAnalyseAdmin;
import com.rozarltd.service.BetfairBettingActivityAnalyseService;
import com.rozarltd.service.BetfairBettingActivityService;
import com.rozarltd.service.BettingActivityAnalyseAdmin;
import com.rozarltd.service.BettingActivityAnalyseService;
import com.rozarltd.service.BettingActivityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        PersistenceProfileConfig.class,
        EhCacheConfig.class
})
public class CoreBettingServicesConfig {

    @Bean
    public BettingActivityService bettingActivityService() {
        return new BetfairBettingActivityService();
    }

    @Bean
    public BettingActivityAnalyseService bettingActivityAnalyseService() {
        return new BetfairBettingActivityAnalyseService();
    }

    @Bean
    public BettingActivityAnalyseAdmin bettingActivityAnalyseAdmin() {
        return new BetfairBettingActivityAnalyseAdmin();
    }
}
