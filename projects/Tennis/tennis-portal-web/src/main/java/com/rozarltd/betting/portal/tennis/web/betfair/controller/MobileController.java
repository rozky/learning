package com.rozarltd.betting.portal.tennis.web.betfair.controller;

import com.rozarltd.account.BetfairUser;
import com.rozarltd.betting.portal.tennis.web.ModelAttributeName;
import com.rozarltd.betting.portal.tennis.web.service.UserService;
import com.rozarltd.betting.service.MarketService;
import com.rozarltd.eventing.event.ReplicationCompletedEvent;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.module.betfairapi.domain.account.statement.StatementRecordType;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.domain.market.BetfairPopularEvent;
import com.rozarltd.module.betfairapi.service.BetfairAccountApi;
import com.rozarltd.repository.BetfairAccountStatementRepository;
import com.rozarltd.util.java.lang.DateUtilities;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Controller
public class MobileController implements ApplicationEventPublisherAware {
    @Autowired private UserService userService;
    @Autowired private MarketService betfairMarketFacade;
    @Autowired private BetfairAccountStatementRepository repository;
    @Autowired private BetfairAccountApi accountService;
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @RequestMapping(value = "mobile", method = RequestMethod.GET)
    public void renderMarkets(ModelMap modelMap) {
        List<BetfairMarket> liveMarkets = betfairMarketFacade.getLiveMarkets(BetfairPopularEvent.tennis.getEventId());

        modelMap.addAttribute(ModelAttributeName.markets, liveMarkets);
    }

    @RequestMapping(value = "mobilestatement", method = RequestMethod.GET)
    public void showStatement(ModelMap modelMap) {
        List<AccountStatementRecord> fullAccStatement = (List<AccountStatementRecord>) repository.findAll();

        Map<Long, DayAccountStatement> asByDays = new TreeMap<Long, DayAccountStatement>();
        if(fullAccStatement != null && fullAccStatement.size() > 0) {
            for (AccountStatementRecord record : fullAccStatement) {

                long dayOfTheYear = -1 * DateUtils.getFragmentInDays(new Date(record.getSettledAt()), Calendar.YEAR);
                DayAccountStatement day = asByDays.get(dayOfTheYear);
                if(day == null) {
                    day = new DayAccountStatement();
                    asByDays.put(dayOfTheYear, day);
                }
                day.addItem(record);

//                repository.save(record);
            }
        }

        Set<Map.Entry<Long,DayAccountStatement>> accountStatementsByDay = asByDays.entrySet();
        modelMap.addAttribute("statements", accountStatementsByDay);

        AccountStatementSummary summary = new AccountStatementSummary();
        summary.addDayStatements(asByDays.values());
        modelMap.addAttribute("summary", summary);

        LinkedList<Candle> candles = new LinkedList<Candle>();
        int day = 1;
        for(Map.Entry<Long,DayAccountStatement> entry: accountStatementsByDay) {
            candles.push(new Candle(String.valueOf(day++), entry.getValue().getStartBalanceAll(), entry.getValue().getBalance()));
        }
        modelMap.addAttribute("candles", candles);
    }

    @RequestMapping(value = "mobilereplicate", method = RequestMethod.GET)
    public void replicateAccountStatement(ModelMap modelMap, @RequestParam Integer days, HttpServletRequest request) {
        StopWatch watch = new StopWatch();
        watch.start();

        Date startDate = DateUtils.addDays(DateUtilities.today(), -days);
        Date endDate = new Date();
        BetfairUser currentUser = userService.getCurrentUser(request);

        List<AccountStatementRecord> statement =
                accountService.getWalletStatement(currentUser.getPublicApiToken(), startDate, endDate);

        long startCount = repository.count();

        if(statement != null && statement.size() > 0) {
            for (AccountStatementRecord record : statement) {
                repository.save(record);
            }
        }

        long endCount = repository.count();
        long newItemsCount = endCount - startCount;

        watch.stop();

        AccountStatementReplicationResult replicationResult = new AccountStatementReplicationResult();
        replicationResult.setStartDate(startDate);
        replicationResult.setEndDate(endDate);
        replicationResult.setStartCount(startCount);
        replicationResult.setEndCount(endCount);
        replicationResult.setNewCount(newItemsCount);
        replicationResult.setDuration(watch.getTotalTimeMillis() / 1000);

        modelMap.addAttribute("replicationResult", replicationResult);

        eventPublisher.publishEvent(
                new ReplicationCompletedEvent(this)
                        .withFromDate(startDate)
                        .withToDate(endDate)
                        .withNewItemCount(newItemsCount)
                        .withDuration(watch.getTotalTimeMillis()));
    }

    // dataReplication
        // source
        // data
        // mapFunction
        // => ReplicationResult

    public interface DataReplicator<I,O> {
        public ReplicationResult replicate(DataSource<I> source, CrudRepository<O, Integer> repository);
    }

