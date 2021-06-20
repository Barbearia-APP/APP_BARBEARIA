package io.osvaldocabral.appbarbearia.Model;

import java.util.UUID;

public class CreateServiceDatetime {

    public CreateServiceDatetime(String date, String time, String name) {
        this.date = date;
        this.time = time;
        this.name = name;
        uui = UUID.randomUUID().toString();
    }

    private String uui;
    private String date;
    private String time;
    private String name;

    public String getUui() {
        return uui;
    }

    public void setUui(String uui) {
        this.uui = uui;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
