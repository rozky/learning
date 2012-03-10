package com.rozarltd.betfairapi.stub.domain;

import com.betfair.publicapi.exchange.types.APIErrorEnum;
import com.betfair.publicapi.exchange.types.APIResponseHeader;
import com.betfair.publicapi.exchange.types.GetAllMarketsErrorEnum;
import com.betfair.publicapi.exchange.types.GetAllMarketsResp;
import com.rozarltd.betfairapi.domain.market.BetfairMarket;

public class BetfairGlobalApiResponseFixtures {
    public static final String DEFAULT_SESSION_TOKEN = "TDJH6SY76SSAS9JH";
    public static final APIErrorEnum DEFAULT_HEAD_ERROR = APIErrorEnum.OK;
    public static final String DEFAULT_MINOR_ERROR = "";

    public static GetAllMarketsResp createGetAllMarketsResp(BetfairMarket... markets) {
        GetAllMarketsResp response = new GetAllMarketsResp();
        response.setHeader(createApiResponseHeader());
        response.setErrorCode(GetAllMarketsErrorEnum.OK);
        response.setMinorErrorCode(DEFAULT_MINOR_ERROR);
        String compressedMarket = BetfairMarketToCompressedStringConverter.convert(markets);
        response.setMarketData(compressedMarket);

        return response;
    }

    public static GetAllMarketsResp createGetAllMarketsResp() {
        return createGetAllMarketsResp(BetfairMarketFixtures.betfairMarket());
    }

    private static APIResponseHeader createApiResponseHeader() {
        APIResponseHeader header = new APIResponseHeader();
        header.setErrorCode(DEFAULT_HEAD_ERROR);
        header.setMinorErrorCode(DEFAULT_MINOR_ERROR);
        header.setSessionToken(DEFAULT_SESSION_TOKEN);

        return header;
    }
}
