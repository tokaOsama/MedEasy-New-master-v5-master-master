package com.example.nihal.medeasy.Models;

public class LabResultModle {

    private String name,var , pos , nav ;

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public LabResultModle(String name, String var, String pos, String nav) {
        this.name = name;
        this.var = var;
        this.pos = pos;
        this.nav = nav;
    }

    public LabResultModle() {

    }
}
