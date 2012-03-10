package com.rozarltd.betfairapi.service;

import com.rozarltd.betfairrestapi.domain.response.GetMarketResponse;
import com.rozarltd.betfairrestapi.domain.response.PlaceBetResponse;

public interface BFRestApiService {
    public GetMarketResponse getMarket(int marketId);
    public ServiceResponse<PlaceBetResponse> placeBet(String sessionToken, int marketId, int selectionId, double price, double stake);
}
