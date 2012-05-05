package com.rozarltd.module.betfairwebsite.exception;

public class WebScrapingException extends Exception {
    public WebScrapingException() {
    }

    public WebScrapingException(String message) {
        super(message);
    }

    public WebScrapingException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebScrapingException(Throwable cause) {
        super(cause);
    }
}
