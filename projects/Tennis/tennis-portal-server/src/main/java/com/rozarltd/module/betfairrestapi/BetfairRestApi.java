package com.rozarltd.module.betfairrestapi;

import com.rozarltd.module.betfairapi.service.ServiceResponse;
import com.rozarltd.module.betfairrestapi.domain.response.GetMarketResponse;
import com.rozarltd.module.betfairrestapi.domain.response.PlaceBetResponse;

public interface BetfairRestApi {
    public GetMarketResponse getMarket(int marketId);
    public ServiceResponse<PlaceBetResponse> placeBet(String sessionToken, int marketId, int selectionId, double price, double stake);
}
