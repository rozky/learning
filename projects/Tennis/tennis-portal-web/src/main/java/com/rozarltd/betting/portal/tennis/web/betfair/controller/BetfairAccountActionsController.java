package com.rozarltd.betting.portal.tennis.web.betfair.controller;

import com.rozarltd.account.BetfairUser;
import com.rozarltd.module.betfairapi.service.BetfairAccountApi;
import com.rozarltd.betting.portal.tennis.web.ModelAttributeName;
import com.rozarltd.betting.portal.tennis.web.session.SessionAttributeName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("betfair/account")
public class BetfairAccountActionsController {
    private BetfairAccountApi betfairAccountService;

    @Autowired
    public BetfairAccountActionsController(BetfairAccountApi betfairAccountService) {
        this.betfairAccountService = betfairAccountService;
    }

    @RequestMapping(value = "balance", method = RequestMethod.GET)
    public void doGetBalance(HttpServletRequest request, ModelMap modelMap) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            BetfairUser currentUser = (BetfairUser) session.getAttribute(SessionAttributeName.currentUser);
            modelMap.addAttribute(ModelAttributeName.accountBalance,
                    betfairAccountService.getAccountWallets(currentUser.getPublicApiToken()));
        }
    }
}
