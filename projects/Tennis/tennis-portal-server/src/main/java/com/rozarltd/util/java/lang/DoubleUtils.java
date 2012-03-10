package com.rozarltd.util.java.lang;

public abstract class DoubleUtils {
    public static final Double valueOf(String value) {
        if(value == null || value.trim().isEmpty()) {
            return null;
        }

        return Double.valueOf(value);
    }
}
