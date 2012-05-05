package com.rozarltd.util;

public class EnumUtils {
    public static final String asString(Enum object) {
        return object != null ? object.name() : null;
    }

    public static final <T extends Enum<T>> T safeValueOf(Enum name, Class<T> enumClazz) {
        String enumName = asString(name);

        return enumName != null ? Enum.valueOf(enumClazz, enumName) : null;
    }
}
