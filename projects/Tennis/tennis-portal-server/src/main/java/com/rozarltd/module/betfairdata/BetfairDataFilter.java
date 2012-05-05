package com.rozarltd.module.betfairdata;

import com.rozarltd.module.betfairdata.parser.BetfairDataRow;
import com.rozarltd.module.betfairdata.parser.BetfairDataRowFieldSetMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StopWatch;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BetfairDataFilter {
    private static File PENDING_DIR = new File("c:\\development\\Betfair Data\\pending");
    private static File PROCESSED_DIR = new File("c:\\development\\Betfair Data\\processed");

    public void process() throws Exception {
        DefaultLineMapper<BetfairDataRow> lineMapper = new DefaultLineMapper<BetfairDataRow>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(BetfairDataRowFieldSetMapper.ORIGINAL_FIELDS);
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new BetfairDataRowFieldSetMapper());

        FlatFileItemReader<BetfairDataRow> itemReader = new FlatFileItemReader<BetfairDataRow>();
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper);


        Collection<File> files = FileUtils.listFiles(PENDING_DIR, new String[]{"csv"}, false);
        for(File file: files) {
            System.out.println("Processing: " + file.getName());
            StopWatch watch = new StopWatch();
            watch.start();
            itemReader.setResource(new FileSystemResource(file));
            itemReader.open(new ExecutionContext());

            BetfairDataRow dataRow;
            int count = 0;
            int incorrectCount = 0;
            List<BetfairDataRow> processedData = new ArrayList<BetfairDataRow>();
            while ((dataRow = itemReader.read()) != null) {
                if (dataRow.isMatchOdds()) {
                    count++;
                    processedData.add(dataRow);
                } else {
                    incorrectCount++;
                }
            }
            itemReader.close();

            File outputFile = new File(PROCESSED_DIR, getProcessedFileName(file.getName()));
            writeProcessedItems(processedData, outputFile);
            watch.stop();

            System.out.println(String.format("%s : %s :%s : %s", outputFile.getName(), watch.getTotalTimeSeconds(), count, incorrectCount));
        }
    }

    private final static void writeProcessedItems(List<BetfairDataRow> items, File outputFile) throws Exception {
        FlatFileItemWriter<BetfairDataRow> itemWriter = new FlatFileItemWriter<BetfairDataRow>();
        itemWriter.setResource(new FileSystemResource(outputFile));
        itemWriter.setLineAggregator(new PassThroughLineAggregator<BetfairDataRow>());
        itemWriter.open(new ExecutionContext());
        itemWriter.write(items);
        itemWriter.close();
    }

    private static String getProcessedFileName(String fileName) {
        return fileName.replaceAll("bfinf_other_(\\d{6})to(\\d{6})_.*", "processed_$1_$2.csv");
    }
}
