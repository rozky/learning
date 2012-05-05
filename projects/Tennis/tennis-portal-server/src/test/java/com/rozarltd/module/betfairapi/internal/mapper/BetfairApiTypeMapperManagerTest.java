package com.rozarltd.module.betfairapi.internal.mapper;

import com.betfair.publicapi.exchange.types.Market;
import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.module.betfairapi.internal.mapper.betfair.BFMarketTypeMapper;
import com.rozarltd.module.betfairapi.internal.mapper.betfair.BFTypeMapperManager;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BetfairApiTypeMapperManagerTest {
    private static ObjectTypeMapperManager typeMapperManager;

    @BeforeClass
    public static void beforeClass() {
        typeMapperManager = new BFTypeMapperManager();
    }

    @Test
    public void shouldReturnCorrectMapperForMarketToBetfairMarketTypeConversion() {
        // given - mapper manager instance exists

        // when
        ObjectTypeMapper<Market, BetfairMarket> mapper = typeMapperManager.getMapper(Market.class, BetfairMarket.class);

        // then
        assertThat(mapper.getClass().getName(), is(BFMarketTypeMapper.class.getName()));
    }

    @Test
    public void shouldReturnNullWhenThereIsNoMapperForGivenInAndOutTypes() {
        // given
        Class<String> inType = String.class;
        Class<Integer> outType = Integer.class;

        // when
        ObjectTypeMapper<String, Integer> mapper = typeMapperManager.getMapper(inType, outType);

        // then
        assertThat(mapper, Matchers.nullValue());
    }

    @Test
    public void shouldReturnNullWhenInAndOutArgumentAreNull() {
        // given
        Class<?> inType = null;
        Class<?> outType = null;

        // when
        ObjectTypeMapper<?, ?> mapper = typeMapperManager.getMapper(inType, outType);

        // then
        assertThat(mapper, Matchers.nullValue());
    }
}
