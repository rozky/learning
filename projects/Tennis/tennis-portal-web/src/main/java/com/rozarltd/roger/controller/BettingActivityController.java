package com.rozarltd.roger.controller;

import com.rozarltd.domain.Page;
import com.rozarltd.domain.analyse.DayBettingStatsCollection;
import com.rozarltd.domain.analyse.MarketBettingStatsCollection;
import com.rozarltd.entity.analyse.DayBettingStats;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.service.BettingActivityAnalyseAdmin;
import com.rozarltd.service.BettingActivityService;
import com.rozarltd.service.MarketBetsAnalyser;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BettingActivityController {
    @Autowired
    private BettingActivityService bettingActivityService;

    @Autowired
    private BettingActivityAnalyseAdmin bettingActivityAnalyseAdmin;

    @RequestMapping(value = "betting/history", method = RequestMethod.GET)
    public void showBetHistory(ModelMap model,
                               @RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Integer pageSize) {

        Page currentPage = new Page(page, pageSize);
        model.put("betHistory", bettingActivityService.getBetResults(currentPage));
        model.put("currentPage", currentPage);
    }

    @RequestMapping(value = "betting/summary/day", method = RequestMethod.GET)
    public void showBettingSummaryByDay(ModelMap model,
                                        @RequestParam(required = false) Integer page,
                                        @RequestParam(required = false) Integer pageSize) {
//        Page currentPage = new Page(page, pageSize);
//        model.put("bettingSummary", bettingActivityService.getLatestDaysBets(10));
//        model.put("currentPage", currentPage);
    }


    @RequestMapping(value = "betting/betfair/history", method = RequestMethod.GET)
    public void showBetfairAccountStatement(ModelMap model) {

        Date now = DateUtils.addMonths(new Date(), -8);
        Date yesterday = DateUtils.addDays(now, 2);

//        List<AccountStatementRecord> history = bettingActivityService.getAccountStatement(yesterday, now);
        List<AccountStatementRecord> history = bettingActivityService.getAccountStatement(DateUtils.addDays(new Date(1348245487000L), -10), new Date(1348245487000L));

        Map<String, List<AccountStatementRecord>> historyByMarketType = new HashMap<String, List<AccountStatementRecord>>();
        for (AccountStatementRecord item : history) {
            List<AccountStatementRecord> marketTypeHist = historyByMarketType.get(item.getMarketName());
            if(marketTypeHist == null) {
                marketTypeHist = new ArrayList<AccountStatementRecord>();
                historyByMarketType.put(item.getMarketName(), marketTypeHist);
            }
            marketTypeHist.add(item);
        }

        model.put("betHistory", historyByMarketType);
        model.put("marketTypes", historyByMarketType.keySet());
    }

    @RequestMapping(value = "betting/betfair/analysis", method = RequestMethod.GET)
    public void showBetfairAccountStatementAnalysis(ModelMap model) {

        List<DayBettingStats> analysis = bettingActivityAnalyseAdmin.load();

        model.put("analysis", new DayBettingStatsCollection(analysis));
    }

    @RequestMapping(value = "betting/betfair/dashboard", method = RequestMethod.GET)
    public void showMarketBettingAnalytics(ModelMap model) {

        List<AccountStatementRecord> bets = bettingActivityService.getAccountStatement();
        model.put("marketBets", new MarketBettingStatsCollection(new MarketBetsAnalyser().analyse(bets)));
    }

}
