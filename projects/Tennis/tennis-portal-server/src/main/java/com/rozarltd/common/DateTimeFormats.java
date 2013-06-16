package com.rozarltd.common;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public abstract class DateTimeFormats {
    public static final DateTimeFormatter NO_MILLIS = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");

    private DateTimeFormats() {
    }
}
