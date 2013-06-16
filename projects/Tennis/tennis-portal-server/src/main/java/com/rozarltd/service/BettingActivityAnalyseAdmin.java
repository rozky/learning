package com.rozarltd.service;

import com.rozarltd.entity.analyse.DayBettingStats;

import java.util.Date;
import java.util.List;

public interface BettingActivityAnalyseAdmin {
    void performAnalysisAndSaveResult(Date from, Date to);
    List<DayBettingStats> load();
    void delete();
}
