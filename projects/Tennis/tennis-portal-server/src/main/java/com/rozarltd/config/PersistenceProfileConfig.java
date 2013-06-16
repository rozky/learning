package com.rozarltd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        HsqlPersistenceConfig.class,
        HsqlPersistenceTestConfig.class
})
public class PersistenceProfileConfig {
}
