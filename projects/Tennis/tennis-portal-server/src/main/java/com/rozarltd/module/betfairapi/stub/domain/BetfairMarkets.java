package com.rozarltd.module.betfairapi.stub.domain;

import com.rozarltd.module.betfairapi.domain.ExchangeIdEnum;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

public class BetfairMarkets {
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

    public static BetfairMarket todayMarket(int marketId) {
        BetfairMarket market = new BetfairMarket();
        market.setMarketId(marketId);
        market.setMarketName("Match Odds");
        market.setMarketStatus("ACTIVE");
        market.setStartAt(new Date().getTime());
        return market;
    }

    public static BetfairMarket yesterdayMarket(int marketId) {
        BetfairMarket market = todayMarket(marketId);
        market.setStartAt(DateUtils.addDays(market.getStartAtDate(), -1).getTime());
        return market;
    }

    public static BetfairMarket tomorrowMarket(int marketId) {
        BetfairMarket market = todayMarket(marketId);
        market.setStartAt(DateUtils.addDays(market.getStartAtDate(), 1).getTime());
        return market;
    }

    public static BetfairMarket nextWeekMarket(int marketId) {
        BetfairMarket market = todayMarket(marketId);
        market.setStartAt(DateUtils.addDays(market.getStartAtDate(), 7).getTime());
        return market;
    }
}
