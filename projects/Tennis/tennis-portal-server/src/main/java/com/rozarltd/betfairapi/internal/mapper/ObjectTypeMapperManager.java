package com.rozarltd.betfairapi.internal.mapper;

public interface ObjectTypeMapperManager {
    public <IN_TYPE, OUT_TYPE> ObjectTypeMapper<IN_TYPE, OUT_TYPE> getMapper(Class<IN_TYPE> inType, Class<OUT_TYPE> outType);
}
