package com.rozarltd.betting.portal.tennis.web.controller;

import com.rozarltd.betting.portal.tennis.web.ModelAttributeName;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
//    private AccountWalletService accountStatementService;

//    @Autowired
//    public HomeController(AccountWalletService accountStatementService) {
//        this.accountStatementService = accountStatementService;
//    }

    // todo
    public void doGetDefaultHandler() {

    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public void doGet(Model model) {
        model.addAttribute(ModelAttributeName.welcome, "hello");
//        model.addAttribute(ModelAttributeName.accountStatementItems.toString(), accountStatementService.getStatement(0, 10));
    }
}
