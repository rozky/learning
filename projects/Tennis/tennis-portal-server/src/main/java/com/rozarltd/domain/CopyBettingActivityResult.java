package com.rozarltd.domain;

import java.util.Date;

public class CopyBettingActivityResult {
    private Date from;
    private Date to;
    private int count;

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public int getCount() {
        return count;
    }

    public CopyBettingActivityResult(Date from, Date to, int count) {
        this.from = from;
        this.to = to;
        this.count = count;
    }
}
