package com.rozarltd.util.java.lang;

public class LongUtils {

    public static Long valueOf(String value) {
        if(value == null || value.trim().isEmpty()) {
            return null;
        }

        return Long.valueOf(value);
    }
}
