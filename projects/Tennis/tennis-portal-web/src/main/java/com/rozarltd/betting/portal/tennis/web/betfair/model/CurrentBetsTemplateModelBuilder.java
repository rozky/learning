package com.rozarltd.betting.portal.tennis.web.betfair.model;

import com.rozarltd.betfairapi.domain.bet.BetfairBet;
import com.rozarltd.betting.portal.tennis.web.ModelAttributeName;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

public class CurrentBetsTemplateModelBuilder {
    private List<BetfairBet> currentBets;
    private Map<Integer, String> runners;

    public CurrentBetsTemplateModelBuilder withBets(List<BetfairBet> currentBets) {
        this.currentBets = currentBets;
        return this;
    }

    public CurrentBetsTemplateModelBuilder withRunners(Map<Integer, String> runners) {
        this.runners = runners;
        return this;
    }

    public void build(ModelMap modelMap) {
        Assert.notEmpty(runners);

        modelMap.addAttribute(ModelAttributeName.hasCurrentBets, currentBets != null && currentBets.size() > 0);
        modelMap.addAttribute(ModelAttributeName.currentBets, currentBets);
        modelMap.addAttribute(ModelAttributeName.runnerNames, runners);
    }
}
