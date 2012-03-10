package com.rozarltd.betfairapi.internal.mapper.betfair;

import com.rozarltd.betfairapi.domain.bet.BetfairBet;
import com.rozarltd.betfairapi.internal.mapper.ObjectTypeMapper;
import com.rozarltd.betfairrestapi.domain.bet.BFRestBet;
import org.apache.commons.lang.math.NumberUtils;

public class BFRestBetTypeMapper implements ObjectTypeMapper<BFRestBet, BetfairBet> {

    @Override
    public BetfairBet mapFrom(BFRestBet bet) {
        if(bet == null) {
            return null;
        }

        BetfairBet betfairBet = new BetfairBet();
        betfairBet.setBetId(NumberUtils.toLong(bet.getId()));
        betfairBet.setPrice(NumberUtils.toDouble(bet.getPrice()));
        betfairBet.setAveragePrice(NumberUtils.toDouble(bet.getAveragePriceMatched()));
        betfairBet.setSize(NumberUtils.toDouble(bet.getSize()));
        betfairBet.setBetStatus(bet.getBetStatus());
        betfairBet.setBetType(bet.getBetType());
        betfairBet.setMarketId(NumberUtils.toInt(bet.getMarketId()));
        betfairBet.setSelectionId(NumberUtils.toInt(bet.getSelectionId()));
        betfairBet.setMatchedSize(NumberUtils.toDouble(bet.getSizeMatched()));
        betfairBet.setProfitAndLost(NumberUtils.toDouble(bet.getProfitAndLoss()));

        return betfairBet;
    }
}
