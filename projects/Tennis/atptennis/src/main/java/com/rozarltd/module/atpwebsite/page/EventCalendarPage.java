package com.rozarltd.module.atpwebsite.page;

import com.rozarltd.module.atpwebsite.domain.TennisTournamentCategory;
import com.rozarltd.module.atpwebsite.domain.TennisTournament;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventCalendarPage {
    private static final String PAGE_URL = "http://www.atpworldtour.com/Tournaments/Event-Calendar.aspx";
    private static final String[] TOURNAMENT_START_DATE_FORMATS = {"DD.MM.yyyy"};
    private Document page;

    public static EventCalendarPage navigate() {
        Document page = null;
        try {
            page = Jsoup.connect(PAGE_URL).get();
        } catch (IOException e) {
            // todo - handle status
        }
        return new EventCalendarPage(page);
    }

    public EventCalendarPage(Document page) {
        this.page = page;
    }

    public List<TennisTournament> getTournaments() {
        EventsCalendarTable calendar = new EventsCalendarTable(page.getElementById("calendarContentWrap"));
        List<TennisTournament> tournaments = new ArrayList<TennisTournament>();
        for(TournamentTableRow tournamentRow: calendar.getTournaments()) {
            TennisTournament tournament = new TennisTournament();
            tournament.setCategory(TennisTournamentCategory.from(tournamentRow.getCategoryImageAlt()));
            tournament.setStartDate(parseStartDate(tournamentRow.getStartDate()));
            tournament.setName(tournamentRow.getName());
            tournament.setLocation(tournamentRow.getLocation());
            tournament.setSurface(tournamentRow.getSurface());
            tournament.setPrizeMoney(NumberUtils.toInt(tournamentRow.getPrizeMoney().replaceAll("\\D",""), 0));

            tournaments.add(tournament);
        }

        return tournaments;
    }

    private Date parseStartDate(String startDate) {
        try {
            return DateUtils.parseDate(startDate.replaceAll("Live", ""), TOURNAMENT_START_DATE_FORMATS);
        } catch (ParseException e) {
            return null;
        }
    }
}
