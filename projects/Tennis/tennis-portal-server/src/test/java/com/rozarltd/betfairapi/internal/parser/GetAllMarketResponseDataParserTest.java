package com.rozarltd.betfairapi.internal.parser;

import com.rozarltd.betfairapi.domain.market.BetfairMarket;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GetAllMarketResponseDataParserTest {
    private static final String VALID_SINGLE_MARKET_STRING = ":104142702~Djokovic GS Total~O~ACTIVE~1325415600000~\\Tennis\\Group A\\Tennis Specials 2012\\Djokovic Specials~/2/26765986/26765989/104142702~0~1~~1325976355856~5~1~330.38~N~Y:";
    private static final String MARKET_WITHOUT_ID_STRING = ":Djokovic GS Total~O~ACTIVE~1325415600000~\\Tennis\\Group A\\Tennis Specials 2012\\Djokovic Specials~/2/26765986/26765989/104142702~0~1~~1325976355856~5~1~330.38~N~Y:";


    private GetAllMarketResponseDataParser parser;

    @Before
    public void beforeEach() {
        parser = new GetAllMarketResponseDataParser();
    }

    @Test
    public void shouldParseMarketCorrectly() throws Exception {
        // given
        String validMarketString = VALID_SINGLE_MARKET_STRING;

        // when
        List<BetfairMarket> markets = parser.parse(validMarketString);

        // then
        assertThat(markets.size(), is(1));
        BetfairMarket market = markets.get(0);
        assertThat(market.getMarketId(), is(104142702));
        assertThat(market.getMarketName(), is("Djokovic GS Total"));
        assertThat(market.getMarketType(), is("O"));
        assertThat(market.getMarketStatus(), is("ACTIVE"));
        assertThat(market.getStartAt(), is(1325415600000L));
        assertThat(market.getMenuPath(), is("\\Tennis\\Group A\\Tennis Specials 2012\\Djokovic Specials"));
        assertThat(market.getEventHierarchy(), is("/2/26765986/26765989/104142702"));
        assertThat(market.getBetDelay(), is("0"));
        assertThat(market.getExchangeId(), is("1"));
        assertThat(market.getCountryCodeISO3(), is(""));
        assertThat(market.getLastRefreshedAt(), is("1325976355856"));
        assertThat(market.getNumberOfRunners(), is("5"));
        assertThat(market.getNumberOfWinners(), is("1"));
        assertThat(market.getTotalAmountMatched(), is("330.38"));
        assertThat(market.getBspMarket(), is("N"));
        assertThat(market.getTurningInPlay(), is("Y"));
    }

    @Test
    public void shouldNotParseUncompletedMarkets() throws IOException, URISyntaxException {
        // given
        String validMarketString = MARKET_WITHOUT_ID_STRING;

        // when
        List<BetfairMarket> markets = parser.parse(validMarketString);

        // then
        assertThat(markets.size(), is(0));
    }

    @Test
    public void shouldReturnEmptyListForEmptyResponse() {
        // given
        String validMarketString = "";

        // when
        List<BetfairMarket> markets = parser.parse(validMarketString);

        // then
        assertThat(markets.size(), is(0));
    }

    private String getMarketString(String fileName) throws IOException, URISyntaxException {
        URL resource = ClassLoader.getSystemResource(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(new File(resource.toURI())));
        String result = reader.readLine();
        reader.close();

        return result;
    }
}
