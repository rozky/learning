package com.rozarltd.repository;

import com.rozarltd.betting.domain.stats.DailyBettingReport;
import org.springframework.data.repository.CrudRepository;

public interface DailyBettingDataRepository extends CrudRepository<DailyBettingReport, Integer> {
}
