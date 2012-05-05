package com.rozarltd.module.betfairrestapi.domain.market;

public class BetfairRestApiMarketRunnerPrice {
    private String bestBackAmount;
   	private String bestBackPrice;
   	private String bestLayAmount;
   	private String bestLayPrice;
   	private String lastPriceMatched;
   	private String marketId;
   	private String orderIndex;
   	private String selectionId;
   	private String totalAmountMatched;
   	private String withdrawn;

 	public String getBestBackAmount(){
		return this.bestBackAmount;
	}
	public void setBestBackAmount(String bestBackAmount){
		this.bestBackAmount = bestBackAmount;
	}
 	public String getBestBackPrice(){
		return this.bestBackPrice;
	}
	public void setBestBackPrice(String bestBackPrice){
		this.bestBackPrice = bestBackPrice;
	}
 	public String getBestLayAmount(){
		return this.bestLayAmount;
	}
	public void setBestLayAmount(String bestLayAmount){
		this.bestLayAmount = bestLayAmount;
	}
 	public String getBestLayPrice(){
		return this.bestLayPrice;
	}
	public void setBestLayPrice(String bestLayPrice){
		this.bestLayPrice = bestLayPrice;
	}
 	public String getLastPriceMatched(){
		return this.lastPriceMatched;
	}
	public void setLastPriceMatched(String lastPriceMatched){
		this.lastPriceMatched = lastPriceMatched;
	}
 	public String getMarketId(){
		return this.marketId;
	}
	public void setMarketId(String marketId){
		this.marketId = marketId;
	}
 	public String getOrderIndex(){
		return this.orderIndex;
	}
	public void setOrderIndex(String orderIndex){
		this.orderIndex = orderIndex;
	}
 	public String getSelectionId(){
		return this.selectionId;
	}
	public void setSelectionId(String selectionId){
		this.selectionId = selectionId;
	}
 	public String getTotalAmountMatched(){
		return this.totalAmountMatched;
	}
	public void setTotalAmountMatched(String totalAmountMatched){
		this.totalAmountMatched = totalAmountMatched;
	}
 	public String getWithdrawn(){
		return this.withdrawn;
	}
	public void setWithdrawn(String withdrawn){
		this.withdrawn = withdrawn;
	}
}
