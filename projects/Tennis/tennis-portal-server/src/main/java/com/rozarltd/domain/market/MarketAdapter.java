package com.rozarltd.domain.market;

import com.rozarltd.module.betfairapi.domain.market.BetfairMarket;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;
import java.util.List;

public class MarketAdapter {
    private int marketId;
    private String fullMarketName;
    private List<MarketRunner> runners;
    private Date startAtDate;
    private BetfairMarket betfairMarket;

    public MarketAdapter() {
    }

    public MarketAdapter(int marketId, String fullMarketName, List<MarketRunner> runners) {
        this.marketId = marketId;
        this.fullMarketName = fullMarketName;
        this.runners = runners;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public String getFullMarketName() {
        return fullMarketName;
    }

    public void setFullMarketName(String fullMarketName) {
        this.fullMarketName = fullMarketName;
    }

    public List<MarketRunner> getRunners() {
        return runners;
    }

    public void setRunners(List<MarketRunner> runners) {
        this.runners = runners;
    }

    public boolean hasRunners() {
        return this.runners != null && !runners.isEmpty();
    }

    public String getMarketNameShort() {
        String marketNameShort = "";
        if(runners != null && runners.size() > 0) {
            for(MarketRunner runner: runners) {
                marketNameShort += "".equals(marketNameShort) ? runner.getRunnerName() : " vs " + runner.getRunnerName();
            }
        }

        return marketNameShort;
    }

    public Date getStartAtDate() {
        if(this.startAtDate != null) {
            return new Date(this.startAtDate.getTime());
        }

        return null;
    }

    public void setStartAtDate(Date startAtDate) {
        this.startAtDate = startAtDate;
    }

    public boolean isTodayMarket() {
        return startAtDate != null && DateUtils.isSameDay(this.startAtDate, new Date());
    }

    public void setBetfairMarket(BetfairMarket betfairMarket) {
        this.betfairMarket = betfairMarket;
    }

    public boolean isInPlay() {
        return betfairMarket.isInPlay();
    }
}
