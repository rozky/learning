package com.rozarltd.module.betfairwebsite.service;

import com.rozarltd.module.betfairwebsite.exception.WebScrapingException;
import com.rozarltd.module.betfairwebsite.page.coupon.component.TennisInPlayCouponExtractor;
import com.rozarltd.module.betfairwebsite.page.coupon.domain.TennisInPlayCoupon;
import com.rozarltd.module.betfairwebsite.page.market.domain.WebMarket;
import com.rozarltd.util.http.HttpClientException;
import com.rozarltd.util.http.HttpTemplate;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.nodes.Document;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class BetfairWebsiteClient {

    // todo - inject this
    private ObjectMapper jsonObjectMapper = new ObjectMapper();
    private HttpTemplate httpTemplate = new HttpTemplate();

//    @Autowired
//    public BetfairWebsiteClient(HttpTemplate httpTemplate) {
//        this.httpTemplate = httpTemplate;
//    }

    public WebMarket getMarket(int marketId) throws WebScrapingException {

        String marketURL = Urls.MARKET_URL.getURL(marketId);

        try {
            Document doc = httpTemplate.getForDocument(marketURL);
            String jsonResponse = doc.text().replace("while(1) {};", "");
            WebMarket webMarket = jsonObjectMapper.readValue(jsonResponse, WebMarket.class);

            return webMarket;
        }
        catch (Exception exception) {
            throw new WebScrapingException("Error occurred while extracting market information from " +
                    "the following URL: " + marketURL , exception);
        }
    }

    @Cacheable("betfair-today-tennis-in-play-coupon")
    public TennisInPlayCoupon getTodayInPlayTennisCoupon() throws WebScrapingException {

        String tennisCouponPageContent;
        String url = Urls.TENNIS_TODAY_IN_PLAY_COUPON.getURL();
        try {
            tennisCouponPageContent = httpTemplate.getForContent(url);
        }
        catch (HttpClientException exception) {
            throw new WebScrapingException("Error occurred while extracting today in-play tennis markets data from " +
                    "the following URL: " + url , exception);
        }

        TennisInPlayCouponExtractor dataExtractor = new TennisInPlayCouponExtractor();
        return dataExtractor.extract(tennisCouponPageContent);
    }

    private static enum Urls {
        TENNIS_TODAY_IN_PLAY_COUPON("http://uk.site.sports.betfair.com/betting/LoadCouponDataAction.do?ci=10041010"),
        MARKET_URL("http://uk.site.sports.betfair.com/betting/api/json/getPartialMarketData.do?mi=%s&prevcache=%s", 2);

        private String format;
        private int argumentCount;

        private Urls(String format, int arguments) {
            this.format = format;
            this.argumentCount = arguments;
        }

        private Urls(String format) {
            this(format, 0);
        }

        public String getURL() {
            if(argumentCount != 0) {
                throw new IllegalArgumentException(
                        String.format("The %s URL requires %s argumentCount but 0 argumentCount has been supplied",
                                this.name(), argumentCount));
            }

            return String.format(this.format);
        }

        public String getURL(Object... arguments) {
            if(argumentCount != arguments.length) {
                throw new IllegalArgumentException(
                        String.format("The %s URL requires %s argumentCount but %s argumentCount has been supplied",
                                this.name(), argumentCount, arguments.length));
            }

            return String.format(this.format, arguments);
        }
    }
}
