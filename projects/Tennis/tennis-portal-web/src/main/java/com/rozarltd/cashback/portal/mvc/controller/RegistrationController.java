package com.rozarltd.cashback.portal.mvc.controller;

import com.rozarltd.cashback.portal.form.RegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {

    @RequestMapping(method = RequestMethod.GET)
    public String onGet() {
        return "registration";        
    }

    @RequestMapping(method = RequestMethod.POST)
    public void onPost(@ModelAttribute RegistrationForm registrationForm) {

    }

    @ModelAttribute("registrationForm")
    public RegistrationForm getRegistrationForm() {
        return new RegistrationForm();
    }
}
