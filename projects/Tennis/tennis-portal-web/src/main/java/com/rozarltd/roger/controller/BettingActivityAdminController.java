package com.rozarltd.roger.controller;

import com.rozarltd.account.BetfairUser;
import com.rozarltd.service.BettingActivityAdminService;
import com.rozarltd.service.BettingActivityAnalyseAdmin;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class BettingActivityAdminController {
    //    @Autowired private BettingActivityAdminService<BetfairUser> bettingActivityAdminService;
    private BettingActivityAdminService<BetfairUser> bettingActivityAdminService;

    @Autowired

    private BettingActivityAnalyseAdmin bettingActivityAnalyseAdmin;

    @RequestMapping(value = "betting/admin/copy", method = RequestMethod.GET)
    public void copyBettingActivityFromBetfairToLocalDataStore() {
        BetfairUser user = null;
        bettingActivityAdminService.copyBettingActivity(user, new Date(), null); // todo - dates

        Date date = DateUtils.addDays(new Date(), -1);

    }

    // TODO - this should be POST
    @RequestMapping(value = "betting/admin/analyse", method = RequestMethod.GET)
    public void performDayAnalysis() {
        Date now = new Date();
        Date fromDate = DateUtils.addYears(now, -2);

        bettingActivityAnalyseAdmin.performAnalysisAndSaveResult(fromDate, now);
    }

    @RequestMapping(value = "betting/admin/analyse/delete", method = RequestMethod.GET)
    public void deleteDayAnalysis() {
        bettingActivityAnalyseAdmin.delete();
    }
}
