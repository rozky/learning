package com.rozarltd.util.java.lang;

import java.math.BigDecimal;

public abstract class DoubleUtils {
    public static Double valueOf(String value) {
        if(value == null || value.trim().isEmpty()) {
            return null;
        }

        return Double.valueOf(value);
    }

    public static double valueOf(double value, int scale) {
        return BigDecimal.valueOf(value).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
