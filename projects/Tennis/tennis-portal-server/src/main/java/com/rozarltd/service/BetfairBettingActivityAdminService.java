package com.rozarltd.service;

import com.rozarltd.account.BetfairUser;
import com.rozarltd.domain.CopyBettingActivityResult;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.module.betfairapi.service.BetfairAccountApi;
import com.rozarltd.repository.BetfairAccountStatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Betting activity admin that operates on bets placed on the Betfair
 */
public class BetfairBettingActivityAdminService implements BettingActivityAdminService<BetfairUser> {

    @Autowired private BetfairAccountStatementRepository accountStatementRepository;
    @Autowired private BetfairAccountApi betfairAccountApi;

    /**
     * {@inheritDoc}
     */
    @Override
    public CopyBettingActivityResult copyBettingActivity(BetfairUser user, Date from, Date to) {
        Assert.notNull(user);
        Assert.notNull(from);

        Date toDate = (to == null) ? new Date() : to;

        List<AccountStatementRecord> statement =
                betfairAccountApi.getWalletStatement(user.getPublicApiToken(), from, toDate);

        int count = 0;
        if(statement != null && statement.size() > 0) {
            count = statement.size();
            for (AccountStatementRecord record : statement) {
                // todo - verify that this one doesn't exists yet
                accountStatementRepository.save(record);
            }
        }

        return new CopyBettingActivityResult(from, to, count);
    }

    @Override
    public CopyBettingActivityResult copyBettingActivity(BetfairUser user) {

        return null;
    }
}
