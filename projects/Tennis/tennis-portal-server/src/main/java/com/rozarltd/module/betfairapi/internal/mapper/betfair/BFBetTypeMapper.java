package com.rozarltd.module.betfairapi.internal.mapper.betfair;

import com.betfair.publicapi.exchange.types.Bet;
import com.rozarltd.module.betfairapi.domain.bet.BetfairBet;
import com.rozarltd.module.betfairapi.internal.mapper.ObjectTypeMapperSupport;
import com.rozarltd.util.java.lang.DateUtilities;

public class BFBetTypeMapper extends ObjectTypeMapperSupport<Bet, BetfairBet> {

    @Override
    public BetfairBet mapFrom(Bet bet) {
        BetfairBet betfairBet = new BetfairBet();
        betfairBet.setBetId(bet.getBetId());
        betfairBet.setMarketId(bet.getMarketId());
        betfairBet.setSelectionId(bet.getSelectionId());
        betfairBet.setMarketName(bet.getMarketName());
        betfairBet.setFullMarketName(bet.getFullMarketName());
        betfairBet.setSelectionName(bet.getSelectionName());
        betfairBet.setMatchedSize(bet.getMatchedSize());
        betfairBet.setPrice(bet.getPrice());
        betfairBet.setAveragePrice(bet.getAvgPrice());
        betfairBet.setProfitAndLost(bet.getProfitAndLoss());
        betfairBet.setMarketType(bet.getMarketType().name());
        betfairBet.setBetStatus(bet.getBetStatus().name());
        betfairBet.setBetType(bet.getBetType().name());
        betfairBet.setPlacedDate(DateUtilities.getDate(bet.getPlacedDate()));
        betfairBet.setSettledData(DateUtilities.getDate(bet.getSettledDate()));

        return betfairBet;
    }
}
