package com.rozarltd.betfairapi.internal.factory;



import com.betfair.publicapi.exchange.types.APIRequestHeader;
import com.betfair.publicapi.exchange.types.AccountStatementIncludeEnum;
import com.betfair.publicapi.exchange.types.ArrayOfCancelBets;
import com.betfair.publicapi.exchange.types.ArrayOfInt;
import com.betfair.publicapi.exchange.types.BetStatusEnum;
import com.betfair.publicapi.exchange.types.BetsOrderByEnum;
import com.betfair.publicapi.exchange.types.CancelBets;
import com.betfair.publicapi.exchange.types.CancelBetsReq;
import com.betfair.publicapi.exchange.types.GetAccountFundsReq;
import com.betfair.publicapi.exchange.types.GetAccountStatementReq;
import com.betfair.publicapi.exchange.types.GetAllMarketsReq;
import com.betfair.publicapi.exchange.types.GetCurrentBetsReq;
import com.betfair.publicapi.exchange.types.GetMUBetsReq;
import com.betfair.publicapi.exchange.types.GetMarketReq;
import com.betfair.publicapi.exchange.types.SortOrderEnum;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;

@Component
public class BFExchangeAPIRequestFactory {

    public GetMarketReq createGetMarketRequest(String sessionToken, int marketId) {
        GetMarketReq request = new GetMarketReq();
        request.setHeader(createRequestHeaderInstance(sessionToken));
        request.setMarketId(marketId);
        request.setIncludeCouponLinks(false);

        return request;
    }

    public GetAllMarketsReq createGetAllMarketsRequest(String sessionToken, Integer eventTypeId) {
        GetAllMarketsReq request = new GetAllMarketsReq();
        request.setHeader(createRequestHeaderInstance(sessionToken));
        request.setEventTypeIds(new ArrayOfInt(Arrays.asList(eventTypeId)));

        return request;
    }

    public GetAccountStatementReq createGetAccountStatementRequestInstance(String sessionToken, int startRecord, int recordCount) {
        // move current date 3 month into the past
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.setTime(DateUtils.addDays(startDateCalendar.getTime(), -80));

        GetAccountStatementReq request = new GetAccountStatementReq();
        request.setHeader(createRequestHeaderInstance(sessionToken));
        request.setStartDate(startDateCalendar);
        request.setEndDate(Calendar.getInstance());
        request.setStartRecord(startRecord);
        request.setRecordCount(recordCount);
        request.setItemsIncluded(AccountStatementIncludeEnum.ALL);
        return request;
    }

    public GetAccountFundsReq createGetAccountFundsRequest(String sessionToken) {
        GetAccountFundsReq request = new GetAccountFundsReq();
        request.setHeader(createRequestHeaderInstance(sessionToken));

        return request;
    }

    public GetCurrentBetsReq createGetCurrentBetsRequest(String sessionToken) {
        GetCurrentBetsReq request = new GetCurrentBetsReq();
        request.setHeader(createRequestHeaderInstance(sessionToken));
        request.setStartRecord(0);
        request.setRecordCount(100);
        request.setOrderBy(BetsOrderByEnum.PLACED_DATE);
        request.setDetailed(false);
        request.setNoTotalRecordCount(false);
        request.setBetStatus(BetStatusEnum.M);

        return request;
    }

    public GetMUBetsReq createGetMUBetsRequest(String sessionToken) {
        GetMUBetsReq request = new GetMUBetsReq();
        request.setHeader(createRequestHeaderInstance(sessionToken));
        request.setStartRecord(0);
        request.setRecordCount(100);
        request.setOrderBy(BetsOrderByEnum.PLACED_DATE);
        request.setSortOrder(SortOrderEnum.DESC);
        request.setBetStatus(BetStatusEnum.MU);

        return request;
    }

    public CancelBetsReq createCancelBetsRequest(String sessionToken, long betId) {
        CancelBetsReq request = new CancelBetsReq();
        request.setHeader(createRequestHeaderInstance(sessionToken));
        request.setBets(new ArrayOfCancelBets(Arrays.asList(new CancelBets(betId))));

        return request;
    }

    private APIRequestHeader createRequestHeaderInstance(String sessionToken) {
        APIRequestHeader header = new APIRequestHeader();
        header.setSessionToken(sessionToken);

        return header;
    }
}
