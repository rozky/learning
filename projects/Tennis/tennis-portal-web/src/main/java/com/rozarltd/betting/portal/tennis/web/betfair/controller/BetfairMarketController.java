package com.rozarltd.betting.portal.tennis.web.betfair.controller;

import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.domain.market.BetfairRootEventId;
import com.rozarltd.module.betfairapi.service.AccountFacade;
import com.rozarltd.module.betfairapi.service.AccountService;
import com.rozarltd.module.betfairapi.service.BFExchangeApiService;
import com.rozarltd.betting.service.MarketService;
import com.rozarltd.betting.portal.tennis.web.ModelAttributeName;
import com.rozarltd.betting.portal.tennis.web.Routing;
import com.rozarltd.betting.portal.tennis.web.service.UserService;
import com.rozarltd.account.User;
import com.rozarltd.domain.market.Market;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class BetfairMarketController {

    private MarketService betfairMarketFacade;
    private AccountFacade betfairAccountFacade;
    private BFExchangeApiService betfairExchangeApiService;
    private AccountService betfairAccountService;
    private UserService userService;

    @Autowired
    public BetfairMarketController(MarketService betfairMarketFacade,
                                   BFExchangeApiService betfairExchangeApiService,
                                   AccountService betfairAccountService,
                                   AccountFacade betfairAccountFacade, UserService userService) {
        this.betfairMarketFacade = betfairMarketFacade;
        this.betfairExchangeApiService = betfairExchangeApiService;
        this.betfairAccountService = betfairAccountService;
        this.betfairAccountFacade = betfairAccountFacade;
        this.userService = userService;
    }

    @RequestMapping(value = Routing.BETFAIR_MARKET_DEVELOPMENT, method = RequestMethod.GET)
    public void renderDevelopment(HttpServletRequest request, ModelMap modelMap) {

        User currentUser = userService.getCurrentUser(request);

//        List<BetfairBet> currentBets = betfairExchangeApiService.getCurrentBets(currentUser.getBetfairPublicApiToken());
//        modelMap.addAttribute(ModelAttributeName.hasCurrentBets, currentBets != null && currentBets.size() > 0);
//        modelMap.addAttribute(ModelAttributeName.currentBets, currentBets);

        Map<Integer, String> runnerNames = betfairMarketFacade.getRunnerNames();
        modelMap.addAttribute(ModelAttributeName.runnerNames, runnerNames);


        Set<Market> matchOddMarkets = betfairMarketFacade.getMatchOddMarkets();
        modelMap.addAttribute(ModelAttributeName.markets, matchOddMarkets);

        // wallets
        // modelMap.addAttribute(ModelAttributeName.accountWallets, betfairAccountFacade.getWallets(currentUser));
    }

    @RequestMapping(value = Routing.BETFAIR_MARKET, method = RequestMethod.GET)
    public void renderMarkets(ModelMap modelMap) {
        Map<String,List<BetfairMarket>> markets = betfairMarketFacade.getMarkets(BetfairRootEventId.tennis.getEventId());

        modelMap.addAttribute(ModelAttributeName.markets,markets);
        modelMap.addAttribute(ModelAttributeName.marketsCount, markets.size());
    }
}
