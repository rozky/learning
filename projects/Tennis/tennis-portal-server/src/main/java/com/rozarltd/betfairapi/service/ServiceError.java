package com.rozarltd.betfairapi.service;

public class ServiceError {
    private String errorCode;
    private String minorCode;
    private String description;

    public ServiceError(String errorCode) {
        this.errorCode = errorCode;
    }

    public ServiceError(String errorCode, String minorCode) {
        this.errorCode = errorCode;
        this.minorCode = minorCode;
    }

    public ServiceError(String errorCode, String minorCode, String description) {
        this.errorCode = errorCode;
        this.minorCode = minorCode;
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMinorCode() {
        return minorCode;
    }

    public String getDescription() {
        return description;
    }
}
