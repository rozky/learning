package com.rozarltd.betting.rules;

import com.google.common.base.Optional;
import com.rozarltd.betting.domain.BetRequest;
import com.rozarltd.account.User;

public interface BettingRule {
    public Optional<RuleViolation> verify(User user, BetRequest placeBetDetails);
}
