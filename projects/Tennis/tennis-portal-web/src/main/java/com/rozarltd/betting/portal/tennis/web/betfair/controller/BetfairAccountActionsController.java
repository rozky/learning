package com.rozarltd.betting.portal.tennis.web.betfair.controller;

import com.rozarltd.betfairapi.service.AccountService;
import com.rozarltd.betting.portal.tennis.web.ModelAttributeName;
import com.rozarltd.betting.portal.tennis.web.session.SessionAttributeName;
import com.rozarltd.domain.account.User;
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
    private AccountService betfairAccountService;

    @Autowired
    public BetfairAccountActionsController(AccountService betfairAccountService) {
        this.betfairAccountService = betfairAccountService;
    }

    @RequestMapping(value = "balance", method = RequestMethod.GET)
    public void doGetBalance(HttpServletRequest request, ModelMap modelMap) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            User currentUser = (User) session.getAttribute(SessionAttributeName.currentUser);
            modelMap.addAttribute(ModelAttributeName.accountBalance,
                    betfairAccountService.getAccountWallets(currentUser.getBetfairPublicApiToken()));
        }
    }
}
