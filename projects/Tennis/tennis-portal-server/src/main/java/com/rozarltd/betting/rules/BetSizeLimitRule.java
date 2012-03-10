package com.rozarltd.betting.rules;

import com.google.common.base.Optional;
import com.rozarltd.betting.domain.BetLittle;
import com.rozarltd.betting.service.RuleViolation;
import com.rozarltd.domain.account.User;

public class BetSizeLimitRule implements BettingRule {
    private static final double MAX_BET_SIZE = 600;

    @Override
    public Optional<RuleViolation> verify(User user, BetLittle placeBetDetails) {
        if(placeBetDetails != null && placeBetDetails.getStake() > MAX_BET_SIZE) {
            String violationMessage = String.format("Your bet of £%s  is more than maximum allowed bet size £%s.",
                    MAX_BET_SIZE, placeBetDetails.getStake());

            Optional.of(new RuleViolation(violationMessage));
        }

        return Optional.absent();
    }
}
