package com.rozarltd.junit;

public @interface Retry {
    public int value() default 3;
}
