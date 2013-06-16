package com.rozarltd.module.atpwebsite.domain;

public enum TennisTournamentCategory {
    UNKNOWN("unknown"),
    ATP250("atp 250"),
    ATP500("atp 500"),
    ATP1000("atp masters 1000"),
    GRAND_SLAM("grand slam"),
    DAVIS_CUP("davis cup"),
    ATP_FINALS("atp finals"),
    WTA("wta");

    private String description;

    TennisTournamentCategory(String description) {
        this.description = description;
    }

    public static TennisTournamentCategory from(String description) {
        description = description.toLowerCase();
        for(TennisTournamentCategory category: TennisTournamentCategory.values()) {
            if(description.contains(category.description)) {
                return category;
            }
        }

        return UNKNOWN;
    }
}
