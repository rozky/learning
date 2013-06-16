package com.rozarltd.module.betfairapi.domain.market;

import org.apache.commons.lang.time.DateUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class BetfairMarket {
    @Id
    private int id;
    private int marketId;
    private String marketName;
    private String marketType;
    private String marketStatus;
    private Long startAt;
    private Date startAtDate;
    private String menuPath;
    private String eventHierarchy;
    private String betDelay;
    private String exchangeId;
    private String countryCodeISO3;
    private String lastRefreshedAt;
    private String numberOfRunners;
    private String numberOfWinners;
    private String totalAmountMatched;
    private String bspMarket;
    private String turningInPlay;
    @Transient
    private List<BetfairRunner> runners;

    public String getParentEventId() {
        if(eventHierarchy != null && !eventHierarchy.isEmpty()) {
            String[] eventIds = eventHierarchy.split("/");
            return eventIds[eventIds.length - 1];
        }

        return null;
    }

    public String getParentEventName() {
        if(menuPath != null) {
            String[] menuPathItems = menuPath.split("\\\\");
            return menuPathItems[menuPathItems.length - 1];
        }

        return null;
    }

    public String getRootEventName() {
        if(menuPath != null) {
            String[] menuPathItems = menuPath.split("\\\\");
            return menuPathItems[1];
        }

        return null;
    }

    public boolean isInPlay() {
        return betDelay != null && !"0".equals(betDelay);
    }

    public boolean isTodayMarket() {
        return startAtDate != null ? DateUtils.isSameDay(this.startAtDate, new Date()) : false;
    }

    public String getMarketNameShort() {
        String marketNameShort = "";
        if(runners != null && runners.size() > 0) {
            for(BetfairRunner runner: runners) {
                marketNameShort += "".equals(marketNameShort) ? runner.getRunnerName() : " vs " + runner.getRunnerName();
            }
        }

        return marketNameShort;
    }

    public boolean isClosed() {
        return "CLOSED".equals(marketStatus);
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public void setEventHierarchy(String eventHierarchy) {
        this.eventHierarchy = eventHierarchy;
    }

    public void setMarketStatus(String marketStatus) {
        this.marketStatus = marketStatus;
    }

    public void setStartAt(Long startAt) {
        this.startAt = startAt;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.valueOf(startAt));
        this.startAtDate = calendar.getTime();
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public void setBetDelay(String betDelay) {
        this.betDelay = betDelay;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public void setCountryCodeISO3(String countryCodeISO3) {
        this.countryCodeISO3 = countryCodeISO3;
    }

    public void setLastRefreshedAt(String lastRefreshedAt) {
        this.lastRefreshedAt = lastRefreshedAt;
    }

    public void setNumberOfRunners(String numberOfRunners) {
        this.numberOfRunners = numberOfRunners;
    }

    public void setNumberOfWinners(String numberOfWinners) {
        this.numberOfWinners = numberOfWinners;
    }

    public void setTotalAmountMatched(String totalAmountMatched) {
        this.totalAmountMatched = totalAmountMatched;
    }

    public void setBspMarket(String bspMarket) {
        this.bspMarket = bspMarket;
    }

    public void setTurningInPlay(String turningInPlay) {
        this.turningInPlay = turningInPlay;
    }

    public int getMarketId() {
        return marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public String getMarketType() {
        return marketType;
    }

    public String getMarketStatus() {
        return marketStatus;
    }

    public Long getStartAt() {
        return startAt;
    }

    public Date getStartAtDate() {
        if(this.startAtDate != null) {
            return new Date(this.startAtDate.getTime());
        }

        return null;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public String getEventHierarchy() {
        return eventHierarchy;
    }

    public String getBetDelay() {
        return betDelay;
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public String getCountryCodeISO3() {
        return countryCodeISO3;
    }

    public String getLastRefreshedAt() {
        return lastRefreshedAt;
    }

    public String getNumberOfRunners() {
        return numberOfRunners;
    }

    public String getNumberOfWinners() {
        return numberOfWinners;
    }

    public String getTotalAmountMatched() {
        return totalAmountMatched;
    }

    public String getBspMarket() {
        return bspMarket;
    }

    public String getTurningInPlay() {
        return turningInPlay;
    }

    public List<BetfairRunner> getRunners() {
        return runners;
    }

    public void setRunners(List<BetfairRunner> runners) {
        this.runners = runners;
    }
}
