package com.rozarltd.betting.rules;

import com.google.common.base.Optional;
import com.rozarltd.betting.domain.BetLittle;
import com.rozarltd.betting.service.RuleViolation;
import com.rozarltd.domain.account.User;

public interface BettingRule {
    public Optional<RuleViolation> verify(User user, BetLittle placeBetDetails);
}
