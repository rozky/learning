package com.rozarltd.module.atpwebsite.page;

import com.rozarltd.html.PageElement;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class EventsCalendarTable extends PageElement {

    public EventsCalendarTable(Element element) {
        super(element);
    }

    public List<TournamentTableRow> getTournaments() {
        Elements tournamentRows = element.select(".calendarTable tbody tr");

        List<TournamentTableRow> tournaments = new ArrayList<TournamentTableRow>();
        for(Element tournament: tournamentRows) {
            tournaments.add(new TournamentTableRow(tournament));
        }
        return tournaments;
    }
}
