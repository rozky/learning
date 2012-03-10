package com.rozarltd.betfairapi.service;

import com.betfair.publicapi.exchange.BFExchangeService;
import com.betfair.publicapi.exchange.types.GetAllMarketsErrorEnum;
import com.betfair.publicapi.exchange.types.GetAllMarketsReq;
import com.betfair.publicapi.exchange.types.GetAllMarketsResp;
import com.betfair.publicapi.exchange.types.GetCompleteMarketPricesCompressedReq;
import com.betfair.publicapi.exchange.types.GetCompleteMarketPricesCompressedResp;
import com.betfair.publicapi.exchange.types.GetCompleteMarketPricesErrorEnum;
import com.rozarltd.betfairapi.domain.market.BetfairMarket;
import com.rozarltd.betfairapi.domain.market.BetfairMarketPrices;
import com.rozarltd.betfairapi.internal.BetfairSessionToken;
import com.rozarltd.betfairapi.internal.factory.BFExchangeAPIRequestFactory;
import com.rozarltd.betfairapi.internal.mapper.betfair.BFTypeMapperManager;
import com.rozarltd.betfairapi.internal.mapper.ObjectTypeMapperManager;
import com.rozarltd.betfairapi.internal.parser.GetAllMarketResponseDataParser;
import com.rozarltd.betfairapi.stub.domain.BetfairGlobalApiResponseFixtures;
import com.rozarltd.betfairapi.stub.domain.BetfairMarketFixtures;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BetfairMarketServiceTest {

    private BetfairExchangeApiService marketService;

    // mock required to build marketService class under test
    private BetfairSessionToken sessionToken = new BetfairSessionToken("session-token");
    private BFExchangeAPIRequestFactory exchangeApiRequestFactory = new BFExchangeAPIRequestFactory();
    private GetAllMarketResponseDataParser getAllMarketResponseDataParser = new GetAllMarketResponseDataParser();
    private ObjectTypeMapperManager objectTypeMapperManager = new BFTypeMapperManager();
    @Mock private BFExchangeService exchangeApiService;

    @Before
    public void beforeEach() {
        initMocks(this);

        sessionToken = new BetfairSessionToken("session-token");
        marketService = new BetfairExchangeApiService(sessionToken, exchangeApiService,
                exchangeApiRequestFactory, getAllMarketResponseDataParser,
                objectTypeMapperManager);
    }

    @Test
    public void shouldConvertGetAllMarketResponseToTheListOfBetfairMarkets() {
        // given
        BetfairMarketFixtures.betfairMarket();
        GetAllMarketsResp response = BetfairGlobalApiResponseFixtures.createGetAllMarketsResp();
        when(exchangeApiService.getAllMarkets(org.mockito.Matchers.<GetAllMarketsReq>any())).thenReturn(response);

        // when
        List<BetfairMarket> markets = marketService.getMarkets(2);

        // then
        assertThat(markets.size(), is(1));
        BetfairMarket betfairMarket = markets.get(0);
        assertThat(betfairMarket.getMarketId(), is(105000000));
        assertThat(betfairMarket.getMarketName(), is("Match Odds"));
        assertThat(betfairMarket.getMarketStatus(), is("ACTIVE"));
        assertThat(betfairMarket.getMarketType(), is("O"));
        assertThat(betfairMarket.getStartAt(), is(1325415600000L));
        assertThat(betfairMarket.getEventHierarchy(), is("/2/26765986/26765989/104142702"));
        assertThat(betfairMarket.getMenuPath(), is("\\Tennis\\Group A\\Wimbledon\\Djokovic vs Nadal"));
        assertThat(betfairMarket.getBetDelay(), is("0"));
        assertThat(betfairMarket.getNumberOfRunners(), is("2"));
        assertThat(betfairMarket.getNumberOfWinners(), is("1"));
        assertThat(betfairMarket.getExchangeId(), is("1"));
        assertThat(betfairMarket.getCountryCodeISO3(), is(""));
        assertThat(betfairMarket.getLastRefreshedAt(), is("1325976355856"));
        assertThat(betfairMarket.getTotalAmountMatched(), is("1505.89"));
        assertThat(betfairMarket.getBspMarket(), is("N"));
        assertThat(betfairMarket.getTurningInPlay(), is("Y"));
    }

    @Test
    public void shouldReturnEmptyListWhenBetfairApiReturnsError() {
        // given
        GetAllMarketsResp response = mock(GetAllMarketsResp.class);
        when(exchangeApiService.getAllMarkets(org.mockito.Matchers.<GetAllMarketsReq>any())).thenReturn(response);
        when(response.getErrorCode()).thenReturn(GetAllMarketsErrorEnum.INVALID_EVENT_TYPE_ID);

        // when
        List<BetfairMarket> markets = marketService.getMarkets(2);

        // then
        assertThat(markets.size(), is(0));
    }
    
    @Test
    public void shouldOnlyReturnMarketWhichAreCurrentlyInPlay() {
//        // given
//        BetfairMarket[] betfairMarkets = {inPlayBetfairMarket("1"), betfairMarket("2"), inPlayBetfairMarket("3"), betfairMarket("4")};
//        GetAllMarketsResp response = BetfairGlobalApiResponseFixtures.createGetAllMarketsResp(betfairMarkets);
//        when(exchangeApiService.getAllMarkets(org.mockito.Matchers.<GetAllMarketsReq>any())).thenReturn(response);
//
//        // when
//        List<BetfairMarket> markets = marketService.getInPlayMarkets(2);
//
//        // then
//        assertThat(markets.size(), is(2));
//        assertThat(markets.get(0).getMarketId(), is("1"));
//        assertThat(markets.get(1).getMarketId(), is("3"));
    }

    @Test
    public void shouldReturnMarketPrices() {
        // given
        GetCompleteMarketPricesCompressedResp response = mock(GetCompleteMarketPricesCompressedResp.class);
        when(response.getErrorCode()).thenReturn(GetCompleteMarketPricesErrorEnum.INVALID_MARKET);
        when(exchangeApiService.getCompleteMarketPricesCompressed(org.mockito.Matchers.<GetCompleteMarketPricesCompressedReq>any()))
                .thenReturn(response);

        // when
        BetfairMarketPrices marketPrices = marketService.getMarketPrices("1");

        // then
        assertThat(marketPrices, Matchers.<Object>nullValue());
    }
    
    @Test
    public void shouldReturnNullWhenMarketPricesApiRequestReturnError() {
        // given
        GetCompleteMarketPricesCompressedResp response = mock(GetCompleteMarketPricesCompressedResp.class);
        when(response.getErrorCode()).thenReturn(GetCompleteMarketPricesErrorEnum.INVALID_MARKET);
        when(exchangeApiService.getCompleteMarketPricesCompressed(org.mockito.Matchers.<GetCompleteMarketPricesCompressedReq>any()))
                .thenReturn(response);

        // when
        BetfairMarketPrices marketPrices = marketService.getMarketPrices("1");

        // then
        assertThat(marketPrices, Matchers.<Object>nullValue());
    }
}
