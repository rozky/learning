package com.rozarltd.repository;

import com.rozarltd.entity.analyse.DayBettingStats;
import org.springframework.data.repository.CrudRepository;

public interface DayBettingStatsRepository extends CrudRepository<DayBettingStats, Integer> {

//    @Query(value = "{ 'startAt' : { $gte : ?0, $lt: ?1 } }",  fields="{ 'marketId' : 1 }")
//    List<BetfairMarket> findByStartAtBetween(long startDate, long endDate);
}
