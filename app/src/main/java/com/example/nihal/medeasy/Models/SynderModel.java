package com.example.nihal.medeasy.Models;

public class SynderModel {

    private String drug , syndrome , date ;

        public String getDrug() {
            return drug;
        }

        public void setDrug(String drug) {
            this.drug = drug;
        }

        public String getSyndrome() {
            return syndrome;
        }

        public void setSyndrome(String syndrome) {
            this.syndrome = syndrome;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public SynderModel(String syndrome, String drug, String date) {
            this.drug = drug;
            this.syndrome = syndrome;
            this.date = date;
        }

        public SynderModel() {
        }


}
