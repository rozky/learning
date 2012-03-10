package com.rozarltd.betfairrestapi.domain.market;

public class BetfairRestApiMarketRunner {
    private String id;
   	private String marketId;
   	private String name;
   	private BetfairRestApiMarketRunnerPrice price;

 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getMarketId(){
		return this.marketId;
	}
	public void setMarketId(String marketId){
		this.marketId = marketId;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public BetfairRestApiMarketRunnerPrice getPrice(){
		return this.price;
	}
	public void setPrice(BetfairRestApiMarketRunnerPrice price){
		this.price = price;
	}
}
