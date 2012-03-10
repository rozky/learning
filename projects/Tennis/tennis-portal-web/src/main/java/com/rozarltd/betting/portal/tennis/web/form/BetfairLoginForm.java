package com.rozarltd.betting.portal.tennis.web.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class BetfairLoginForm {

    @NotBlank
    @Length(min = 5, max = 20)
    private String username;

    @NotBlank
    @Length(min = 5, max = 20)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
