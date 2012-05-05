package com.rozarltd.module.betfairdata.repository;

import com.rozarltd.module.betfairdata.strategy.MarketData;
import org.springframework.data.repository.CrudRepository;

public interface MarketDataRepository extends CrudRepository<MarketData, Integer> {
}
