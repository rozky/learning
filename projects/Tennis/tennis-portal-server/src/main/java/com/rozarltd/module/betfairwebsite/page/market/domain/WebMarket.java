package com.rozarltd.module.betfairwebsite.page.market.domain;

import java.util.List;

public class WebMarket{
   	private MarketUpdates marketupdates;
   	private List runnerprices;
   	private List runners;
   	private SportsParameters sportsParameters;
   	private SysParams sysparams;

 	public MarketUpdates getMarketupdates(){
		return this.marketupdates;
	}
	public void setMarketupdates(MarketUpdates marketupdates){
		this.marketupdates = marketupdates;
	}
 	public List getRunnerprices(){
		return this.runnerprices;
	}
	public void setRunnerprices(List runnerprices){
		this.runnerprices = runnerprices;
	}
 	public List getRunners(){
		return this.runners;
	}
	public void setRunners(List runners){
		this.runners = runners;
	}
 	public SportsParameters getSportsParameters(){
		return this.sportsParameters;
	}
	public void setSportsParameters(SportsParameters sportsParameters){
		this.sportsParameters = sportsParameters;
	}
 	public SysParams getSysparams(){
		return this.sysparams;
	}
	public void setSysparams(SysParams sysparams){
		this.sysparams = sysparams;
	}
}
