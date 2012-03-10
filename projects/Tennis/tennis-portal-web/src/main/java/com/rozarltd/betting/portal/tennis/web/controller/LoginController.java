package com.rozarltd.betting.portal.tennis.web.controller;

import com.rozarltd.betting.portal.tennis.web.ModelAttributeName;
import com.rozarltd.betting.portal.tennis.web.Routing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = Routing.LOGIN, method = RequestMethod.GET)
    public void login(Model model) {
        model.addAttribute(ModelAttributeName.welcome, "hello");
    }
}
