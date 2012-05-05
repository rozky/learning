package com.rozarltd.mongodb;

import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.module.betfairapi.stub.domain.AccountStatementRecordFixture;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoRepositoryConfiguration.class,
        loader=AnnotationConfigContextLoader.class)
@Ignore
public class MongoDbIntegrationTest {

    @Autowired private MongoTemplate mongoTemplate;

    @Test
    public void shouldCreateANewDocument() {
        // given
        // when
//        mongoTemplate.insert(new SampleObject("michal rozar", 25, false), "test");
        boolean exists = mongoTemplate.collectionExists(AccountStatementRecord.class);
        if(exists) {
            System.out.println("exist");
        }
        else {
            mongoTemplate.createCollection(AccountStatementRecord.class);
        }

        mongoTemplate.insert(AccountStatementRecordFixture.winRecord(new Date(), 100.50));

        // then
    }
}
