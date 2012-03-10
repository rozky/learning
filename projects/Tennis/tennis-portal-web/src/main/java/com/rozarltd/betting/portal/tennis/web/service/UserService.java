package com.rozarltd.betting.portal.tennis.web.service;

import com.rozarltd.betfairapi.service.AccountService;
import com.rozarltd.setting.ApplicationProperties;
import com.rozarltd.betting.portal.tennis.web.session.SessionAttributeName;
import com.rozarltd.domain.account.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserService {
    private AccountService betfairAccountService;

    @Autowired
    public UserService(AccountService betfairAccountService) {
        this.betfairAccountService = betfairAccountService;
    }

    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(SessionAttributeName.currentUser);

        if(currentUser == null && ApplicationProperties.isDevelopmentMode()) {
            String username = ApplicationProperties.getDevelopmentBetfairUsername();
            String password = ApplicationProperties.getDevelopmentBetfairPassword();

            String betfairSession = betfairAccountService.login(username, password);

            if (betfairSession != null) {
                currentUser = new User(username, betfairSession, null);
                session.setAttribute(SessionAttributeName.currentUser, currentUser);
            }
        }

        return currentUser;
    }
}
