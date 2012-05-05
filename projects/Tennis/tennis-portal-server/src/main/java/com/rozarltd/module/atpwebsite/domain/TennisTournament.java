package com.rozarltd.module.atpwebsite.domain;

import java.util.Date;

public class TennisTournament {
    private TennisTournamentCategory category;
    private Date startDate;
    private String name;
    private String location;
    private String surface;
    private int prizeMoney;

    public TennisTournamentCategory getCategory() {
        return category;
    }

    public void setCategory(TennisTournamentCategory category) {
        this.category = category;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public void setPrizeMoney(int prizeMoney) {
        this.prizeMoney = prizeMoney;
    }
}
