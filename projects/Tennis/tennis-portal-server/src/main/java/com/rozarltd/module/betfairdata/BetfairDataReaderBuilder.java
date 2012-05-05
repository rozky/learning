package com.rozarltd.module.betfairdata;

import com.rozarltd.module.betfairdata.parser.BetfairDataRow;
import com.rozarltd.module.betfairdata.parser.BetfairDataRowFieldSetMapper;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

public class BetfairDataReaderBuilder {
    private String[] fields;

    public BetfairDataReaderBuilder fields(String[] fields) {
        this.fields = fields;
        return this;
    }

    public FlatFileItemReader<BetfairDataRow> build() {
        FlatFileItemReader<BetfairDataRow> reader = new FlatFileItemReader<BetfairDataRow>();

        DefaultLineMapper<BetfairDataRow> lineMapper = new DefaultLineMapper<BetfairDataRow>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        if(fields != null) {
            tokenizer.setNames(fields);
        }

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new BetfairDataRowFieldSetMapper());

        reader.setLineMapper(lineMapper);

        return reader;
    }
}
