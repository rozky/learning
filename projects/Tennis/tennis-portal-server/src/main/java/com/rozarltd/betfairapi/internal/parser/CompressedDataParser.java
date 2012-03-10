package com.rozarltd.betfairapi.internal.parser;

import com.rozarltd.betfairapi.service.BetfairApiResponseParsingException;

public interface CompressedDataParser<T> {
    public T parse(String compressedData) throws BetfairApiResponseParsingException;
}
