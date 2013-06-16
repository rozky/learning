package com.rozarltd.betting.service;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.rozarltd.account.BetfairUser;
import com.rozarltd.betting.common.domain.BetParams;
import com.rozarltd.betting.rules.BetSizeLimitRule;
import com.rozarltd.betting.rules.BettingRule;
import com.rozarltd.betting.rules.RuleViolation;
import com.rozarltd.module.betfairapi.domain.bet.BetfairBet;
import com.rozarltd.module.betfairapi.internal.mapper.ObjectTypeMapperManager;
import com.rozarltd.module.betfairapi.service.ServiceResponse;
import com.rozarltd.module.betfairrestapi.BetfairRestApi;
import com.rozarltd.module.betfairrestapi.domain.bet.BFRestBet;
import com.rozarltd.module.betfairrestapi.domain.response.PlaceBetResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RuleBasedBettingFacade implements BettingFacade {
    private final BetfairRestApi betfairRestApiService;
    private final ObjectTypeMapperManager objectTypeMapperManager;

    private Set<BettingRule> bettingRules = new TreeSet<BettingRule>();

    @Inject
    public RuleBasedBettingFacade(BetfairRestApi betfairRestApiService, ObjectTypeMapperManager objectTypeMapperManager) {
        this.betfairRestApiService = betfairRestApiService;
        this.objectTypeMapperManager = objectTypeMapperManager;

        bettingRules.add(new BetSizeLimitRule());
    }

    @Override
    public BetPlacementResult placeBet(BetfairUser user, BetParams bet) {

        // check rules
        List<RuleViolation> violations = checkBettingRules(user, bet);
        if(violations.size() > 0) {
            return new BetPlacementResult(violations);
        }

        // place bet
        ServiceResponse<PlaceBetResponse> response = betfairRestApiService.placeBet(user.getPublicApiToken(), bet);

        if(response.isError()) {
            return new BetPlacementResult(response.getError().getErrorCode());
        }


        BetfairBet betfairBet = objectTypeMapperManager.getMapper(BFRestBet.class, BetfairBet.class).mapFrom(response.getResponse().getBet());

        return new BetPlacementResult(betfairBet);
    }

    private List<RuleViolation> checkBettingRules(BetfairUser user, BetParams placeBetDetails) {
        List<RuleViolation> violations = new ArrayList<RuleViolation>();
        if(bettingRules != null && bettingRules.size() > 0) {
            for(BettingRule rule: bettingRules) {
                Optional<RuleViolation> ruleVerificationResult = rule.verify(user, placeBetDetails);
                if(ruleVerificationResult.isPresent()) {
                    violations.add(ruleVerificationResult.get());
                }
            }
        }

        return violations;
    }

    private class MaxAllowedMarketsWithBet implements BettingRule {
        private static final double MAXIMUM = 3;

        @Override
        public Optional<RuleViolation> verify(BetfairUser user, BetParams placeBetDetails) {
            return null;
        }
    }

    // MarketExposureLimitRule
    // ExposureLimitRule
    // DailyBetLimitRule
    // DailyLostLimitRule


    // MaxAllowedNumberOfOpenedBets
    // MaxAllowedDailyLost
    // BetAgainstHeavyFavoriteNotAllowed
    // MaxAllowedExposure
    // OnlyMatchOddsBetsAreAllowed
    // MaxNumberOfDailyBetsExceeded
    // NumberOgBetsPerFiveMinutesExceeded
    // LowLiquidityRule
}
