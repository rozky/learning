package com.rozarltd.module.betfairdata.service;

import com.rozarltd.module.betfairdata.BetfairDataFilter;
import com.rozarltd.module.betfairdata.parser.BetfairDataRow;
import com.rozarltd.module.betfairdata.repository.MarketDataRepository;
import com.rozarltd.module.betfairdata.strategy.BetfairDataProcessor;
import com.rozarltd.module.betfairdata.strategy.MarketData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class BetfairDataService {
    private MarketDataRepository marketDataRepository;
    private BetfairDataReader dataReader;

    @Autowired
    public BetfairDataService(MarketDataRepository marketDataRepository, BetfairDataReader dataLoader) {
        this.marketDataRepository = marketDataRepository;
        this.dataReader = dataLoader;
    }

    public void filter() throws Exception {
        new BetfairDataFilter().process();
    }

    public void process() {

    }

    public Collection<MarketData> process(List<BetfairDataRow> data) {
        Map<Integer, MarketData> processedData = new BetfairDataProcessor().process(data);
        return processedData.values();
    }



    public void processAndSave() {
        List<BetfairDataRow> data = dataReader.read();
        Collection<MarketData> processedData = process(data);

        if(processedData != null && processedData.size() > 0) {
            marketDataRepository.save(processedData);
        }
    }

    public void loadProcessedData() {

    }
}
