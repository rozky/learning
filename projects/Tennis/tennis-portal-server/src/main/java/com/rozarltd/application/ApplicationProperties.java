package com.rozarltd.application;

public class ApplicationProperties {
    public static final String APPLICATION_MODE = "application.mode";
    public static final String DEV_USERNAME_FOR_BETFAIR = "dev.betfair.username";
    public static final String DEV_PASSWORD_FOR_BETFAIR = "dev.betfair.password";

    public static String getApplicationMode() {
        return System.getProperty(APPLICATION_MODE);
    }

    public static boolean isDevelopmentMode() {
        return "dev".equals(getApplicationMode());
    }

    public static String getDevelopmentBetfairUsername() {
        return isDevelopmentMode() ? System.getProperty(DEV_USERNAME_FOR_BETFAIR) : null;
    }

    public static String getDevelopmentBetfairPassword() {
        return isDevelopmentMode() ? System.getProperty(DEV_PASSWORD_FOR_BETFAIR) : null;
    }
}
