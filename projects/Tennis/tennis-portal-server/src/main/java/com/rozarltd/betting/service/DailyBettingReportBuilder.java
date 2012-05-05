package com.rozarltd.betting.service;

import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.betting.domain.stats.DailyBettingReport;
import com.rozarltd.betting.domain.stats.ReportStatus;
import com.rozarltd.util.java.lang.DoubleUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class DailyBettingReportBuilder {
    private Date reportDate;
    private int placedBetCount = 0;
    private int wonBetCount = 0;
    private int lostBetCount = 0;
    private double amountPlaced = 0;
    private double commission = 0;
    private double startBalance = 0;
    private double endBalance = 0;
    private double deposit = 0;
    private double withdrawal = 0;
    private double totalAmountWon = 0;
    private double totalAmountLost = 0;
    private Set<String> marketNames = new HashSet<String>();
    private int wonMarkets = 0;
    private List<AccountStatementRecord> statement;
    private ReportStatus status;

    public DailyBettingReport build(Date reportDay, List<AccountStatementRecord> statement) {
        this.reportDate = DateUtils.truncate(reportDay, Calendar.DATE);
        this.statement = statement;

        analyseStatement();

        DailyBettingReport report = new DailyBettingReport();
        report.setDate(reportDate);
        report.setWonBets(wonBetCount);
        report.setLostBets(lostBetCount);
        report.setPlacedBets(placedBetCount);
        report.setTotalAmountPlaced(DoubleUtils.valueOf(amountPlaced, 2));
        report.setTotalAmountWon(DoubleUtils.valueOf(totalAmountWon, 2));
        report.setTotalAmountLost(DoubleUtils.valueOf(totalAmountLost, 2));
        report.setCommissionPaid(DoubleUtils.valueOf(commission, 2));
        report.setStartBalance(DoubleUtils.valueOf(startBalance, 2));
        report.setEndBalance(endBalance);
        report.setProfit(DoubleUtils.valueOf(endBalance - startBalance - deposit + withdrawal, 2));
        report.setDeposits(DoubleUtils.valueOf(deposit, 2));
        report.setWithdrawals(DoubleUtils.valueOf(withdrawal, 2));
        report.setMarkets(marketNames.size());
        report.setWonMarkets(wonMarkets);
        report.setLostMarkets(marketNames.size() - wonMarkets);
        report.setStatus(status);

        return report;
    }

    private void analyseStatement() {
        List<AccountStatementRecord> reportDayStatement = filterReportDayRecords();

        if(reportDayStatement.size() < 1) {
            return;
        }

        boolean isFirstRecord = true;
        AccountStatementRecord lastRecord = null;
        for (AccountStatementRecord record: reportDayStatement) {
            if (isFirstRecord) {
                isFirstRecord = false;
                endBalance = record.getAccountBalance();
            }

            switch (record.getWinLost()) {
                case RESULT_WON:
                    wonBetCount++;
                    placedBetCount++;
                    totalAmountWon += record.getAmount();
                    amountPlaced += record.getStake();
                    marketNames.add(record.getFullMarketName());
                    break;
                case RESULT_LOST:
                    lostBetCount++;
                    placedBetCount++;
                    totalAmountLost += (-1 * record.getAmount());
                    amountPlaced += record.getStake();
                    marketNames.add(record.getFullMarketName());
                    break;
                case COMMISSION:
                    wonMarkets++;
                    commission += (-1 * record.getAmount());
                    break;
                case DEPOSIT:
                    deposit += record.getAmount();
                    break;
                case WITHDRAWAL:
                    withdrawal += (-1 * record.getAmount());
                    break;
            }

            lastRecord = record;
        }

        startBalance = lastRecord.getAccountBalance() - lastRecord.getAmount();

        buildReportStatus();
    }

    private void buildReportStatus() {
        if(reportDate != null) {
            boolean isSameDay = DateUtils.isSameDay(reportDate, new Date());
            status = isSameDay ? ReportStatus.P : ReportStatus.C;
        }
    }

    private List<AccountStatementRecord> filterReportDayRecords(){
        List<AccountStatementRecord> reportDayStatement = new ArrayList<AccountStatementRecord>();
        if (statement != null && reportDate != null && statement.size() > 0) {
            for (AccountStatementRecord record: statement) {
                if(DateUtils.isSameDay(reportDate, new Date(record.getSettledAt()))) {
                    reportDayStatement.add(record);
                }
            }
        }

        return reportDayStatement;
    }
}
