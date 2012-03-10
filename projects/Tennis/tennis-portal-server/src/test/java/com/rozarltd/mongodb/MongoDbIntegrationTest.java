package com.rozarltd.mongodb;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoDbUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.net.UnknownHostException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoDbRepositoryConfiguration.class,
        loader=AnnotationConfigContextLoader.class)
public class MongoDbIntegrationTest {

    @Autowired private MongoTemplate mongoTemplate;

    @Test
    public void shouldWork() throws UnknownHostException {
        // given
        // when
        MongoURI mongoUri = new MongoURI("mongodb://development:development@ds029297.mongolab.com:29297");
        Mongo mongo = new Mongo(mongoUri);
//        DB betting = MongoDbUtils.getDB(mongo, "betting");
        DB betting = MongoDbUtils.getDB(mongo, "development");
//        betting.authenticate("development")
        System.out.println("cool");
        // then
    }

    @Test
    public void shouldCreateANewDocument() {
        // given
        // when
//        mongoTemplate.insert(new SampleObject("michal rozar", 25, false), "test");
        // then
    }

    public class SampleObject {
        private String name;
        private int age;
        private boolean active;

        public SampleObject(String name, int age, boolean active) {
            this.name = name;
            this.age = age;
            this.active = active;
        }
    }
}
