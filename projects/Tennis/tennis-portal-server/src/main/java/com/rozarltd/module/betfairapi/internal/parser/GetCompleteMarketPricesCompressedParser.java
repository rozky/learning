package com.rozarltd.module.betfairapi.internal.parser;

import com.rozarltd.module.betfairapi.domain.market.BetfairMarketPrices;
import com.rozarltd.module.betfairapi.domain.market.BetfairRunner;
import com.rozarltd.module.betfairapi.internal.builder.BetfairRunnerBuilder;
import org.apache.commons.lang.StringUtils;

public class GetCompleteMarketPricesCompressedParser implements CompressedDataParser<BetfairMarketPrices> {
    private static final String TOP_LEVEL_OBJECTS_DELIMITER = ":";
    private static final String VALUE_FIELDS_DELIMITER = "~";
    private static final String RUNNER_PARTS_DELIMITER = "\\|";

    // todo - catch any exception are convert it to something more meaningful
    @Override
    public BetfairMarketPrices parse(String compressedData) {
        BetfairMarketPricesBuilder betfairMarketPricesBuilder = new BetfairMarketPricesBuilder();
        String[] topLevelObjects = StringUtils.splitPreserveAllTokens(compressedData, TOP_LEVEL_OBJECTS_DELIMITER);

        betfairMarketPricesBuilder.withMarketDetails(topLevelObjects[Position.MarketPricesField.compressedMarketDetails]);
        for (int i = 1; i < topLevelObjects.length; i++) {
            betfairMarketPricesBuilder.withRunner(topLevelObjects[i]);
        }

        return betfairMarketPricesBuilder.build();
    }

    public class BetfairMarketPricesBuilder {
        private BetfairMarketPrices betfairMarketPrices = new BetfairMarketPrices();

        public BetfairMarketPricesBuilder withMarketDetails(String compressedMarketDetails) {
            String[] fields = StringUtils.splitPreserveAllTokens(compressedMarketDetails,VALUE_FIELDS_DELIMITER);

            betfairMarketPrices.setMarketId(fields[Position.MarketDetailField.marketId]);
            betfairMarketPrices.setInPlayDelay(fields[Position.MarketDetailField.inPlayDelay]);

            return this;
        }

        public BetfairMarketPricesBuilder withRunner(String compressedRunner) {
            String[] fields = StringUtils.splitPreserveAllTokens(compressedRunner,RUNNER_PARTS_DELIMITER);

            BetfairRunner betfairRunner = new BetfairRunnerBuilder()
                    .withRunnerDetails(fields[Position.RunnerField.compressedRunnerDetails])
                    .withRunnerPrices(fields[Position.RunnerField.compressedRunnerPrices])
                    .build();

            betfairMarketPrices.getRunners().add(betfairRunner);

            return this;
        }

        public BetfairMarketPrices build() {
            return betfairMarketPrices;
        }
    }

    public interface Position {

        interface MarketPricesField {
            int compressedMarketDetails = 0;
        }

        interface MarketDetailField {
            int marketId = 0;
            int inPlayDelay = 1;
        }

        interface RunnerField {
            int compressedRunnerDetails = 0;
            int compressedRunnerPrices = 1;
        }

        interface RunnerDetailsField {
            int runnerId = 0;
            int orderIndex = 1;
            int totalAmountMatched = 2;
            int lastPriceMatched = 3;
            int handicap = 4;
            int reductionFactor = 5;
            int vacant = 6;
            int asianLineId = 7;
            int farSpPrice = 8;
            int nearSpPrice = 9;
            int actualSpPrice = 10;
        }

        interface RunnerPriceField {
            int price = 0;
            int totalAvailableToBack = 1;
            int totalAvailableToLay = 2;
            int totalBspLayLiability = 3;
            int totalBspBackersStakeVolume = 4;
        }
    }
}
