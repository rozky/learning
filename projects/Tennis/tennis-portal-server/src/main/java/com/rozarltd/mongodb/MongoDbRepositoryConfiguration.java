package com.rozarltd.mongodb;


import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoDbRepositoryConfiguration extends AbstractMongoConfiguration {
//    private static final String MONGO_LAB_DB_NAME = "betting";
//    private static final String MONGO_LAB_USERNAME = "michal.rozar@gmail.com";
//    private static final String MONGO_LAB_PASSWORD = "g3tp1x3l";
//    private static final String MONGO_LAB_HOST = "ds029797.mongolab.com";
//    private static final int MONGO_LAB_PORT = 29797;

    // rozarlimited / g3tp1x3l
    private static final String MONGO_LAB_DB_NAME = "development"; // db password is: development
    private static final String MONGO_LAB_USERNAME = "development";
    private static final String MONGO_LAB_PASSWORD = "development";
    private static final String MONGO_LAB_HOST = "ds029297.mongolab.com";
    private static final int MONGO_LAB_PORT = 29297;

    @Bean
    @Override
    public Mongo mongo() throws Exception {
        return new Mongo(MONGO_LAB_HOST, MONGO_LAB_PORT);
    }

    @Override
    public String getDatabaseName() {
        return MONGO_LAB_DB_NAME;
    }

    @Override
    public UserCredentials getUserCredentials() {
        return new UserCredentials(MONGO_LAB_USERNAME, MONGO_LAB_PASSWORD);
    }
}
