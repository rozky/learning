package com.rozarltd.roger.controller;

import com.rozarltd.service.BettingActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BettingGoalsController {
    @Autowired
    private BettingActivityService bettingActivityService;


    @RequestMapping(value = "/betting/goals", method = RequestMethod.GET)
    public void showMarketBettingAnalytics(ModelMap model) {
        List<BettingGoal> goals = new ArrayList<BettingGoal>();
        goals.add(new BettingGoal.Builder().description("Return of investment(ROI) over 5%").value("0.05").build());
        goals.add(new BettingGoal.Builder().description("Number of single selection markets over 70%").value("0.70").build());
        goals.add(new BettingGoal.Builder().description("Number of days ended in profit over 85%").value("0.85").build());
        goals.add(new BettingGoal.Builder().description("Number of winning bets over 85%").value("0.85").build());
        goals.add(new BettingGoal.Builder().description("Number of match odds bets over 98%").value("0.98").build());
        goals.add(new BettingGoal.Builder().description("Number of tennis bets over 95%").value("0.95").build());
        goals.add(new BettingGoal.Builder().description("Average number of bets per market 1.6").value("0.95").build());
        goals.add(new BettingGoal.Builder().description("Number of sure lose bets under 10%").value("0.95").build());
        goals.add(new BettingGoal.Builder().description("Bets with exposure over defined threshold lower than 10%").value("0.95").build());

        // todo - this is rule and not a goal , hmm
        goals.add(new BettingGoal.Builder().description("Exposure per market lower than 30% of current bank").value("0.95").build());


        model.put("goals", goals);
    }
}
