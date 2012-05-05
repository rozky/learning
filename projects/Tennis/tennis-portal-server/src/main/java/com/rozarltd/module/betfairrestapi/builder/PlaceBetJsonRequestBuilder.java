package com.rozarltd.module.betfairrestapi.builder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

public class PlaceBetJsonRequestBuilder {
    private static final String FORMAT = "json";
    private static final String BACK_BET_TYPE = "B";
    private static final String LAY_BET_TYPE = "L";
    private static final String EXCHANGE = "UK";

    private String sessionId;
    private String marketId;
    private String selectionId;
    private String price;
    private String stake;
    private String betType;

    public PlaceBetJsonRequestBuilder(String sessionId) {
        this.sessionId = sessionId;
    }

    public PlaceBetJsonRequestBuilder withSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public PlaceBetJsonRequestBuilder withMarketId(int marketId) {
        this.marketId = String.valueOf(marketId);
        return this;
    }

    public PlaceBetJsonRequestBuilder withSelectionId(int selectionId) {
        this.selectionId = String.valueOf(selectionId);
        return this;
    }

    public PlaceBetJsonRequestBuilder withPrice(double price) {
        this.price = String.valueOf(price);
        return this;
    }

    public PlaceBetJsonRequestBuilder withStake(double stake) {
        this.stake = String.valueOf(stake);
        return this;
    }

    public HttpEntity<MultiValueMap<String, String>> buildBackBet() {
        this.betType = BACK_BET_TYPE;
        return build();
    }

    public HttpEntity<MultiValueMap<String, String>> buildLayBet() {
        this.betType = LAY_BET_TYPE;
        return build();
    }

    public HttpEntity<MultiValueMap<String, String>> buildBackBet(int marketId, int selectionId, double price, double stake) {
        withMarketId(marketId);
        withSelectionId(selectionId);
        withPrice(price);
        withStake(stake);

        return buildBackBet();
    }

    public HttpEntity<MultiValueMap<String, String>> buildLayBet(int marketId, int selectionId, double price, double stake) {
        withMarketId(marketId);
        withSelectionId(selectionId);
        withPrice(price);
        withStake(stake);

        return buildLayBet();
    }

    private HttpEntity<MultiValueMap<String, String>> build() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<String, String>() {
            {
                put("session_id", Collections.singletonList(sessionId));
                put("marketId", Collections.singletonList(marketId));
                put("selectionId", Collections.singletonList(selectionId));
                put("price", Collections.singletonList(price));
                put("size", Collections.singletonList(stake));
                put("exchange", Collections.singletonList(EXCHANGE));
                put("betType", Collections.singletonList(betType));
                put("format", Collections.singletonList(FORMAT));
            }
        };

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

        return new HttpEntity<MultiValueMap<String, String>>(requestParams, requestHeaders);
    }
}
