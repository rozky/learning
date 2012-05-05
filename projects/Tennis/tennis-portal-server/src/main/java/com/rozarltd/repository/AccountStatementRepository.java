package com.rozarltd.repository;

import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import org.springframework.data.repository.CrudRepository;

public interface AccountStatementRepository extends CrudRepository<AccountStatementRecord, Integer> {
}
