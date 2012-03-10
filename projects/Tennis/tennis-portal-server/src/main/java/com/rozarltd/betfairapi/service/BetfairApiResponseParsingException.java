package com.rozarltd.betfairapi.service;

public class BetfairApiResponseParsingException extends BetfairApiException {

    public BetfairApiResponseParsingException() {
    }

    public BetfairApiResponseParsingException(String message) {
        super(message);
    }

    public BetfairApiResponseParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BetfairApiResponseParsingException(Throwable cause) {
        super(cause);
    }
}
