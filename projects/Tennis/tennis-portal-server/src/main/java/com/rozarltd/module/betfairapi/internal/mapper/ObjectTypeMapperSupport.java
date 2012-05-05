package com.rozarltd.module.betfairapi.internal.mapper;

import com.google.common.base.Function;
import org.apache.commons.lang.NotImplementedException;

public abstract class ObjectTypeMapperSupport<IN,OUT> implements ObjectTypeMapper<IN,OUT>{
    @Override
    public Function<IN, OUT> mapFunction() {
        throw new NotImplementedException();
    }
}
