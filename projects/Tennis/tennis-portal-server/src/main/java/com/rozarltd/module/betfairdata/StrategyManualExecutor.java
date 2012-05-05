package com.rozarltd.module.betfairdata;

import com.rozarltd.module.betfairdata.parser.BetfairDataRow;
import com.rozarltd.module.betfairdata.parser.BetfairDataRowFieldSetMapper;
import com.rozarltd.module.betfairdata.strategy.BettingStrategyResult;
import com.rozarltd.module.betfairdata.strategy.BettingStrategyResultBuilder;
import com.rozarltd.module.betfairdata.strategy.StartingOddsIntervalStrategy;
import com.rozarltd.module.betfairdata.strategy.StartingOddsStrategy;
import com.rozarltd.module.betfairdata.strategy.Strategy;
import com.rozarltd.module.betfairdata.strategy.StrategyAnalyser;
import org.apache.commons.io.FileUtils;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StopWatch;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StrategyManualExecutor {

    // categories: woman/man , single/double, best of 3/5, points 250, 500, 1000, fed cup, davis cup, surface,
    // qualification
    // bookmaker profit margin: 0-15 15-30 ...

    private static File PENDING_DIR = new File("c:\\development\\Betfair Data\\pending");
    private static File PROCESSED_DIR = new File("c:\\development\\Betfair Data\\processed");
    private static File ANALYSE_DIR = new File("c:\\development\\Betfair Data\\analyse");


    // 16 fields
    public static void main(String[] args) throws Exception {
//        new BetfairDataFilter().process();

        analyseBettingData();

    }




    private static final void analyseBettingData() throws Exception {
        FlatFileItemReader<BetfairDataRow> itemReader = new FlatFileItemReader<BetfairDataRow>();

        DefaultLineMapper<BetfairDataRow> lineMapper = new DefaultLineMapper<BetfairDataRow>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(BetfairDataRowFieldSetMapper.PROCESSED_FIELDS);
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new BetfairDataRowFieldSetMapper());

        itemReader.setLineMapper(lineMapper);
//        itemReader.open(new ExecutionContext());

        StopWatch watch = new StopWatch();
        watch.start();
        BetfairDataRow dataRow;
        List<BetfairDataRow> data = new ArrayList<BetfairDataRow>();

        Collection<File> files = FileUtils.listFiles(ANALYSE_DIR, new String[]{"csv"}, false);
        StopWatch loadingWatch = new StopWatch();
        loadingWatch.start();
        for(File file: files) {
            itemReader.setResource(new FileSystemResource(file));
            itemReader.open(new ExecutionContext());
            while ((dataRow = itemReader.read()) != null) {
                data.add(dataRow);
            }
            itemReader.close();
            loadingWatch.stop();
            System.out.println(String.format("%s : %s", loadingWatch.getTotalTimeSeconds(), file.getName()));
            loadingWatch.start();
        }
        loadingWatch.stop();

        executeStartingOddsStrategy(data);
//        executeStartingOddsIntervalStrategy(data);
//        executeStartingOddsIntervalStrategyAggregateResult(data);

        watch.stop();

        System.out.println("duration: " + watch.getTotalTimeSeconds());
//        System.out.println("result: " + strategyResult);
    }

//    private List<BetfairDataRow>  loadDataFromFiles() {
//
//    }

    private static void executeStartingOddsStrategy(List<BetfairDataRow> data) {
        for (float maxOdds = 1.1f; maxOdds < 2.01; maxOdds += 0.1) {
            Strategy bettingStrategy = new StartingOddsStrategy(maxOdds);
            BettingStrategyResult strategyResult = new StrategyAnalyser().processAndAnalyse(data, bettingStrategy);
            System.out.println("result: " + strategyResult);
        }
    }

    private static void executeStartingOddsIntervalStrategy(List<BetfairDataRow> data) {

        StrategyAnalyser strategyAnalyser = new StrategyAnalyser();

        for (float maxOdds = 1.0f; maxOdds < 1.95; maxOdds += 0.1) {
            Strategy bettingStrategy = new StartingOddsIntervalStrategy(maxOdds, maxOdds + 0.1f);
            BettingStrategyResult strategyResult = strategyAnalyser.processAndAnalyse(data, bettingStrategy);

            System.out.println("result: " + strategyResult);
        }
    }

    private static void executeStartingOddsIntervalStrategyAggregateResult(List<BetfairDataRow> data) {

        StrategyAnalyser strategyAnalyser = new StrategyAnalyser();
        BettingStrategyResultBuilder resultBuilder = new BettingStrategyResultBuilder();

        for (float maxOdds = 1.0f; maxOdds < 1.95; maxOdds += 0.1) {
            Strategy bettingStrategy = new StartingOddsIntervalStrategy(maxOdds, maxOdds + 0.1f);
            strategyAnalyser.processAndAnalyse(data, bettingStrategy, resultBuilder);
        }

        System.out.println("result: " + resultBuilder.buildResult());
    }
}
