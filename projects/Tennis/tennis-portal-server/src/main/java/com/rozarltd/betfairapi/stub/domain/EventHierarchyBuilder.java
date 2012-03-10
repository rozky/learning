package com.rozarltd.betfairapi.stub.domain;

import org.apache.commons.lang.StringUtils;

public abstract class EventHierarchyBuilder {
    private static final String EVENTS_SEPARATOR = "/";

    EventHierarchyBuilder() {
    }

    public static String build(Integer... events) {
        return StringUtils.join(events, EVENTS_SEPARATOR);
    }
}
