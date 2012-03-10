package com.rozarltd.betting.portal.tennis.web.betfair.controller;

import com.rozarltd.betfairapi.service.AccountFacade;
import com.rozarltd.betfairapi.service.AccountService;
import com.rozarltd.betfairapi.service.BFExchangeApiService;
import com.rozarltd.betfairapi.service.BFRestApiService;
import com.rozarltd.betfairapi.service.MarketFacade;
import com.rozarltd.betting.domain.BetLittle;
import com.rozarltd.betting.portal.tennis.web.ModelAttributeName;
import com.rozarltd.betting.portal.tennis.web.Routing;
import com.rozarltd.betting.portal.tennis.web.betfair.domain.StatusJsonResponse;
import com.rozarltd.betting.portal.tennis.web.betfair.model.CurrentBetsTemplateModelBuilder;
import com.rozarltd.betting.portal.tennis.web.service.UserService;
import com.rozarltd.betting.service.BetPlacementResult;
import com.rozarltd.betting.service.BettingFacade;
import com.rozarltd.domain.account.User;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BetfairRestController{
    private BFRestApiService restApiService;
    private BFExchangeApiService exchangeApiService;
    private AccountService betfairAccountService;
    private BettingFacade bettingFacade;
    private BFExchangeApiService betfairExchangeApiService;
    private MarketFacade betfairMarketFacade;
    private AccountFacade betfairAccountFacade;
    private UserService userService;

    @Autowired
    public BetfairRestController(BFRestApiService restApiService, BFExchangeApiService exchangeApiService,
                                 AccountService betfairAccountService, BettingFacade bettingFacade,
                                 BFExchangeApiService betfairExchangeApiService,
                                 MarketFacade betfairMarketFacade,
                                 AccountFacade betfairAccountFacade,
                                 UserService userService) {
        this.restApiService = restApiService;
        this.exchangeApiService = exchangeApiService;
        this.betfairAccountService = betfairAccountService;
        this.bettingFacade = bettingFacade;
        this.betfairExchangeApiService = betfairExchangeApiService;
        this.betfairMarketFacade = betfairMarketFacade;
        this.betfairAccountFacade = betfairAccountFacade;
        this.userService = userService;
    }

    @RequestMapping(value = Routing.BETFAIR_BET, method = RequestMethod.GET)
    public void getCurrentBets(ModelMap modelMap, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);

        new CurrentBetsTemplateModelBuilder()
                .withBets(betfairExchangeApiService.getCurrentBets(user.getBetfairPublicApiToken()))
                .withRunners(betfairMarketFacade.getRunnerNames())
                .build(modelMap);
    }

    @RequestMapping(value = Routing.BETFAIR_ACCOUNT_WALLET, method = RequestMethod.GET)
    public void getAccountWallets(ModelMap modelMap, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        modelMap.addAttribute(ModelAttributeName.accountWallets, betfairAccountFacade.getWallets(user));
    }

    @RequestMapping(value = Routing.BETFAIR_BET, method = RequestMethod.POST)
    public @ResponseBody BetPlacementResult placeBet(HttpServletRequest request, BetLittle bet) {
        User user = userService.getCurrentUser(request);
        BetPlacementResult betPlacementResult = bettingFacade.placeABet(user, bet);

        return betPlacementResult;
    }

    @RequestMapping(value = Routing.BETFAIR_CANCEL_BET, method = RequestMethod.POST)
    public @ResponseBody StatusJsonResponse cancelBet(HttpServletRequest request, String betId) {
        long betIdAsLong = NumberUtils.toLong(betId, -1);
        if(betIdAsLong > 0) {
            User user = userService.getCurrentUser(request);
            if(user == null) {
                return new StatusJsonResponse("USER_NOT_LOGGED_IN");
            }

            String betCancellationStatus = exchangeApiService.cancelBet(user.getBetfairPublicApiToken(), betIdAsLong);
            return new StatusJsonResponse(betCancellationStatus);
        }
        return new StatusJsonResponse("INVALID_BET_ID");
    }
}
