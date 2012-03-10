package com.rozarltd.betfairapi.internal.mapper.betfair;

import com.betfair.publicapi.exchange.types.Bet;
import com.betfair.publicapi.exchange.types.MUBet;
import com.betfair.publicapi.exchange.types.Market;
import com.rozarltd.betfairapi.domain.bet.BetfairBet;
import com.rozarltd.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.betfairapi.internal.mapper.ObjectTypeMapper;
import com.rozarltd.betfairapi.internal.mapper.ObjectTypeMapperManager;
import com.rozarltd.betfairrestapi.domain.bet.BFRestBet;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BFTypeMapperManager implements ObjectTypeMapperManager {
    private Map<Key, ObjectTypeMapper> mappers;

    @Override
    public <IN_TYPE, OUT_TYPE> ObjectTypeMapper<IN_TYPE, OUT_TYPE> getMapper(Class<IN_TYPE> inType, Class<OUT_TYPE> outType) {
        if(mappers == null) {
            registerMappers();
        }

        return (ObjectTypeMapper<IN_TYPE, OUT_TYPE>) mappers.get(new Key(inType, outType));
    }

    private synchronized void registerMappers() {
        if(mappers == null) {
            mappers = new HashMap<Key, ObjectTypeMapper>();
        }

        mappers.put(new Key(Market.class, BetfairMarket.class), new BFMarketTypeMapper());
        mappers.put(new Key(Bet.class, BetfairBet.class), new BFBetTypeMapper());
        mappers.put(new Key(MUBet.class, BetfairBet.class), new BFMUBetTypeMapper());
        mappers.put(new Key(BFRestBet.class, BetfairBet.class), new BFRestBetTypeMapper());
    }

    private class Key {
        private Class inputType;
        private Class outputType;

        public Key(Class inputType, Class outputType) {
            this.inputType = inputType;
            this.outputType = outputType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (inputType != null ? !inputType.equals(key.inputType) : key.inputType != null) return false;
            if (outputType != null ? !outputType.equals(key.outputType) : key.outputType != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = inputType != null ? inputType.hashCode() : 0;
            result = 31 * result + (outputType != null ? outputType.hashCode() : 0);
            return result;
        }
    }
}
