package com.rozarltd.module.betfairdata.strategy;

import com.rozarltd.module.betfairdata.parser.BetfairDataRow;

import java.util.HashMap;
import java.util.Map;

public class BetfairDataProcessor {

    public Map<Integer, MarketData> process(Iterable<BetfairDataRow> dataRow) {
        Map<Integer, MarketData> markets = new HashMap<Integer, MarketData>();

        for (BetfairDataRow row : dataRow) {
            processRow(markets, row);
        }

        return markets;
    }

    private void processRow(Map<Integer, MarketData> markets, BetfairDataRow dataRow) {
        MarketData market = markets.get(dataRow.getEventId());
        if(market == null) {
            market = new MarketData(dataRow.getEventId(), dataRow.getFullMarketName());
            markets.put(dataRow.getEventId(), market);
        }
        else {
            market.update(dataRow);
        }
    }
}