    public class AccountStatementDataReplicator implements DataReplicator<AccountStatementRecord, AccountStatementRecord> {
        @Override
        public ReplicationResult replicate(DataSource<AccountStatementRecord> source,
                                           CrudRepository<AccountStatementRecord, Integer> repository) {
            StopWatch watch = new StopWatch();
            watch.start();
            ReplicationResult replicationResult = new ReplicationResult();
            replicationResult.setStart(new Date());
            replicationResult.setPreReplicationCount(MobileController.this.repository.count());

            List<AccountStatementRecord> data = source.getData();
            if(data != null && !data.isEmpty()) {
                MobileController.this.repository.save(data);
                replicationResult.setPostReplicationCount(data.size());
            }

            replicationResult.setPostReplicationCount(MobileController.this.repository.count());
            watch.stop();
            replicationResult.setDuration(watch.getLastTaskTimeMillis());
            return replicationResult;
        }
    }

    private interface DataSource<T> {
        public List<T> getData();
    }

    // todo: find better name, RequestAwareAccountStatementDataSource
    private class AccountStatementDataSource implements DataSource<AccountStatementRecord> {
        private BetfairUser user;
        private int days;

        private AccountStatementDataSource(BetfairUser user, int days) {
            this.user = user;
            this.days = days;
        }

        @Override
        public List<AccountStatementRecord> getData() {
            Date startDate = DateUtils.addDays(DateUtilities.today(), -days);
            Date endDate = new Date();

            return accountService.getWalletStatement(user.getPublicApiToken(), startDate, endDate);
        }
    }

    public class ReplicationResult {
        private Date start;
        private long duration;
        private long preReplicationCount;
        private long postReplicationCount;
        private long newCount;

        public Date getStart() {
            return start;
        }

        public void setStart(Date start) {
            this.start = start;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public long getPostReplicationCount() {
            return postReplicationCount;
        }

        public void setPostReplicationCount(long postReplicationCount) {
            this.postReplicationCount = postReplicationCount;
        }

        public long getPreReplicationCount() {
            return preReplicationCount;
        }

        public void setPreReplicationCount(long preReplicationCount) {
            this.preReplicationCount = preReplicationCount;
        }

        public long getNewCount() {
            return newCount;
        }

        public void setNewCount(long newCount) {
            this.newCount = newCount;
        }
    }
    public class AccountStatementSummary {
        private List<DayAccountStatement> days;
        private int deposits;
        private int withdrawals;
        private int profit;
        private int winningDaysCount;
        private int losingDaysCount;
        private double startBalance = 0;
        private double endBalance;
        private double balance = 0;

        public void addDayStatements(Collection<DayAccountStatement> statements) {
            for(DayAccountStatement statement: statements) {
                addDayStatement(statement);
            }

            ArrayList<DayAccountStatement> dayAccountStatements = new ArrayList<DayAccountStatement>(statements);
            Collections.reverse(dayAccountStatements);
            for(DayAccountStatement statement: dayAccountStatements) {
                updateBalance(statement);
            }
        }

        public void addDayStatement(DayAccountStatement statement)  {
            this.deposits += statement.getDeposit();
            this.withdrawals += statement.getWithdrawal();
            this.profit += statement.getProfit();

            if(statement.getProfit() < 0) {
                losingDaysCount++;
            } else {
                winningDaysCount++;
            }
        }

        public void updateBalance(DayAccountStatement statement)  {
            statement.setStartBalanceAll(balance);
            balance += statement.getProfit();
            statement.setBalance(balance);
        }

        public int getDeposits() {
            return deposits;
        }

        public int getWithdrawals() {
            return withdrawals;
        }

        public int getProfit() {
            return profit;
        }

        public int getWinningDaysCount() {
            return winningDaysCount;
        }

        public int getLosingDaysCount() {
            return losingDaysCount;
        }
    }

    public class DayAccountStatement {
        private double deposit;
        private double withdrawal;

        // todo: remove
        private AccountStatementRecord startBalanceItem;
        // todo: remove
        private AccountStatementRecord endBalanceItem;

        private List<AccountStatementRecord> earliestStatementRecords;
        private List<AccountStatementRecord> latestStatementRecords;
        private double balance;

        // rename - startAccountBalance

        private double startBalanceAll;

        public double getStartBalanceAll() {
            return startBalanceAll;
        }

        public void setStartBalanceAll(double startBalanceAll) {
            this.startBalanceAll = startBalanceAll;
        }

        private List<AccountStatementRecord> items = new ArrayList<AccountStatementRecord>();

        public void addItem(AccountStatementRecord item) {
            this.items.add(item);
            updateStartBalance(item);
            updateEndBalance(item);
            updateDeposit(item);
            updateWithdrawal(item);
        }

        public Date getDay() {
            return new Date(startBalanceItem.getSettledAt());
        }

        public int getProfit() {
            return getEndBalance() - getStartBalance() - getDeposit() + getWithdrawal();
        }

        public int getStartBalance() {
            AccountStatementRecord startBalanceRecord = getStartBalanceItem();
            return (int) (startBalanceRecord.getAccountBalance() - startBalanceRecord.getAmount());
        }                

