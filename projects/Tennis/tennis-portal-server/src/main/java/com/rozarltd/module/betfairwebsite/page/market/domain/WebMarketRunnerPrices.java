package com.rozarltd.module.betfairwebsite.page.market.domain;

import java.util.List;

public class WebMarketRunnerPrices {
   	private List bestBacks;
   	private List bestLays;
   	private Number marketId;
   	private Number runnerId;

 	public List getBestBacks(){
		return this.bestBacks;
	}
	public void setBestBacks(List bestBacks){
		this.bestBacks = bestBacks;
	}
 	public List getBestLays(){
		return this.bestLays;
	}
	public void setBestLays(List bestLays){
		this.bestLays = bestLays;
	}
 	public Number getMarketId(){
		return this.marketId;
	}
	public void setMarketId(Number marketId){
		this.marketId = marketId;
	}
 	public Number getRunnerId(){
		return this.runnerId;
	}
	public void setRunnerId(Number runnerId){
		this.runnerId = runnerId;
	}
}
