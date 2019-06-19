package com.example.nihal.medeasy.Models;

public  class Drugs {
    public String name,dose,tperd,from,to;

    public Drugs(String name, String dose, String tperd, String from,String to) {
        this.name = name;
        this.dose = dose;
        this.tperd = tperd;
        this.from = from;
        this.to = to;
    }

    public Drugs() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getTperd() {
        return tperd;
    }

    public void setTperd(String tperd) {
        this.tperd = tperd;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}

