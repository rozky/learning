package com.rozarltd.util.java.lang;

import org.apache.commons.lang.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public abstract class DateUtilities {
    public static Date getDate(Calendar calendar) {
        if(calendar != null) {
            return calendar.getTime();
        }

        return null;
    }

    public static Date truncateToDay(Date date) {
        return DateUtils.truncate(date, Calendar.DATE);
    }

    public static Date truncateAndAddDays(Date date, int amount) {
        return DateUtils.truncate(DateUtils.addDays(date, amount), Calendar.DATE);
    }

    public static Date today() {
        return DateUtils.truncate(new Date(), Calendar.DATE);
    }

    public static Date tomorrow() {
        return DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DATE);
    }
}
