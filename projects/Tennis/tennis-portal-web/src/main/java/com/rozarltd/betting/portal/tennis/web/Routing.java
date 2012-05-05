package com.rozarltd.betting.portal.tennis.web;

public interface Routing {
    public static final String HOME = "/";
    public static final String REST = "/rest";
    public static final String LOGIN = "/login";
    public static final String BETFAIR_HOME = "/betfair";
    public static final String BETFAIR_LOGIN = BETFAIR_HOME + "/login";
    public static final String BETFAIR_ACTIONS = BETFAIR_HOME + "/actions";
    public static final String BETFAIR_BET = BETFAIR_HOME + "/bet";
    public static final String BETFAIR_CANCEL_BET = BETFAIR_BET + "/cancel";
    public static final String BETFAIR_MARKET = BETFAIR_HOME + "/market";
    public static final String REST_BETFAIR_MARKET = REST + BETFAIR_HOME + "/market.json";
    public static final String BETFAIR_MARKET_LIVE = BETFAIR_MARKET + "/live";
    public static final String BETFAIR_MARKET_DEVELOPMENT = BETFAIR_MARKET + "/development";
    public static final String BETFAIR_ACCOUNT_WALLET = BETFAIR_HOME + "/wallet";
    public static final String BETFAIR_ACCOUNT_STATEMENT = BETFAIR_HOME + "/account-statement";
    public static final String BETFAIR_DAILY_BETTING_REPORT = BETFAIR_HOME + "/daily-betting-report";
    public static final String CONFIRMATION = "/confirmation";
    public static final String BETFAIR_REPLICATION = BETFAIR_HOME + "/replication";
    public static final String BETFAIR_TODAY_MARKET_REPLICATION = BETFAIR_HOME + "/today-market-replication";
}
