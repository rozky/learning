package com.rozarltd.betting.rules;

import com.google.common.base.Optional;
import com.rozarltd.account.BetfairUser;
import com.rozarltd.betting.common.domain.BetParams;

public interface BettingRule {
    public Optional<RuleViolation> verify(BetfairUser user, BetParams placeBetDetails);
}
