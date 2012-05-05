package com.rozarltd.module.betfairwebsite.page.market.domain;

public class WebMarketRunners {
   	private Number marketId;
   	private String name;
   	private Number runnerId;
   	private Number selectionId;
   	private boolean vacant;

 	public Number getMarketId(){
		return this.marketId;
	}
	public void setMarketId(Number marketId){
		this.marketId = marketId;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public Number getRunnerId(){
		return this.runnerId;
	}
	public void setRunnerId(Number runnerId){
		this.runnerId = runnerId;
	}
 	public Number getSelectionId(){
		return this.selectionId;
	}
	public void setSelectionId(Number selectionId){
		this.selectionId = selectionId;
	}
 	public boolean getVacant(){
		return this.vacant;
	}
	public void setVacant(boolean vacant){
		this.vacant = vacant;
	}
}
