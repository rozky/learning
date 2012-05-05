package com.rozarltd.module.betfairwebsite.page.market.domain;

public class MarketUpdates {
   	private Number betDelay;
   	private boolean bspReconciled;
   	private Number id;
   	private Number profitAndLossForSettledBets;
   	private String status;
   	private String totalMoneyMatched;

 	public Number getBetDelay(){
		return this.betDelay;
	}
	public void setBetDelay(Number betDelay){
		this.betDelay = betDelay;
	}
 	public boolean getBspReconciled(){
		return this.bspReconciled;
	}
	public void setBspReconciled(boolean bspReconciled){
		this.bspReconciled = bspReconciled;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public Number getProfitAndLossForSettledBets(){
		return this.profitAndLossForSettledBets;
	}
	public void setProfitAndLossForSettledBets(Number profitAndLossForSettledBets){
		this.profitAndLossForSettledBets = profitAndLossForSettledBets;
	}
 	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
 	public String getTotalMoneyMatched(){
		return this.totalMoneyMatched;
	}
	public void setTotalMoneyMatched(String totalMoneyMatched){
		this.totalMoneyMatched = totalMoneyMatched;
	}
}
