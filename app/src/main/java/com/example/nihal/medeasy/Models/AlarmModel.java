package com.example.nihal.medeasy.Models;

import java.util.ArrayList;

public class AlarmModel {
    String timeHour,timeMin,title;
    ArrayList<String> Days;

    public String getTimeHour() {
        return timeHour;
    }

    public void setTimeHour(String timeHour) {
        this.timeHour = timeHour;
    }

    public String getTimeMin() {
        return timeMin;
    }

    public void setTimeMin(String timeMin) {
        this.timeMin = timeMin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getDays() {
        return Days;
    }

    public void setDays(ArrayList<String> days) {
        Days = days;
    }
}
