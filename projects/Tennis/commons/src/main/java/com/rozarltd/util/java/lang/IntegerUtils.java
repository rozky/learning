package com.rozarltd.util.java.lang;

public abstract class IntegerUtils {

    public static Integer valueOf(String value) {
        if(value == null || value.trim().isEmpty()) {
            return null;
        }

        return Integer.valueOf(value);
    }
}
