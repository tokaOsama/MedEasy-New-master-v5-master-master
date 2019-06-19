package com.example.nihal.medeasy.Models;

public class AssessmentSheetModel {

    private String yourComplaint , pastHistory  ;
    private String DM;
    private String Smoker;
    private String Hypertension ;
    private String Dysliplidemia ;
    private String GeneralLook;
    private String level_of_consclousness;
    private String pulse_rate;
    private String pulse_rhythm;
    private String pulse_equality;
    private String peripheral_pulsation;
    private String vital_signs_bp_right;
    private String vital_signs_bp_left;
    private String vital_signs_temp;
    private String vital_signs_rr;
    private String vital_signs_o2_saturation;
    private String chest_pain;
    private String head_and_neck_neck;
    private String ll_oedema_1;
    private String ll_oedema_2;
    private String ll_oedema_3;

    public AssessmentSheetModel(String yourComplaint) {
        this.yourComplaint = yourComplaint;
    }

    public AssessmentSheetModel(String yourComplaint, String pastHistory, String presentHistory, String DM, String smoker, String hypertension, String dysliplidemia, String generalLook, String level_of_consclousness, String pulse_rate, String pulse_rhythm, String pulse_equality, String peripheral_pulsation, String peripheral_pulsation_notfelt, String vital_signs_bp_right, String vital_signs_bp_left, String vital_signs_temp, String vital_signs_rr, String vital_signs_o2_saturation, String chest_pain, String head_and_neck_neck, String ll_oedema_1, String ll_oedema_2, String ll_oedema_3) {
        this.yourComplaint = yourComplaint;
        this.pastHistory = pastHistory;
        this.DM = DM;
        Smoker = smoker;
        Hypertension = hypertension;
        Dysliplidemia = dysliplidemia;
        GeneralLook = generalLook;
        this.level_of_consclousness = level_of_consclousness;
        this.pulse_rate = pulse_rate;
        this.pulse_rhythm = pulse_rhythm;
        this.pulse_equality = pulse_equality;
        this.peripheral_pulsation = peripheral_pulsation;
        this.vital_signs_bp_right = vital_signs_bp_right;
        this.vital_signs_bp_left = vital_signs_bp_left;
        this.vital_signs_temp = vital_signs_temp;
        this.vital_signs_rr = vital_signs_rr;
        this.vital_signs_o2_saturation = vital_signs_o2_saturation;
        this.chest_pain = chest_pain;
        this.head_and_neck_neck = head_and_neck_neck;
        this.ll_oedema_1 = ll_oedema_1;
        this.ll_oedema_2 = ll_oedema_2;
        this.ll_oedema_3 = ll_oedema_3;
    }

    public String getYourComplaint() {
        return yourComplaint;
    }

    public String getPastHistory() {
        return pastHistory;
    }


    public String getDM() {
        return DM;
    }

    public String getSmoker() {
        return Smoker;
    }

    public String getHypertension() {
        return Hypertension;
    }

    public String getDysliplidemia() {
        return Dysliplidemia;
    }

    public String getGeneralLook() {
        return GeneralLook;
    }

    public String getLevel_of_consclousness() {
        return level_of_consclousness;
    }

    public String getPulse_rate() {
        return pulse_rate;
    }

    public String getPulse_rhythm() {
        return pulse_rhythm;
    }

    public String getPulse_equality() {
        return pulse_equality;
    }

    public String getPeripheral_pulsation() {
        return peripheral_pulsation;
    }


    public String getVital_signs_bp_right() {
        return vital_signs_bp_right;
    }

    public String getVital_signs_bp_left() {
        return vital_signs_bp_left;
    }

    public String getVital_signs_temp() {
        return vital_signs_temp;
    }

    public String getVital_signs_rr() {
        return vital_signs_rr;
    }

    public String getVital_signs_o2_saturation() {
        return vital_signs_o2_saturation;
    }

    public String getChest_pain() {
        return chest_pain;
    }

    public String getHead_and_neck_neck() {
        return head_and_neck_neck;
    }

    public String getLl_oedema_1() {
        return ll_oedema_1;
    }

    public String getLl_oedema_2() {
        return ll_oedema_2;
    }

    public String getLl_oedema_3() {
        return ll_oedema_3;
    }


    public void setYourComplaint(String yourComplaint) {
        this.yourComplaint = yourComplaint;
    }

    public void setPastHistory(String pastHistory) {
        this.pastHistory = pastHistory;
    }


    public void setDM(String DM) {
        this.DM = DM;
    }

    public void setSmoker(String smoker) {
        Smoker = smoker;
    }

    public void setHypertension(String hypertension) {
        Hypertension = hypertension;
    }

    public void setDysliplidemia(String dysliplidemia) {
        Dysliplidemia = dysliplidemia;
    }

    public void setGeneralLook(String generalLook) {
        GeneralLook = generalLook;
    }

    public void setLevel_of_consclousness(String level_of_consclousness) {
        this.level_of_consclousness = level_of_consclousness;
    }

    public void setPulse_rate(String pulse_rate) {
        this.pulse_rate = pulse_rate;
    }

    public void setPulse_rhythm(String pulse_rhythm) {
        this.pulse_rhythm = pulse_rhythm;
    }

    public void setPulse_equality(String pulse_equality) {
        this.pulse_equality = pulse_equality;
    }

    public void setPeripheral_pulsation(String peripheral_pulsation) {
        this.peripheral_pulsation = peripheral_pulsation;
    }

    public void setVital_signs_bp_right(String vital_signs_bp_right) {
        this.vital_signs_bp_right = vital_signs_bp_right;
    }

    public void setVital_signs_bp_left(String vital_signs_bp_left) {
        this.vital_signs_bp_left = vital_signs_bp_left;
    }

    public void setVital_signs_temp(String vital_signs_temp) {
        this.vital_signs_temp = vital_signs_temp;
    }

    public void setVital_signs_rr(String vital_signs_rr) {
        this.vital_signs_rr = vital_signs_rr;
    }

    public void setVital_signs_o2_saturation(String vital_signs_o2_saturation) {
        this.vital_signs_o2_saturation = vital_signs_o2_saturation;
    }

    public void setChest_pain(String chest_pain) {
        this.chest_pain = chest_pain;
    }

    public void setHead_and_neck_neck(String head_and_neck_neck) {
        this.head_and_neck_neck = head_and_neck_neck;
    }

    public void setLl_oedema_1(String ll_oedema_1) {
        this.ll_oedema_1 = ll_oedema_1;
    }

    public void setLl_oedema_2(String ll_oedema_2) {
        this.ll_oedema_2 = ll_oedema_2;
    }

    public void setLl_oedema_3(String ll_oedema_3) {
        this.ll_oedema_3 = ll_oedema_3;
    }
}

