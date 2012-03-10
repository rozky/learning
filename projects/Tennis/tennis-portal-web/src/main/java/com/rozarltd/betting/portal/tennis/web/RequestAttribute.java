package com.rozarltd.betting.portal.tennis.web;

import javax.servlet.http.HttpServletRequest;

public enum RequestAttribute {
    eventId,
    marketId;

    public String getValue(HttpServletRequest request) {
        return (String) request.getAttribute(this.toString());
    }
}
