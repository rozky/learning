package com.rozarltd.module.betfairdata;

import com.rozarltd.module.betfairdata.parser.BetfairDataRow;
import com.rozarltd.module.betfairdata.parser.BetfairDataRowFieldSetMapper;
import com.rozarltd.module.betfairdata.strategy.*;
import org.apache.commons.io.FileUtils;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MarketDataAnalyser {
    private static File ANALYSE_DIR = new File("c:\\development\\Betfair Data\\analyse");

    public final void analyse() throws Exception {
        FlatFileItemReader<BetfairDataRow> reader = new BetfairDataReaderBuilder()
                .fields(BetfairDataRowFieldSetMapper.PROCESSED_FIELDS).build();

        BetfairDataRow dataRow;
        List<BetfairDataRow> data = new ArrayList<BetfairDataRow>();

        Collection<File> files = FileUtils.listFiles(ANALYSE_DIR, new String[]{"csv"}, false);

        for(File file: files) {
            reader.setResource(new FileSystemResource(file));
            reader.open(new ExecutionContext());

            while ((dataRow = reader.read()) != null) {
                data.add(dataRow);
            }

            reader.close();

            System.out.println(String.format("%s", file.getName()));
        }

        Map<Integer, MarketData> markets = new BetfairDataProcessor().process(data);
    }

}
