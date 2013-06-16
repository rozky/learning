package com.rozarltd.service;

import com.rozarltd.entity.analyse.DayBettingStats;
import com.rozarltd.repository.DayBettingStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class BetfairBettingActivityAnalyseAdmin implements BettingActivityAnalyseAdmin {

    @Autowired
    private BettingActivityAnalyseService analyseService;

    @Autowired
    private DayBettingStatsRepository dayBettingStatsRepository;

    @Override
    public void performAnalysisAndSaveResult(Date from, Date to) {
        System.out.println("starting analysis");
        List<DayBettingStats> analyse = analyseService.analyseByDate(from, to);
        System.out.println("starting saving");
        dayBettingStatsRepository.save(analyse);
    }

    @Override
    public List<DayBettingStats> load() {
        return (List<DayBettingStats>) dayBettingStatsRepository.findAll();
    }

    @Override
    public void delete() {
        dayBettingStatsRepository.deleteAll();
    }
}
