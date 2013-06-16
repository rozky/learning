package com.rozarltd.roger.controller;

import com.rozarltd.domain.analyse.BettingStatsCollection;
import com.rozarltd.domain.analyse.MarketBettingStatsCollection;
import com.rozarltd.service.BettingActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BettingDashboardController {
    @Autowired
    private BettingActivityService bettingActivityService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public void showMarketBettingAnalytics(ModelMap model) {

        BettingStatsCollection latestDaysBets = bettingActivityService.getLatestDaysBets(7);
        model.put("statsByDate", latestDaysBets.getStatsByDate());

        MarketBettingStatsCollection statsByMarket = latestDaysBets.getStatsByMarket();
        model.put("statsByMarket", statsByMarket);
        model.put("latestDayMarketStats", statsByMarket.getLatestDayMarketStats());
    }
}
