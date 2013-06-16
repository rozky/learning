package com.rozarltd.module.betfairrestapi;

import com.google.inject.Inject;
import com.rozarltd.betting.common.domain.BetParams;
import com.rozarltd.module.betfairapi.service.ServiceError;
import com.rozarltd.module.betfairapi.service.ServiceResponse;
import com.rozarltd.module.betfairrestapi.builder.PlaceBetJsonRequestBuilder;
import com.rozarltd.module.betfairrestapi.domain.response.GetMarketResponse;
import com.rozarltd.module.betfairrestapi.domain.response.PlaceBetResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;

public class JsonBetfairRestApi implements BetfairRestApi {
    private static final String REST_API_ENDPOINT = "https://rest.labs.betfair.com/";
    private static final String GET_MARKET_PATH = REST_API_ENDPOINT + "market/{marketId}?extend=price&format=json";
    private static final String PLACE_BET_PATH = REST_API_ENDPOINT + "submit";

    private RestOperations restTemplate;

    @Inject
    public JsonBetfairRestApi(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public GetMarketResponse getMarket(int marketId) {
        return restTemplate.getForObject(GET_MARKET_PATH, GetMarketResponse.class, marketId);
    }

    @Override
    public ServiceResponse<PlaceBetResponse> placeBet(String sessionId, BetParams bet) {
        Assert.notNull(sessionId);
        Assert.notNull(bet);

        try {
            HttpEntity<MultiValueMap<String, String>> request =
                    new PlaceBetJsonRequestBuilder(sessionId).buildBackBet(bet);

            ResponseEntity<PlaceBetResponse> response =
                    restTemplate.exchange(PLACE_BET_PATH, HttpMethod.POST, request, PlaceBetResponse.class);

            PlaceBetResponse placeBetResp = response.getBody();
            if (placeBetResp.isError()) {
                return ServiceResponse.error(new ServiceError(placeBetResp.error()));
            }

            return ServiceResponse.response(placeBetResp);
        } catch (Exception e) {
            return ServiceResponse.error(new ServiceError("API_ERROR", null, e.getMessage()));
        }
    }
}
