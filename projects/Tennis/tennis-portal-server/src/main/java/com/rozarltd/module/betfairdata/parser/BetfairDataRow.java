package com.rozarltd.module.betfairdata.parser;

public class BetfairDataRow {
    private int sportId; // tennis = 2
    private int eventId;
    private String settledDate;
    private String fullMarketName;
    private String marketName;
    private long selectionId;
    private String selectionName;
    private float odds;
    private String latestTaken;
    private String firstTaken;
    private String win;
    private String inPlay;

    public boolean isMatchOdds() {
        return "Match Odds".equals(marketName);
    }

    public boolean isInPlay() {
        return "IP".equals(inPlay);
    }

    public boolean isWin() {
        return "1".equals(win);
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getSettledDate() {
        return settledDate;
    }

    public void setSettledDate(String settledDate) {
        this.settledDate = settledDate;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getFullMarketName() {
        return fullMarketName;
    }

    public void setFullMarketName(String fullMarketName) {
        this.fullMarketName = fullMarketName;
    }

    public long getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(long selectionId) {
        this.selectionId = selectionId;
    }

    public String getSelectionName() {
        return selectionName;
    }

    public void setSelectionName(String selectionName) {
        this.selectionName = selectionName;
    }

    public float getOdds() {
        return odds;
    }

    public void setOdds(float odds) {
        this.odds = odds;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getLatestTaken() {
        return latestTaken;
    }

    public void setLatestTaken(String latestTaken) {
        this.latestTaken = latestTaken;
    }

    public String getFirstTaken() {
        return firstTaken;
    }

    public void setFirstTaken(String firstTaken) {
        this.firstTaken = firstTaken;
    }

    public String getInPlay() {
        return inPlay;
    }

    public void setInPlay(String inPlay) {
        this.inPlay = inPlay;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", sportId, eventId, settledDate, fullMarketName,
                marketName, selectionId, selectionName, odds, latestTaken, firstTaken, win, inPlay);
    }
}
