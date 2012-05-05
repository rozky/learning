package com.rozarltd.module.betfairdata.parser;

import com.rozarltd.module.betfairapi.domain.BetfairEventId;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class BetfairDataRowFieldSetMapper implements FieldSetMapper<BetfairDataRow> {
    public static final String[] ORIGINAL_FIELDS = new String[] {"sportId", "eventId", "settledDate", "fullMarketName",
            "scheduledOff", "marketName", "actualOff", "selectionId", "selectionName", "odds", "numberOfBets",
            "volumeMatched", "latestTaken", "firstTaken", "winFlag", "inPlay"};

    public static final String[] PROCESSED_FIELDS = new String[] {"sportId", "eventId", "settledDate", "fullMarketName",
            "marketName", "selectionId", "selectionName", "odds", "latestTaken", "firstTaken", "winFlag", "inPlay"};

    private static final BetfairDataRow EMPTY_ROW = new BetfairDataRow();

    @Override
    public BetfairDataRow mapFieldSet(FieldSet fieldSet) throws BindException {
        int sportId = fieldSet.readInt("sportId");
        String marketName = fieldSet.readString("marketName");
        if(BetfairEventId.tennis.getId() == sportId && "Match Odds".equals(marketName)) {
            BetfairDataRow row = new BetfairDataRow();
            row.setSportId(sportId);
            row.setEventId(fieldSet.readInt("eventId"));
            row.setSettledDate(fieldSet.readString("settledDate"));
            row.setFullMarketName(fieldSet.readString("fullMarketName"));
            row.setMarketName(marketName);
            row.setSelectionId(fieldSet.readLong("selectionId"));
            row.setSelectionName(fieldSet.readString("selectionName"));
            row.setOdds(fieldSet.readFloat("odds"));
            row.setLatestTaken(fieldSet.readString("latestTaken"));
            row.setFirstTaken(fieldSet.readString("firstTaken"));
            row.setWin(fieldSet.readString("winFlag"));
            row.setInPlay(fieldSet.readString("inPlay"));

            return row;
        }
        else {
            return EMPTY_ROW;
        }
    }
}
