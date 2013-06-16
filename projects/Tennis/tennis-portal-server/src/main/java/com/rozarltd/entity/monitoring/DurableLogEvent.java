package com.rozarltd.entity.monitoring;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DurableLogEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String type;
    private long date;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DurableLogEvent withType(String type) {
        this.type = type;
        return this;
    }

    public DurableLogEvent withText(String text) {
        this.text = text;
        return this;
    }

    public DurableLogEvent withCreateNow() {
        this.date = new Date().getTime();
        return this;
    }
}
