package com.rozarltd.betting.account;

import com.betfair.publicapi.exchange.types.AccountStatementEnum;
import com.betfair.publicapi.exchange.types.AccountStatementItem;
import com.rozarltd.betting.service.AccountFinanceService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

@Ignore
public class AccountFinanceServiceTest {

//    @Mock private AccountWalletService accountWalletService;

    private AccountFinanceService accountFinanceService;

    @Before
    public void beforeEach() {
        initMocks(this);

//        accountFinanceService = new AccountFinanceService(accountWalletService);
    }
    
    @Test
    public void shouldIgnoreUnfinishedStatements() {
        // given
        // when
        // then
    }

//    @Test
//    @Ignore
//    public void shouldGroupStatementItemsForSameEventId() {
//        // given
//        int startRecordPosition = 0;
//        int recordCount = 10;
//        when(accountWalletService.getMainWalletStatement(startRecordPosition, recordCount)).thenReturn(createStatement());
//
//        // when
//        List<AccountStatementRecord> statement = accountFinanceService.getAccountStatement(startRecordPosition, recordCount);
//
//        // then
//        assertThat(statement.size(), is(2));
//        AccountStatementRecord statementRecord1 = statement.get(0);
//        assertThat(statementRecord1.getAccountBalance(), is(1999.0));
//        assertThat(statementRecord1.getAmount(), is(150.0));
//        assertThat(statementRecord1.getStake(), is(1999.0));
//        assertThat(statementRecord1.getMarketName(), is("tennis | wimbledon | Nadal vs Federer | Match Odds"));
//
//        AccountStatementRecord statementRecord2 = statement.get(1);
//        assertThat(statementRecord2.getAccountBalance(), is(2100.0));
//        assertThat(statementRecord2.getAmount(), is(-160.0));
//        assertThat(statementRecord2.getStake(), is(160.0));
//        assertThat(statementRecord2.getMarketName(), is("tennis | wimbledon | Nadal vs Federer | Match Odds"));
//    }

    private List<AccountStatementItem> createStatement() {
        List<AccountStatementItem> statementItems = new ArrayList<AccountStatementItem>();
        statementItems.add(createStatementItem(3, 0, 44, 2260, AccountStatementEnum.RESULT_NOT_APPLICABLE));
        statementItems.add(createStatementItem(2, -100, 0.0, 2160, AccountStatementEnum.RESULT_LOST));
        statementItems.add(createStatementItem(2, -60, 0.0, 2100, AccountStatementEnum.RESULT_LOST));
        statementItems.add(createStatementItem(1, -300, 0.0, 1800, AccountStatementEnum.RESULT_LOST));
        statementItems.add(createStatementItem(1, 150, 0.0, 1950, AccountStatementEnum.RESULT_WON));
        statementItems.add(createStatementItem(1, 50, 0.0, 2000, AccountStatementEnum.RESULT_WON));
        statementItems.add(createStatementItem(1, 0.0, 150, 1999, AccountStatementEnum.RESULT_NOT_APPLICABLE));
        Collections.reverse(statementItems);

        return statementItems;
    }

    private AccountStatementItem createStatementItem(int eventId, double amount, double grossBetAmount, double accountBalance, AccountStatementEnum winLose) {
        AccountStatementItem item = new AccountStatementItem();
        item.setEventId(eventId);
        item.setWinLose(winLose);
        item.setAccountBalance(accountBalance);
        item.setAmount(amount);
        item.setGrossBetAmount(grossBetAmount);
        item.setSettledDate(Calendar.getInstance());
        item.setFullMarketName("tennis | wimbledon | Nadal vs Federer | Match Odds");

        return item;
    }
}


