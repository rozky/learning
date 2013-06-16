package com.rozarltd.util.java.lang;

import java.util.Calendar;
import java.util.Date;

public abstract class DateUtils {
    public static Date getDate(Calendar calendar) {
        if(calendar != null) {
            return calendar.getTime();
        }

        return null;
    }
}
