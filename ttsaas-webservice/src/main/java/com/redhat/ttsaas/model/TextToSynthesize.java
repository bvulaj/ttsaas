package com.redhat.ttsaas.model;

import java.util.Date;

public class TextToSynthesize {
    private Date dateTime;
    private String text;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
