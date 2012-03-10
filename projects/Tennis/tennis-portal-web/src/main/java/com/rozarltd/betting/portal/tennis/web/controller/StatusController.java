package com.rozarltd.betting.portal.tennis.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StatusController {

    @RequestMapping(value = "/internal/status", method = RequestMethod.GET)
    public void status(ModelMap model) {
        model.addAttribute("jRebelEnabled", true);
    }
}
