package com.rozarltd.module.atpwebsite;

import com.rozarltd.module.atpwebsite.service.AtpWebsiteClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtpWebsiteSpringContext {

    @Bean
    public AtpWebsiteClient atpWebsiteClient() {
        return new AtpWebsiteClient();
    }
}
