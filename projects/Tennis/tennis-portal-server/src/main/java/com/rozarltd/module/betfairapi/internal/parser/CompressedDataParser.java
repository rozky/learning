package com.rozarltd.module.betfairapi.internal.parser;

import com.rozarltd.module.betfairapi.service.BetfairApiResponseParsingException;

public interface CompressedDataParser<T> {
    public T parse(String compressedData) throws BetfairApiResponseParsingException;
}
