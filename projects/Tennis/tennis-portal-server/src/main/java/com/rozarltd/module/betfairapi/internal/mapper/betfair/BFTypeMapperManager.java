package com.rozarltd.module.betfairapi.internal.mapper.betfair;

import com.betfair.publicapi.exchange.types.AccountStatementItem;
import com.betfair.publicapi.exchange.types.Bet;
import com.betfair.publicapi.exchange.types.MUBet;
import com.betfair.publicapi.exchange.types.Market;
import com.rozarltd.module.betfairapi.domain.account.statement.AccountStatementRecord;
import com.rozarltd.module.betfairapi.domain.bet.BetfairBet;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.internal.mapper.ObjectTypeMapper;
import com.rozarltd.module.betfairapi.internal.mapper.ObjectTypeMapperManager;
import com.rozarltd.module.betfairrestapi.domain.bet.BFRestBet;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BFTypeMapperManager implements ObjectTypeMapperManager {
    private Map<Key, ObjectTypeMapper> mappers = new HashMap<Key, ObjectTypeMapper>() {
        {
            put(new Key(Market.class, BetfairMarket.class), new BFMarketTypeMapper());
            put(new Key(Bet.class, BetfairBet.class), new BFBetTypeMapper());
            put(new Key(MUBet.class, BetfairBet.class), new BFMUBetTypeMapper());
            put(new Key(BFRestBet.class, BetfairBet.class), new BFRestBetTypeMapper());
            put(new Key(AccountStatementItem.class, AccountStatementRecord.class), new BFAccountStatementItemTypeMapper());
        }
    };

    @Override
    public <IN, OUT> ObjectTypeMapper<IN, OUT> getMapper(Class<IN> inType, Class<OUT> outType) {
        return (ObjectTypeMapper<IN, OUT>) mappers.get(new Key(inType, outType));
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
