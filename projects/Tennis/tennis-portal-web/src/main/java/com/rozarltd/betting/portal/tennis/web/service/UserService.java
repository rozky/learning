package com.rozarltd.betting.portal.tennis.web.service;

import com.rozarltd.account.BetfairUser;
import com.rozarltd.application.ApplicationProperties;
import com.rozarltd.module.betfairapi.service.BetfairAccountApi;
import com.rozarltd.betting.portal.tennis.web.session.SessionAttributeName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserService {
    private BetfairAccountApi betfairAccountService;

    @Autowired
    public UserService(BetfairAccountApi betfairAccountService) {
        this.betfairAccountService = betfairAccountService;
    }

    public BetfairUser getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        BetfairUser currentUser = (BetfairUser) session.getAttribute(SessionAttributeName.currentUser);

        if(currentUser == null && ApplicationProperties.isDevelopmentMode()) {
            String username = ApplicationProperties.getDevelopmentBetfairUsername();
            String password = ApplicationProperties.getDevelopmentBetfairPassword();

            String betfairSession = betfairAccountService.login(username, password);

            if (betfairSession != null) {
                currentUser = new BetfairUser(username, betfairSession, null);
                session.setAttribute(SessionAttributeName.currentUser, currentUser);
            }
        }

        return currentUser;
    }
}
