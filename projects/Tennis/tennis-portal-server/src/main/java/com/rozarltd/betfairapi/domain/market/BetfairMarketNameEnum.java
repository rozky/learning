package com.rozarltd.betfairapi.domain.market;

public enum BetfairMarketNameEnum {
    matchOdds("MO", "Match Odds");

    private String code;
    private String name;


    BetfairMarketNameEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public BetfairMarketNameEnum from(String code) {
        for(BetfairMarketNameEnum value: values()) {
            if(value.getCode().equals(code)) {
                return value;
            }
        }

        return null;
    }
}
