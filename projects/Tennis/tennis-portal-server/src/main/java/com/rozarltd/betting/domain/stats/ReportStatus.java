package com.rozarltd.betting.domain.stats;

public enum ReportStatus {
    C("COMPLETED"),
    P("PARTIAL");

    private String description;

    ReportStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
