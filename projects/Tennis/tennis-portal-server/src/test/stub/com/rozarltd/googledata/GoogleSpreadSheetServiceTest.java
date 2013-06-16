package com.rozarltd.googledata;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GoogleSpreadSheetServiceTest {
    private static final String SPREADSHEET_FEED_URL = "https://spreadsheets.google.com/feeds/spreadsheets/private/full";
    private static final String BETTING_STATS_SPREADSHEET_NAME = "BETTING_STATS";

    @Test
    public void shouldCreateDocument() throws Exception {
        SpreadsheetService service = getSpreadsheetService();
        SpreadsheetEntry bettingStatsSpreadsheet = getBettingStatsSpreadsheet(service);

        WorksheetFeed worksheetFeed = service.getFeed(
                bettingStatsSpreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
           List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
           WorksheetEntry worksheet = worksheets.get(0);

           // Fetch the list feed of the worksheet.
           URL listFeedUrl = worksheet.getListFeedUrl();
           ListFeed listFeed = service.getFeed(listFeedUrl, ListFeed.class);

        SimpleDateFormat dateformater = new SimpleDateFormat("yyyy/MM/dd");

        ListEntry row = new ListEntry();
        row.getCustomElements().setValueLocal("date", dateformater.format(DateUtils.ceiling(new Date(), Calendar.DATE)));
        row.getCustomElements().setValueLocal("won-amount", "352.45");
        row.getCustomElements().setValueLocal("lost-amount", "240.23");
        row.getCustomElements().setValueLocal("bet-count", "3");
        row.getCustomElements().setValueLocal("profit", "120.34");

        row = service.insert(listFeedUrl, row);
        row = service.insert(listFeedUrl, row);
        row = service.insert(listFeedUrl, row);

    }

    private SpreadsheetService getSpreadsheetService() throws Exception {
        SpreadsheetService service =
                new SpreadsheetService("MySpreadsheetIntegration-v1");
        service.setUserCredentials("TODO: {user-email} ", "TODO: {user-password}");

        return service;
    }

    private SpreadsheetEntry getBettingStatsSpreadsheet(SpreadsheetService service) throws Exception {
        SpreadsheetFeed feed = service.getFeed(new URL(SPREADSHEET_FEED_URL), SpreadsheetFeed.class);
        List<SpreadsheetEntry> spreadsheets = feed.getEntries();

        // Iterate through all of the spreadsheets returned
        for (SpreadsheetEntry spreadsheet : spreadsheets) {
            if(BETTING_STATS_SPREADSHEET_NAME.equals(spreadsheet.getTitle().getPlainText())) {
                return spreadsheet;
            }
        }

        return null;
    }
}
