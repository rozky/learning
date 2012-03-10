package com.rozarltd.betfairapi.internal.mapper;

public interface ObjectTypeMapper<IN_TYPE, OUT_TYPE> {
    public OUT_TYPE mapFrom(IN_TYPE inType);
}
