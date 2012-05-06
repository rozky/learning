package com.rozarltd.application;

import org.springframework.core.env.Environment;

public class ApplicationProperties {
    public static final String APPLICATION_MODE = "application.mode";
    public static final String DEV_USERNAME_FOR_BETFAIR = "dev.betfair.username";
    public static final String DEV_PASSWORD_FOR_BETFAIR = "dev.betfair.password";

    private Environment environment;

    public ApplicationProperties(Environment environment) {
        this.environment = environment;
    }

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

    private String getProperty(String name) {
        if(environment != null) {
            return environment.getProperty(name);
        }

        return System.getProperty(name);
    }
}
