package com.rozarltd.betting.portal.tennis.web.betfair.controller;

import com.rozarltd.module.betfairapi.service.AccountService;
import com.rozarltd.betting.portal.tennis.web.ModelAttributeName;
import com.rozarltd.betting.portal.tennis.web.Routing;
import com.rozarltd.betting.portal.tennis.web.form.BetfairLoginForm;
import com.rozarltd.betting.portal.tennis.web.form.ValidationErrorAdapter;
import com.rozarltd.betting.portal.tennis.web.session.SessionAttributeName;
import com.rozarltd.account.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
//@RequestMapping("/betfair")
public class BetfairController {
    private AccountService betfairAccountService;

    @Autowired
    public BetfairController(AccountService betfairAccountService) {
        this.betfairAccountService = betfairAccountService;
    }

    @RequestMapping(value = Routing.BETFAIR_HOME, method = RequestMethod.GET)
    public RedirectView home() {
        return new RedirectView(Routing.BETFAIR_ACTIONS);
    }

    @RequestMapping(value = Routing.BETFAIR_ACTIONS, method = RequestMethod.GET)
    public void renderActions() {

    }


    @RequestMapping(value = Routing.BETFAIR_LOGIN, method = RequestMethod.GET)
    public void renderLoginForm(ModelMap modelMap) {
        buildLoginFormObject(modelMap);
        buildLoginFormValidation(modelMap);
    }

    @RequestMapping(value = Routing.BETFAIR_LOGIN, method = RequestMethod.POST)
    public RedirectView handleLoginFormSubmit(HttpServletRequest request,
                                              @Valid BetfairLoginForm loginForm, BindingResult bindingResult,
                                              RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(true);
        if (bindingResult.hasErrors()) {
            // todo - add form object to flash scope so it can be read again
            // todo - add binding result to flash scope
            redirectAttributes.addFlashAttribute(ModelAttributeName.formObject, loginForm);
            redirectAttributes.addFlashAttribute(ModelAttributeName.formObjectResultBinding, bindingResult);
            return new RedirectView(Routing.BETFAIR_LOGIN);
        }
        String betfairSession = betfairAccountService.login(loginForm.getUsername(), loginForm.getPassword());
        if (betfairSession != null) {
            User currentUser = new User(loginForm.getUsername(), betfairSession, null);
            session.setAttribute(SessionAttributeName.currentUser, currentUser);

            // todo
            redirectAttributes.addFlashAttribute(ModelAttributeName.flashMessage, "");

            return new RedirectView("actions");
        }

        return new RedirectView("login");
    }

    private void buildLoginFormObject(ModelMap modelMap) {
        if (!modelMap.containsAttribute(ModelAttributeName.formObject)) {
            modelMap.addAttribute(ModelAttributeName.formObject, new BetfairLoginForm());
        }
    }

    private void buildLoginFormValidation(ModelMap modelMap) {
        BindingResult bindingResult = (BindingResult) modelMap.get(ModelAttributeName.formObjectResultBinding);
        modelMap.addAttribute(ModelAttributeName.validationErrors, new ValidationErrorAdapter(bindingResult));
    }

}
