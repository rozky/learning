package com.rozarltd.repository;

import com.rozarltd.config.HsqlPersistenceTestConfig;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.Date;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@ActiveProfiles("test-hsql")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = HsqlPersistenceTestConfig.class)
@TransactionConfiguration
public class BetfairAccountStatementRepositoryTest {

    @Autowired
    private BetfairAccountStatementRepository repository;

    // todo
    @Test
    public void shouldCreateEntity() {
        // given
        AccountStatementRecord record = new AccountStatementRecord.Builder().id("1").betId(99).build();

        // when
        AccountStatementRecord savedRecord = repository.save(record);

        // then
        assertThat(savedRecord.getBetId()).isEqualTo(99);
    }

    @Test
    public void shouldGetStatementsForTheGivenPeriod() {
        // given
        Date now = new Date();
        Date yesterday = DateUtils.addDays(now, -1);
        repository.save(new AccountStatementRecord.Builder().id("1").settledDate(now).build());
        repository.save(new AccountStatementRecord.Builder().id("2").settledDate(yesterday).build());

        // when
        List<AccountStatementRecord> statements = repository.findBySettledAtBetween(yesterday.getTime(), now.getTime());

        // then
        assertThat(statements.size()).isEqualTo(2);
    }
}
