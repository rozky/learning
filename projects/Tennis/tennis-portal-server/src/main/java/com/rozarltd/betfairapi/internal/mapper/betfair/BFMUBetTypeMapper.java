package com.rozarltd.betfairapi.internal.mapper.betfair;

import com.betfair.publicapi.exchange.types.MUBet;
import com.rozarltd.betfairapi.domain.bet.BetfairBet;
import com.rozarltd.betfairapi.internal.mapper.ObjectTypeMapper;
import com.rozarltd.util.java.lang.DateUtils;

import java.math.BigDecimal;

public class BFMUBetTypeMapper implements ObjectTypeMapper<MUBet, BetfairBet> {

    @Override
    public BetfairBet mapFrom(MUBet bet) {
        BetfairBet betfairBet = new BetfairBet();
        betfairBet.setBetId(bet.getBetId());
        betfairBet.setTransactionId(bet.getTransactionId());
        betfairBet.setMarketId(bet.getMarketId());
        betfairBet.setSelectionId(bet.getSelectionId());
        betfairBet.setSize(bet.getSize());
        betfairBet.setPrice(bet.getPrice());
        betfairBet.setBetStatus(bet.getBetStatus().name());
        betfairBet.setBetType(bet.getBetType().name());
        betfairBet.setPlacedDate(DateUtils.getDate(bet.getPlacedDate()));
        betfairBet.setProfitAndLost(BigDecimal.valueOf(bet.getSize() * (bet.getPrice() - 1d)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        return betfairBet;
    }
}