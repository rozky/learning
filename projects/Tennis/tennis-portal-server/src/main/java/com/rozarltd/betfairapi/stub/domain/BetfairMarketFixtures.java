package com.rozarltd.betfairapi.stub.domain;

import com.rozarltd.betfairapi.domain.ExchangeIdEnum;
import com.rozarltd.betfairapi.domain.market.BetfairMarket;

public class BetfairMarketFixtures {
    private static final int DEFAULT_MARKET_ID = 105000000;

    public static BetfairMarket betfairMarket() {
        return betfairMarket(DEFAULT_MARKET_ID);
    }

    public static BetfairMarket inPlayBetfairMarket(int marketId) {
        BetfairMarket market = betfairMarket();
        market.setMarketId(marketId);
        market.setBetDelay("5");

        return market;
    }

    public static BetfairMarket betfairMarket(Integer... eventHierarchyEventIds) {
        BetfairMarket market = betfairMarket();
        market.setEventHierarchy(EventHierarchyBuilder.build(eventHierarchyEventIds));

        return market;
    }

    public static BetfairMarket betfairMarket(int marketId) {
        BetfairMarket market = new BetfairMarket();
        market.setMarketId(marketId);
        market.setMarketName("Match Odds");
        market.setMarketType("O");
        market.setMarketStatus("ACTIVE");
        market.setStartAt(1325415600000L);
        market.setMenuPath("\\Tennis\\Group A\\Wimbledon\\Djokovic vs Nadal");
        market.setEventHierarchy("/2/26765986/26765989/104142702");
        market.setBetDelay("0");
        market.setExchangeId(String.valueOf(ExchangeIdEnum.uk.getId()));
        market.setCountryCodeISO3("");
        market.setLastRefreshedAt("1325976355856");
        market.setNumberOfRunners("2");
        market.setNumberOfWinners("1");
        market.setTotalAmountMatched("1505.89");
        market.setBspMarket("N");
        market.setTurningInPlay("Y");
        return market;
    }
}
