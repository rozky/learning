package com.rozarltd.module.betfairapi.service;

public class BetfairApiException extends Exception {
    public BetfairApiException() {
        super();
    }

    public BetfairApiException(String message) {
        super(message);
    }

    public BetfairApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public BetfairApiException(Throwable cause) {
        super(cause);
    }
}
