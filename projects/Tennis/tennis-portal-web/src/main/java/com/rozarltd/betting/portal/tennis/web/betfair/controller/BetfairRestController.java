package com.rozarltd.betting.portal.tennis.web.betfair.controller;

import com.rozarltd.account.BetfairUser;
import com.rozarltd.betting.common.domain.BetParams;
import com.rozarltd.betting.portal.tennis.web.ModelAttributeName;
import com.rozarltd.betting.portal.tennis.web.Routing;
import com.rozarltd.betting.portal.tennis.web.betfair.domain.StatusResponse;
import com.rozarltd.betting.portal.tennis.web.betfair.model.CurrentBetsTemplateModelBuilder;
import com.rozarltd.betting.portal.tennis.web.service.UserService;
import com.rozarltd.betting.service.BetPlacementResult;
import com.rozarltd.betting.service.BettingFacade;
import com.rozarltd.betting.service.MarketService;
import com.rozarltd.module.betfairapi.service.BFExchangeApiService;
import com.rozarltd.module.frontendservices.AccountFacade;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//@Controller
public class BetfairRestController{
    private BFExchangeApiService exchangeApiService;
    private BettingFacade bettingFacade;
    private BFExchangeApiService betfairExchangeApiService;
    private MarketService betfairMarketFacade;
    private AccountFacade betfairAccountFacade;
    private UserService userService;

    @Autowired
    public BetfairRestController(BFExchangeApiService exchangeApiService,
                                  BettingFacade bettingFacade,
                                 BFExchangeApiService betfairExchangeApiService,
                                 MarketService betfairMarketFacade,
                                 AccountFacade betfairAccountFacade,
                                 UserService userService) {
        this.exchangeApiService = exchangeApiService;
        this.bettingFacade = bettingFacade;
        this.betfairExchangeApiService = betfairExchangeApiService;
        this.betfairMarketFacade = betfairMarketFacade;
        this.betfairAccountFacade = betfairAccountFacade;
        this.userService = userService;
    }

    @RequestMapping(value = Routing.BETFAIR_BET, method = RequestMethod.GET)
    public void getCurrentBets(ModelMap modelMap, HttpServletRequest request) {
        BetfairUser user = userService.getCurrentUser(request);

        new CurrentBetsTemplateModelBuilder()
                .withBets(betfairExchangeApiService.getCurrentBets(user.getPublicApiToken()))
                .withRunners(betfairMarketFacade.getRunnerNames())
                .build(modelMap);
    }

    @RequestMapping(value = Routing.BETFAIR_ACCOUNT_WALLET, method = RequestMethod.GET)
    public void getAccountWallets(ModelMap modelMap, HttpServletRequest request) {
        BetfairUser user = userService.getCurrentUser(request);
        modelMap.addAttribute(ModelAttributeName.accountWallets, betfairAccountFacade.getWallets(user));
    }

    @RequestMapping(value = Routing.BETFAIR_BET, method = RequestMethod.POST)
    public @ResponseBody BetPlacementResult placeBet(HttpServletRequest request, BetParams bet) {
        BetfairUser user = userService.getCurrentUser(request);
        BetPlacementResult betPlacementResult = bettingFacade.placeBet(user, bet);

        return betPlacementResult;
    }

    @RequestMapping(value = Routing.BETFAIR_CANCEL_BET, method = RequestMethod.POST)
    public @ResponseBody StatusResponse cancelBet(HttpServletRequest request, String betId) {
        long betIdAsLong = NumberUtils.toLong(betId, -1);
        if(betIdAsLong > 0) {
            BetfairUser user = userService.getCurrentUser(request);
            if(user == null) {
                return new StatusResponse("USER_NOT_LOGGED_IN");
            }

            String betCancellationStatus = exchangeApiService.cancelBet(user.getPublicApiToken(), betIdAsLong);
            return new StatusResponse(betCancellationStatus);
        }
        return new StatusResponse("INVALID_BET_ID");
    }

    @RequestMapping(value = Routing.REST_BETFAIR_MARKET, method = RequestMethod.GET)
    public void getTodayMarkets(ModelMap modelMap) {
//        Set<BetfairMarket> matchOddMarkets = betfairMarketFacade.getMatchOddMarkets();
//        modelMap.addAttribute(ModelAttributeName.markets, MarketViewAdapter.transform(matchOddMarkets));
    }
}
