package com.rozarltd.module.betfairapi.service.market;

import com.google.common.base.Predicate;
import com.google.inject.Inject;
import com.googlecode.functionalcollections.FunctionalIterables;
import com.rozarltd.domain.market.MarketAdapter;
import com.rozarltd.domain.market.MarketRunner;
import com.rozarltd.module.betfairwebsite.exception.WebScrapingException;
import com.rozarltd.module.betfairwebsite.page.coupon.domain.TennisInPlayCoupon;
import com.rozarltd.module.betfairwebsite.service.BetfairWebsiteClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

@Component
public class MarketPricesRefresher implements MarketRefresher {
    private static final Logger logger = LoggerFactory.getLogger(MarketPricesRefresher.class);

    private BetfairWebsiteClient betfairWebsiteClient;

    @Autowired
    @Inject
    public MarketPricesRefresher(BetfairWebsiteClient betfairWebsiteClient) {
        this.betfairWebsiteClient = betfairWebsiteClient;
    }

    @Override
    public void refresh(Collection<MarketAdapter> markets) {
        Assert.notEmpty(markets);

        TennisInPlayCoupon tennisCoupon;
        try {
            tennisCoupon = betfairWebsiteClient.getTodayInPlayTennisCoupon();
        } catch (WebScrapingException e) {
            logger.error("Unable to refresh market runner prices because of the following problem: ", e);
            return;
        }

        for (MarketAdapter market : markets) {
            if (tennisCoupon.containsMarket(market.getMarketId())) {
                List<MarketRunner> newRunners = tennisCoupon.getMarketRunners(market.getMarketId());

                if (market.hasRunners()) {
                    updateRunnerPrices(market.getRunners(), newRunners);
                } else {
                    market.setRunners(newRunners);
                }
            } else {
                if (logger.isInfoEnabled()) {
                    logger.info(String.format("Not able to update market runner prices for market %s " +
                            "because the market was not found.", market.getMarketId()));
                }
            }
        }
    }

    private void updateRunnerPrices(List<MarketRunner> oldRunners, List<MarketRunner> newRunners) {
        for (MarketRunner oldRunner : oldRunners) {
            MarketRunner newRunner = FunctionalIterables.make(newRunners).find(new MarketRunnerFinder(oldRunner.getRunnerId()));
            if (newRunner != null) {
                oldRunner.setBestBackPrice(newRunner.getBestBackPrice());
                oldRunner.setBestLayPrice(newRunner.getBestLayPrice());

                if(oldRunner.getRunnerName() == null) {
                    oldRunner.setRunnerName(newRunner.getRunnerName());
                }
            }
        }
    }

    private static class MarketRunnerFinder implements Predicate<MarketRunner> {
        private final int runnerId;

        public MarketRunnerFinder(int runnerId) {
            this.runnerId = runnerId;
        }

        @Override
        public boolean apply(MarketRunner runner) {
            return runner != null && runnerId == runner.getRunnerId();
        }
    }
}
