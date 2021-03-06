package com.rozarltd.betting.service;

import com.google.inject.Inject;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.repository.BetfairMarketRepository;
import com.rozarltd.util.java.lang.CollectionUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TennisMarketReplicationService implements MarketReplicationService {
    private TennisMarketService marketFacade;
    private BetfairMarketRepository marketRepository;

    @Inject
    @Autowired
    public TennisMarketReplicationService(TennisMarketService marketFacade,
                                          BetfairMarketRepository marketRepository) {
        this.marketFacade = marketFacade;
        this.marketRepository = marketRepository;
    }

    @Override
    public void replicateTodayMarkets() {
        List<BetfairMarket> todayMarkets = marketFacade.getTodayMarkets();

        List<Integer> storedMarkets = getAlreadyReplicatedTodayMarketIds();

        if(!CollectionUtilities.isEmpty(todayMarkets)) {
            for(BetfairMarket market: todayMarkets) {
                if(!storedMarkets.contains(market.getMarketId())) {
                    marketRepository.save(market);
                }
            }
        }
    }

    private List<Integer> getAlreadyReplicatedTodayMarketIds() {
//        List<BetfairMarket> storedMarkets =
//                marketRepository.findByStartAtBetween(DateUtilities.today().getTime(), DateUtilities.tomorrow().getTime());
//
//        List<Integer> marketIds = FunctionalIterables.make(storedMarkets).map(new Function<BetfairMarket, Integer>() {
//            @Override
//            public Integer apply(BetfairMarket input) {
//                return input.getMarketId();
//            }
//        }).toList();
//
//        return marketIds;

        return null;
    }
}
