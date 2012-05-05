package com.rozarltd.mongodb;

import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import org.springframework.data.repository.CrudRepository;

public interface BetfairMarketRepository extends CrudRepository<BetfairMarket, Integer> {
}
