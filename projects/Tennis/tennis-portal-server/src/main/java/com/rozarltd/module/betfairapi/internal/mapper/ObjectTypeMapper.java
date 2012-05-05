package com.rozarltd.module.betfairapi.internal.mapper;

import com.google.common.base.Function;

public interface ObjectTypeMapper<IN_TYPE, OUT_TYPE> {
    public OUT_TYPE mapFrom(IN_TYPE inType);
    public Function<IN_TYPE, OUT_TYPE> mapFunction();
}
