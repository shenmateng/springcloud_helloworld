package com.mt.database;

import java.io.Serializable;

public class Action implements Serializable {

    private static final long serialVersionUID = -217663931984692062L;

    private String text;

    private String html;

    private String alarmText;

    private String alarmHtml;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getAlarmText() {
        return alarmText;
    }

    public void setAlarmText(String alarmText) {
        this.alarmText = alarmText;
    }

    public String getAlarmHtml() {
        return alarmHtml;
    }

    public void setAlarmHtml(String alarmHtml) {
        this.alarmHtml = alarmHtml;
    }
}

