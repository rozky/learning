package com.rozarltd.repository;

import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BetfairAccountStatementRepository extends CrudRepository<AccountStatementRecord, Integer> {

    @Transactional(readOnly = true)
    @Query("select max(e.settledAt) from AccountStatementRecord e")
    Long getMaxSettledDate();

    List<AccountStatementRecord> findBySettledAtBetween(long from, long to);
}
