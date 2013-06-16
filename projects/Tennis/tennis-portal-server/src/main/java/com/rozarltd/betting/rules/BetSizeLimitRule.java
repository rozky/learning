package com.rozarltd.betting.rules;

import com.google.common.base.Optional;
import com.rozarltd.account.BetfairUser;
import com.rozarltd.betting.common.domain.BetParams;

public class BetSizeLimitRule implements BettingRule {
    public static final double MAX_BET_SIZE = 600;

    @Override
    public Optional<RuleViolation> verify(BetfairUser user, BetParams placeBetDetails) {
        if(placeBetDetails != null && placeBetDetails.getStake() > MAX_BET_SIZE) {
            String violationMessage = String.format("Your bet of £%s  is more than maximum allowed bet size £%s.",
                    MAX_BET_SIZE, placeBetDetails.getStake());

            return Optional.of(new RuleViolation(violationMessage));
        }

        return Optional.absent();
    }
}
