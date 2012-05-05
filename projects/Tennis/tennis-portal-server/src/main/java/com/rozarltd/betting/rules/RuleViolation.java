package com.rozarltd.betting.rules;

public class RuleViolation {
    private String violationMessage;

    public RuleViolation(String violationMessage) {
        this.violationMessage = violationMessage;
    }

    @Override
    public String toString() {
        return violationMessage;
    }
}
