package com.rozarltd.betfairrestapi.domain.response;

import com.rozarltd.betfairrestapi.domain.market.BetfairRestApiMarket;

public class GetMarketResponse {
    private BetfairRestApiMarket market;

 	public BetfairRestApiMarket getMarket(){
		return this.market;
	}
	public void setMarket(BetfairRestApiMarket market){
		this.market = market;
	}
}
