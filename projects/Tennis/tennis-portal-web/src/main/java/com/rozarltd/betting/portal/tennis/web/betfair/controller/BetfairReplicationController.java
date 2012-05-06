package com.rozarltd.betting.portal.tennis.web.betfair.controller;

import com.rozarltd.account.User;
import com.rozarltd.betting.service.TennisMarketReplicationService;
import com.rozarltd.betting.portal.tennis.web.Routing;
import com.rozarltd.betting.portal.tennis.web.betfair.domain.StatusResponse;
import com.rozarltd.betting.portal.tennis.web.service.UserService;
import com.rozarltd.module.bettingdata.service.UserBettingDataCollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class BetfairReplicationController {
    private UserBettingDataCollectorService bettingDataCollectorService;
    private UserService userService;
    private TennisMarketReplicationService tennisMarketReplicationService;

    @Autowired
    public BetfairReplicationController(UserBettingDataCollectorService bettingDataCollectorService,
                                        UserService userService, TennisMarketReplicationService tennisMarketReplicationService) {
        this.bettingDataCollectorService = bettingDataCollectorService;
        this.userService = userService;
        this.tennisMarketReplicationService = tennisMarketReplicationService;
    }

    @RequestMapping(value = Routing.BETFAIR_REPLICATION, method = RequestMethod.GET)
    public void replicationActions() {
    }

    @RequestMapping(value = Routing.BETFAIR_ACCOUNT_STATEMENT, method = RequestMethod.POST)
    public void replicateAccountStatement(@RequestParam Date startDate, HttpServletRequest request) {
        User currentUser = userService.getCurrentUser(request);
        bettingDataCollectorService.replicateAccountStatement(currentUser, startDate);
    }

    @RequestMapping(value = Routing.BETFAIR_TODAY_MARKET_REPLICATION, method = RequestMethod.POST)
    public @ResponseBody StatusResponse replicateTodayMarkets() {
        try {
            tennisMarketReplicationService.replicateTodayMarkets();
        }
        catch (Exception e) {
            return StatusResponse.ERROR;
        }

        return StatusResponse.OK;
    }
}
