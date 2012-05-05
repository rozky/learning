package com.rozarltd.module.atpwebsite.service;

import com.rozarltd.module.atpwebsite.domain.TennisTournament;
import com.rozarltd.module.atpwebsite.page.AtpWebsite;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtpWebsiteClient {
    public List<TennisTournament> getTournaments() {
        List<TennisTournament> tournaments = AtpWebsite.eventCalendar().getTournaments();
        return tournaments;
    }
}
