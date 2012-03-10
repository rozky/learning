package com.rozarltd.util;

public class EnumUtils {
    public static final String asString(Enum object) {
        return object != null ? object.name() : null;
    }
}
