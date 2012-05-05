package com.rozarltd.repository;

import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BetfairMarketRepository extends CrudRepository<BetfairMarket, Integer> {

    @Query(value = "{ 'startAt' : { $gte : ?0, $lt: ?1 } }",  fields="{ 'marketId' : 1 }")
    List<BetfairMarket> findMarkets(long startDate, long endDate);
}
