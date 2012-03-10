package com.rozarltd.betfairapi.service;

import com.rozarltd.betfairapi.domain.account.BetfairAccountStatementItem;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/external/server-context.xml")
@Ignore
public class BetfairAccountServiceIntegrationTest {

    @Autowired private BetfairAccountStatementService betfairAccountService;

    @Test
    @Ignore
    public void shouldReturnLastTenStatementItems() {
        // when
        List<BetfairAccountStatementItem> statement = betfairAccountService.getStatement(0, 10);

        // then
        assertThat(statement.size(), is(10));
    }
}
