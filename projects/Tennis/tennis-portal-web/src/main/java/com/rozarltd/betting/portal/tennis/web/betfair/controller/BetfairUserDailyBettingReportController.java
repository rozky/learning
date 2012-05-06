package com.rozarltd.betting.portal.tennis.web.betfair.controller;

import com.rozarltd.account.User;
import com.rozarltd.betting.portal.tennis.web.Routing;
import com.rozarltd.betting.portal.tennis.web.service.UserService;
import com.rozarltd.module.bettingdata.service.UserBettingDataCollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class BetfairUserDailyBettingReportController {
    private UserService userService;
    private UserBettingDataCollectorService bettingDataCollectorService;

    @Autowired
    public BetfairUserDailyBettingReportController(UserBettingDataCollectorService bettingDataCollectorService,
                                                   UserService userService) {
        this.bettingDataCollectorService = bettingDataCollectorService;
        this.userService = userService;
    }


    @RequestMapping(value = Routing.BETFAIR_DAILY_BETTING_REPORT, method = RequestMethod.GET)
    public void showDailyBettingReport(HttpServletRequest request) {

    }

    @RequestMapping(value = Routing.BETFAIR_DAILY_BETTING_REPORT, method = RequestMethod.POST)
    public RedirectView createDailyBettingReport(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date reportDate,
                                                 HttpServletRequest request) {

        User currentUser = userService.getCurrentUser(request);
        bettingDataCollectorService.createDailyBettingSummaryReport(currentUser, reportDate);

        return new RedirectView(Routing.CONFIRMATION);
    }
}
