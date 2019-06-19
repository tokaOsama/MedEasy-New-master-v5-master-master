package com.example.nihal.medeasy.Models;

public class AssessmentSheetsModle {

    private String drug, elt5ssos, description , date;


    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getElt5ssos() {
        return elt5ssos;
    }

    public void setElt5ssos(String elt5ssos) {
        this.elt5ssos = elt5ssos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AssessmentSheetsModle(String drug, String elt5ssos, String description, String date) {
        this.drug = drug;
        this.elt5ssos = elt5ssos;
        this.description = description;
        this.date = date;
    }

    public AssessmentSheetsModle() {
    }
}
