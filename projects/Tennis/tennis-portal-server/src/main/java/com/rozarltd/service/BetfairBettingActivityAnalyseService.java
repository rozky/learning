package com.rozarltd.service;

import com.rozarltd.entity.analyse.DayBettingStats;
import com.rozarltd.entity.analyse.MarketBettingStats;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.repository.BetfairAccountStatementRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BetfairBettingActivityAnalyseService implements BettingActivityAnalyseService {

    @Autowired
    private BetfairAccountStatementRepository accStatementRepository;

    @Override
    public List<DayBettingStats> analyseByDate(List<AccountStatementRecord> statement) {
        Map<Long, DayBettingStats.Builder> dayStatsMap = new TreeMap<Long, DayBettingStats.Builder>();
        if(statement != null && statement.size() > 0) {
            for (AccountStatementRecord record : statement) {
                long day = DateUtils.truncate(new Date(record.getSettledAt()), Calendar.DATE).getTime();

                getValue(dayStatsMap, day).incrementWonAmount(record.getWonAmountIfApplicable())
                        .incrementLostAmount(record.getLostAmountIfApplicable())
                        .incrementCommissionAmount(record.getCommissionAmountIfApplicable())
                        .incrementStake(record.getStakeIfApplicable())
                        .incrementDeposit(record.getDepositAmountIfApplicable())
                        .incrementWithdrawal(record.getWithdrawalAmountIfApplicable());
            }
        }

        List<DayBettingStats> result = new ArrayList<DayBettingStats>();
        for(Map.Entry<Long,DayBettingStats.Builder> entry: dayStatsMap.entrySet()) {
            result.add(entry.getValue().build());
        }

        return result;
    }

    @Override
    public List<DayBettingStats> analyseByDate(Date from, Date to) {
        List<AccountStatementRecord> statement = accStatementRepository.findBySettledAtBetween(from.getTime(), to.getTime());
        return analyseByDate(statement);
    }

    @Override
    public List<MarketBettingStats> analyseByMarket(List<AccountStatementRecord> statement) {
        // todo - inject analyser
        return new MarketBetsAnalyser().analyse(statement);
    }

    private DayBettingStats.Builder getValue(Map<Long, DayBettingStats.Builder> map, Long key) {
        DayBettingStats.Builder value = map.get(key);
        if(value == null) {
            value = new DayBettingStats.Builder().date(key);
            map.put(key, value);
        }

        return value;
    }
}
