package com.rozarltd.module.betfairapi.internal.mapper.betfair;

import com.betfair.publicapi.exchange.types.MUBet;
import com.rozarltd.module.betfairapi.domain.bet.BetfairBet;
import com.rozarltd.module.betfairapi.internal.mapper.ObjectTypeMapperSupport;
import com.rozarltd.util.java.lang.DateUtilities;
import com.rozarltd.util.java.lang.DoubleUtils;

public class BFMUBetTypeMapper extends ObjectTypeMapperSupport<MUBet, BetfairBet> {

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
        betfairBet.setPlacedDate(DateUtilities.getDate(bet.getPlacedDate()));
        betfairBet.setProfitAndLost(DoubleUtils.valueOf(bet.getSize() * (bet.getPrice() - 1d), 2));

        return betfairBet;
    }
}