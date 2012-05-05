package com.rozarltd.betting.portal.tennis.web.betfair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BettingStorageController {

    @RequestMapping(value= "undefined", method = RequestMethod.POST)
    public void backupBetfairBets() {
        // get last backup time
        // get betfair bet from that time
        // copy those bets to mongo db
    }
}