        public int getEndBalance() {
            return (int) getEndBalanceItem().getAccountBalance();
        }

        public int getDeposit() {
            return (int) deposit;
        }

        public int getWithdrawal() {
            return -1 * (int) withdrawal;
        }

        public int getItemsCount() {
            return items.size();
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }



        private void updateStartBalance(AccountStatementRecord item) {
            if(startBalanceItem == null || item.getSettledAt() < startBalanceItem.getSettledAt()) {
                startBalanceItem = item;
                earliestStatementRecords = new ArrayList<AccountStatementRecord>();
                earliestStatementRecords.add(item);
            } else if(item.getSettledAt() == startBalanceItem.getSettledAt()) {
                earliestStatementRecords.add(item);

                if(item.getBetId() < startBalanceItem.getBetId()) {
                    startBalanceItem = item;
                } else if(item.getBetId() == startBalanceItem.getBetId() && StatementRecordType.COMMISSION == startBalanceItem.getWinLost()) {
                    startBalanceItem = item;
                }
            }
        }

        private void updateEndBalance(AccountStatementRecord item) {
            if(endBalanceItem == null || item.getSettledAt() > endBalanceItem.getSettledAt()) {
                endBalanceItem = item;
                latestStatementRecords = new ArrayList<AccountStatementRecord>();
                latestStatementRecords.add(item);
            } else if(item.getSettledAt() == endBalanceItem.getSettledAt()) {
                latestStatementRecords.add(item);

                if(item.getBetId() > endBalanceItem.getBetId()) {
                    endBalanceItem = item;
                } else if(item.getBetId() == endBalanceItem.getBetId() && StatementRecordType.COMMISSION == item.getWinLost()) {
                    endBalanceItem = item;
                }
            }
        }

        private void updateDeposit(AccountStatementRecord item) {
            if(StatementRecordType.DEPOSIT == item.getWinLost()) {
                deposit += item.getAmount();
            }
        }

        private void updateWithdrawal(AccountStatementRecord item) {
            if(StatementRecordType.WITHDRAWAL == item.getWinLost()) {
                withdrawal += item.getAmount();
            }
        }
        
        private AccountStatementRecord getStartBalanceItem() {
            if(!CollectionUtils.isEmpty(earliestStatementRecords)) {
                List<Double> balances = getEndBalances(earliestStatementRecords);

                for(AccountStatementRecord record: earliestStatementRecords) {
                    if(!balances.contains(getBeginAccountBalance(record))) {
                        return record;
                    }
                }
            }

            return null;
        }

        private AccountStatementRecord getEndBalanceItem() {
            if(!CollectionUtils.isEmpty(latestStatementRecords)) {
                List<Double> balances = getBeginBalances(latestStatementRecords);

                for(AccountStatementRecord record: latestStatementRecords) {
                    if(!balances.contains(getEndAccountBalance(record))) {
                        return record;
                    }
                }
            }

            return null;
        }

        private List<Double> getBeginBalances(List<AccountStatementRecord> records) {
            List<Double> balances = new ArrayList<Double>();
            for(AccountStatementRecord record: records) {
                balances.add(getBeginAccountBalance(record));
            }

            return balances;
        }

        private List<Double> getEndBalances(List<AccountStatementRecord> records) {
            List<Double> balances = new ArrayList<Double>();
            for(AccountStatementRecord record: records) {
                balances.add(getEndAccountBalance(record));
            }

            return balances;
        }

        @Override
        public String toString() {
            return String.format("Day Summary: [day = %s] [profit = %s] [start = %s] [end = %s] [in = %s] [out = %s]",
                    new Date(startBalanceItem.getSettledAt()), getProfit(),
                    startBalanceItem.getAccountBalance(), endBalanceItem.getAccountBalance(),
                    deposit, withdrawal);
        }

        private double getBeginAccountBalance(AccountStatementRecord record) {
            return BigDecimal.valueOf(record.getAccountBalance()).subtract(BigDecimal.valueOf(record.getAmount())).doubleValue();
        }

        private double getEndAccountBalance(AccountStatementRecord record) {
            return record.getAccountBalance();
        }
    }

    public class AccountStatementReplicationResult {
        private Date startDate;
        private Date endDate;
        private long startCount;
        private long endCount;
        private long newCount;
        private long duration;

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public long getStartCount() {
            return startCount;
        }

        public void setStartCount(long startCount) {
            this.startCount = startCount;
        }

        public long getEndCount() {
            return endCount;
        }

        public void setEndCount(long endCount) {
            this.endCount = endCount;
        }

        public long getNewCount() {
            return newCount;
        }

        public void setNewCount(long newCount) {
            this.newCount = newCount;
        }
    }

    public static class Candle {
        private String name;
        private double start;
        private double end;

        public Candle(String name, double start, double end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getStart() {
            return start;
        }

        public void setStart(double start) {
            this.start = start;
        }

        public double getEnd() {
            return end;
        }

        public void setEnd(double end) {
            this.end = end;
        }
    }

}
