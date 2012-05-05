package com.rozarltd.repository.converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.domain.market.BetfairRunner;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class BetfairMarketWriteConverter implements Converter<BetfairMarket, DBObject> {

    @Override
    public DBObject convert(BetfairMarket source) {
        DBObject target = new BasicDBObject();
        target.put("marketId", source.getMarketId());
        target.put("startAt", source.getStartAtDate());
        target.put("menuPath", source.getMenuPath());
        target.put("eventHierarchy", source.getEventHierarchy());
        target.put("exchangeId", source.getExchangeId());

        // convert runners
        List<BetfairRunner> sourceRunners = source.getRunners();
        if(sourceRunners != null && !sourceRunners.isEmpty()) {
            List<DBObject> targetRunners = new ArrayList<DBObject>();
            for(BetfairRunner sourceRunner: sourceRunners) {
                DBObject targetRunner = new BasicDBObject();
                targetRunner.put("id", sourceRunner.getRunnerId());
                targetRunner.put("name", source.getRunners());

                targetRunners.add(targetRunner);
            }

            target.put("runners", targetRunners);
        }

        return target;
    }
}
