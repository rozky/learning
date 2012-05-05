package com.rozarltd.betting.service;

import com.rozarltd.module.betfairapi.domain.bet.BetfairBet;
import com.rozarltd.betting.rules.RuleViolation;

import java.util.List;

public class BetPlacementResult {
    private static final String RULE_VIOLATION_STATUS = "BETTING_RULE_VIOLATION";
    private static final String OK_STATUS = "OK";

    private String status;
    private BetfairBet bet;
    private List<RuleViolation> bettingRuleViolations;

    BetPlacementResult(List<RuleViolation> bettingRuleViolations) {
        this.status = RULE_VIOLATION_STATUS;
        this.bettingRuleViolations = bettingRuleViolations;
    }

    BetPlacementResult(BetfairBet bet) {
        this.status = OK_STATUS;
        this.bet = bet;
    }

    BetPlacementResult(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public boolean isError() {
        return !"OK".equals(status);
    }

    public List<RuleViolation> getBettingRuleViolations() {
        return bettingRuleViolations;
    }

    public BetfairBet getBet() {
        return bet;
    }
}
