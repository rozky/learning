package com.rozarltd.module.betfairdata.service;

import com.rozarltd.module.betfairdata.BetfairDataReaderBuilder;
import com.rozarltd.module.betfairdata.parser.BetfairDataRow;
import com.rozarltd.module.betfairdata.parser.BetfairDataRowFieldSetMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class BetfairDataReader {
    private static File ANALYSE_DIR = new File("c:\\development\\Betfair Data\\analyse");

    public List<BetfairDataRow> read() {
        FlatFileItemReader<BetfairDataRow> reader = new BetfairDataReaderBuilder()
                .fields(BetfairDataRowFieldSetMapper.PROCESSED_FIELDS).build();


        List<BetfairDataRow> data = new ArrayList<BetfairDataRow>();

        Collection<File> files = FileUtils.listFiles(ANALYSE_DIR, new String[]{"csv"}, false);

        BetfairDataRow dataRow;
        for(File file: files) {
            reader.setResource(new FileSystemResource(file));
            reader.open(new ExecutionContext());

            while ((dataRow = readNext(reader))  != null) {
                data.add(dataRow);
            }

            reader.close();

            System.out.println(String.format("Loaded %s", file.getName()));
        }
        return data;
    }

    private BetfairDataRow readNext(FlatFileItemReader<BetfairDataRow> reader) {
        try {
            return reader.read();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
