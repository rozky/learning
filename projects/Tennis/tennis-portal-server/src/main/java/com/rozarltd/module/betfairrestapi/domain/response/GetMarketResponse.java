package com.rozarltd.module.betfairrestapi.domain.response;

import com.rozarltd.module.betfairrestapi.domain.market.BetfairRestApiMarket;

public class GetMarketResponse {
    private BetfairRestApiMarket market;

 	public BetfairRestApiMarket getMarket(){
		return this.market;
	}
	public void setMarket(BetfairRestApiMarket market){
		this.market = market;
	}
}
