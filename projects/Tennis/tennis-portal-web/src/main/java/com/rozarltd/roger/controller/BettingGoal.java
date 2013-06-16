package com.rozarltd.roger.controller;

public class BettingGoal {
    private String description;
    private String value;

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public static class Builder {
        private BettingGoal goal = new BettingGoal();

        public Builder description(String description) {
            goal.description = description;
            return this;
        }

        public Builder value(String value) {
            goal.value = value;
            return this;
        }

        public BettingGoal build() {
            return goal;
        }
    }
}
