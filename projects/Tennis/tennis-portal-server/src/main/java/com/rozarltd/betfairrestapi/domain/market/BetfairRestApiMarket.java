package com.rozarltd.betfairrestapi.domain.market;

public class BetfairRestApiMarket {
    private String betDelay;
   	private String eventHierarchy;
   	private String eventName;
   	private String eventTypeId;
   	private String exchange;
   	private String goingInPlay;
   	private String id;
   	private String interval;
   	private String lastRefresh;
   	private String marketInfo;
   	private String marketStatus;
   	private String marketTime;
   	private String marketType;
   	private String menuPath;
   	private String name;
   	private BetfairRestApiMarketRunners runners;
   	private String startTime;
   	private String subtreeDepth;
   	private String timezone;
   	private String totalAmountMatched;
   	private String venue;
   	private String winners;

 	public String getBetDelay(){
		return this.betDelay;
	}
	public void setBetDelay(String betDelay){
		this.betDelay = betDelay;
	}
 	public String getEventHierarchy(){
		return this.eventHierarchy;
	}
	public void setEventHierarchy(String eventHierarchy){
		this.eventHierarchy = eventHierarchy;
	}
 	public String getEventName(){
		return this.eventName;
	}
	public void setEventName(String eventName){
		this.eventName = eventName;
	}
 	public String getEventTypeId(){
		return this.eventTypeId;
	}
	public void setEventTypeId(String eventTypeId){
		this.eventTypeId = eventTypeId;
	}
 	public String getExchange(){
		return this.exchange;
	}
	public void setExchange(String exchange){
		this.exchange = exchange;
	}
 	public String getGoingInPlay(){
		return this.goingInPlay;
	}
	public void setGoingInPlay(String goingInPlay){
		this.goingInPlay = goingInPlay;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getInterval(){
		return this.interval;
	}
	public void setInterval(String interval){
		this.interval = interval;
	}
 	public String getLastRefresh(){
		return this.lastRefresh;
	}
	public void setLastRefresh(String lastRefresh){
		this.lastRefresh = lastRefresh;
	}
 	public String getMarketInfo(){
		return this.marketInfo;
	}
	public void setMarketInfo(String marketInfo){
		this.marketInfo = marketInfo;
	}
 	public String getMarketStatus(){
		return this.marketStatus;
	}
	public void setMarketStatus(String marketStatus){
		this.marketStatus = marketStatus;
	}
 	public String getMarketTime(){
		return this.marketTime;
	}
	public void setMarketTime(String marketTime){
		this.marketTime = marketTime;
	}
 	public String getMarketType(){
		return this.marketType;
	}
	public void setMarketType(String marketType){
		this.marketType = marketType;
	}
 	public String getMenuPath(){
		return this.menuPath;
	}
	public void setMenuPath(String menuPath){
		this.menuPath = menuPath;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public BetfairRestApiMarketRunners getRunners(){
		return this.runners;
	}
	public void setRunners(BetfairRestApiMarketRunners runners){
		this.runners = runners;
	}
 	public String getStartTime(){
		return this.startTime;
	}
	public void setStartTime(String startTime){
		this.startTime = startTime;
	}
 	public String getSubtreeDepth(){
		return this.subtreeDepth;
	}
	public void setSubtreeDepth(String subtreeDepth){
		this.subtreeDepth = subtreeDepth;
	}
 	public String getTimezone(){
		return this.timezone;
	}
	public void setTimezone(String timezone){
		this.timezone = timezone;
	}
 	public String getTotalAmountMatched(){
		return this.totalAmountMatched;
	}
	public void setTotalAmountMatched(String totalAmountMatched){
		this.totalAmountMatched = totalAmountMatched;
	}
 	public String getVenue(){
		return this.venue;
	}
	public void setVenue(String venue){
		this.venue = venue;
	}
 	public String getWinners(){
		return this.winners;
	}
	public void setWinners(String winners){
		this.winners = winners;
	}
}
