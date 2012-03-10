package com.rozarltd.betfairapi.service;

import com.rozarltd.betfairrestapi.domain.response.GetMarketResponse;
import com.rozarltd.betfairrestapi.domain.response.PlaceBetResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class BetfairRestApiService implements BFRestApiService {
    private static final String REST_API_ENDPOINT = "https://rest.labs.betfair.com/";
    private static final String GET_MARKET_URL_TEMPLATE = REST_API_ENDPOINT + "market/{marketId}?extend=price&format=json";
    private static final String PLACE_BET_URL_TEMPLATE = REST_API_ENDPOINT + "submit";


    private RestTemplate restTemplate = new RestTemplate();

    // don't cache as it's used to update prices
    // @Cacheable(value = "betfair-rest-api-market")
    public GetMarketResponse getMarket(int marketId) {
        return restTemplate.getForObject(GET_MARKET_URL_TEMPLATE, GetMarketResponse.class, marketId);
    }

    @Override
    public ServiceResponse<PlaceBetResponse> placeBet(String sessionToken, int marketId, int selectionId, double price, double stake) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.put("session_id", Collections.singletonList(sessionToken));
        params.put("marketId", Collections.singletonList(String.valueOf(marketId)));
        params.put("selectionId", Collections.singletonList(String.valueOf(selectionId)));
        params.put("price", Collections.singletonList(String.valueOf(price)));
        params.put("size", Collections.singletonList(String.valueOf(stake)));
        params.put("exchange", Collections.singletonList("UK"));
        params.put("betType", Collections.singletonList("B"));
        params.put("format", Collections.singletonList("json"));

        try {
            ResponseEntity<PlaceBetResponse> response =
                    restTemplate.exchange(PLACE_BET_URL_TEMPLATE, HttpMethod.POST, buildRequest(params), PlaceBetResponse.class);

            PlaceBetResponse placeBetResp = response.getBody();
            if (placeBetResp.isError()) {
                return ServiceResponse.error(new ServiceError(placeBetResp.error()));
            }

            return ServiceResponse.response(placeBetResp);
        } catch (Exception e) {
            return ServiceResponse.error(new ServiceError("API_ERROR", null, e.getMessage()));
        }
    }

    private HttpEntity<MultiValueMap<String, String>> buildRequest(MultiValueMap<String, String> requestParameters) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

        return new HttpEntity<MultiValueMap<String, String>>(requestParameters, requestHeaders);
    }
}
