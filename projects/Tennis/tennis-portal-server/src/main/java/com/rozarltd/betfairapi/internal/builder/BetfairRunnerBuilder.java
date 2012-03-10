package com.rozarltd.betfairapi.internal.builder;

import com.rozarltd.betfairapi.domain.market.BetfairRunner;
import com.rozarltd.betfairapi.domain.market.BetfairRunnerPrice;
import com.rozarltd.betfairapi.internal.parser.GetCompleteMarketPricesCompressedParser;
import com.rozarltd.util.java.lang.DoubleUtils;
import com.rozarltd.util.java.lang.IntegerUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BetfairRunnerBuilder {
    private static final String VALUE_FIELDS_DELIMITER = "~";

    private BetfairRunner runner = new BetfairRunner();

    public BetfairRunnerBuilder withRunnerDetails(String compressedRunnerDetails) {
        String[] fields = StringUtils.splitPreserveAllTokens(compressedRunnerDetails, VALUE_FIELDS_DELIMITER);

        runner.setRunnerId(IntegerUtils.valueOf(fields[GetCompleteMarketPricesCompressedParser.Position.RunnerDetailsField.runnerId]));
        runner.setOrderIndex(IntegerUtils.valueOf(fields[GetCompleteMarketPricesCompressedParser.Position.RunnerDetailsField.orderIndex]));
        runner.setTotalAmountMatched(DoubleUtils.valueOf(fields[GetCompleteMarketPricesCompressedParser.Position.RunnerDetailsField.totalAmountMatched]));
        runner.setLastPriceMatched(DoubleUtils.valueOf(fields[GetCompleteMarketPricesCompressedParser.Position.RunnerDetailsField.lastPriceMatched]));
        runner.setHandicap(DoubleUtils.valueOf(fields[GetCompleteMarketPricesCompressedParser.Position.RunnerDetailsField.handicap]));
        runner.setReductionFactor(DoubleUtils.valueOf(fields[GetCompleteMarketPricesCompressedParser.Position.RunnerDetailsField.reductionFactor]));
        runner.setVacant(Boolean.valueOf(fields[GetCompleteMarketPricesCompressedParser.Position.RunnerDetailsField.vacant]));
        runner.setAsianLineId(IntegerUtils.valueOf(fields[GetCompleteMarketPricesCompressedParser.Position.RunnerDetailsField.asianLineId]));
        runner.setFarSpPrice(DoubleUtils.valueOf(fields[GetCompleteMarketPricesCompressedParser.Position.RunnerDetailsField.farSpPrice]));
        runner.setNearSpPrice(DoubleUtils.valueOf(fields[GetCompleteMarketPricesCompressedParser.Position.RunnerDetailsField.nearSpPrice]));
        runner.setActualSpPrice(DoubleUtils.valueOf(fields[GetCompleteMarketPricesCompressedParser.Position.RunnerDetailsField.actualSpPrice]));

        return this;
    }

    public BetfairRunnerBuilder withRunnerPrices(String compressedRunnerPrices) {
        String[] fields = StringUtils.splitPreserveAllTokens(compressedRunnerPrices, VALUE_FIELDS_DELIMITER);
        List<BetfairRunnerPrice> runnerPrices = new ArrayList<BetfairRunnerPrice>();
        for(int i = 0; i < fields.length - 1; i+=5) {
            runnerPrices.add(new BetfairRunnerPrice(
                    DoubleUtils.valueOf(fields[i + GetCompleteMarketPricesCompressedParser.Position.RunnerPriceField.price]),
                    DoubleUtils.valueOf(fields[i + GetCompleteMarketPricesCompressedParser.Position.RunnerPriceField.totalAvailableToBack]),
                    DoubleUtils.valueOf(fields[i + GetCompleteMarketPricesCompressedParser.Position.RunnerPriceField.totalAvailableToLay]),
                    DoubleUtils.valueOf(fields[i + GetCompleteMarketPricesCompressedParser.Position.RunnerPriceField.totalBspLayLiability]),
                    DoubleUtils.valueOf(fields[i + GetCompleteMarketPricesCompressedParser.Position.RunnerPriceField.totalBspBackersStakeVolume])
            ));
        }

        runner.setPrices(runnerPrices);

        return this;
    }

    public BetfairRunner build() {
        return runner;
    }
}
