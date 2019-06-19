package com.example.nihal.medeasy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nihal.medeasy.Models.AssessmentSheetModel;

public class AssessmentSheet2AfterSecion extends AppCompatActivity {

    CheckBox level_of_consclousness_A , level_of_consclousness_P, level_of_consclousness_V, level_of_consclousness_U;
    EditText pulse_rate, pulse_rhythm, pulse_equality;
    CheckBox peripheral_pulsation_felt, peripheral_pulsation_notfelt;
    EditText vital_signs_bp_right, vital_signs_bp_left, vital_signs_temp, vital_signs_rr, vital_signs_o2_saturation;
    CheckBox chest_pain_yes, chest_pain_no, head_and_neck_neck_veins, head_and_neck_other;
    CheckBox ll_oedema_yes, ll_oedema_no, ll_oedema_right, ll_oedema_left, ll_oedema_bilateral, ll_oedema_pitting, ll_oedema_nonpitting;
    AssessmentSheetModel model;
    String level_of_consclousness, pulse_rates, pulse_rhythms, pulse_equalitys, peripheral_pulsation, chest_pain, head_and_neck_neck, ll_oedema1, ll_oedema2, ll_oedema3;
    TextView  back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment__sheet2__ater__secion);

        //
        AssessmentSheetModel sheetModel=new AssessmentSheetModel("hxhjdxbdhnd"
                ,"djdjnd","","0","0","0","0"
                ,"4","3","dhdj","dhdbx","dndj"
                ,"1","","djdnd","djdj"
                ,"jdndn","dndm"
                ,"jdjfnd","1","0","1","2","1");

        //


        level_of_consclousness_A = findViewById(R.id.level_of_consclousness_A);
        level_of_consclousness_A.setEnabled(false);
        level_of_consclousness_P = findViewById(R.id.level_of_consclousness_P);
        level_of_consclousness_P.setEnabled(false);
        level_of_consclousness_V = findViewById(R.id.level_of_consclousness_V);
        level_of_consclousness_V.setEnabled(false);
        level_of_consclousness_U = findViewById(R.id.level_of_consclousness_U);
        level_of_consclousness_U.setEnabled(false);

        if (sheetModel.getLevel_of_consclousness().equals("0")) {
            level_of_consclousness_A.setChecked(true);
        } else if (sheetModel.getLevel_of_consclousness().equals("1")) {
            level_of_consclousness_P.setChecked(true);
        } else if (sheetModel.getLevel_of_consclousness().equals("2")) {
            level_of_consclousness_V.setChecked(true);
        } else {
            level_of_consclousness_U.setChecked(true);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        pulse_rate = findViewById(R.id.pulse_rate);
        pulse_rate.setEnabled(false);
        pulse_rate.setText(sheetModel.getPulse_rate());

        pulse_rhythm = findViewById(R.id.pulse_rhythm);
        pulse_rhythm.setEnabled(false);
        pulse_rhythm.setText(sheetModel.getPulse_rhythm());

        pulse_equality = findViewById(R.id.pulse_equality);
        pulse_equality.setEnabled(false);
        pulse_equality.setText(sheetModel.getPulse_equality());

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        peripheral_pulsation_felt = findViewById(R.id.peripheral_pulsation_felt);
        peripheral_pulsation_felt.setEnabled(false);
        peripheral_pulsation_notfelt = findViewById(R.id.peripheral_pulsation_notfelt);
        peripheral_pulsation_notfelt.setEnabled(false);

        if (sheetModel.getPeripheral_pulsation().equals("0")){
            peripheral_pulsation_notfelt.setChecked(true);
        }else {
            peripheral_pulsation_felt.setChecked(true);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        vital_signs_bp_right = findViewById(R.id.vital_signs_bp_right);
        vital_signs_bp_right.setEnabled(false);
        vital_signs_bp_right.setText(sheetModel.getVital_signs_bp_right());

        vital_signs_bp_left = findViewById(R.id.vital_signs_bp_left);
        vital_signs_bp_left.setEnabled(false);
        vital_signs_bp_left.setText(sheetModel.getVital_signs_bp_left());

        vital_signs_temp = findViewById(R.id.vital_signs_temp);
        vital_signs_temp.setEnabled(false);
        vital_signs_temp.setText(sheetModel.getVital_signs_temp());

        vital_signs_rr = findViewById(R.id.vital_sign_rr);
        vital_signs_rr.setEnabled(false);
        vital_signs_rr.setText(sheetModel.getVital_signs_rr());

        vital_signs_o2_saturation = findViewById(R.id.vital_sign_o2_saturation);
        vital_signs_o2_saturation.setEnabled(false);
        vital_signs_o2_saturation.setText(sheetModel.getVital_signs_o2_saturation());

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        chest_pain_yes = findViewById(R.id.chest_pain_yes);
        chest_pain_yes.setEnabled(false);
        chest_pain_no = findViewById(R.id.chest_pain_no);
        chest_pain_no.setEnabled(false);

        if(sheetModel.getChest_pain().equals("0")){
            chest_pain_no.setChecked(true);
        }else{
            chest_pain_yes.setChecked(true);

        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        head_and_neck_neck_veins = findViewById(R.id.head_and_neck_neck_veins);
        head_and_neck_neck_veins.setEnabled(false);
        head_and_neck_other = findViewById(R.id.head_and_neck_other);
        head_and_neck_other.setEnabled(false);

        if(sheetModel.getHead_and_neck_neck().equals(0)){
            head_and_neck_neck_veins.setChecked(true);
        }else{
            head_and_neck_other.setChecked(true);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ll_oedema_yes = findViewById(R.id.ll_oedema_yes);
        ll_oedema_yes.setEnabled(false);
        ll_oedema_no = findViewById(R.id.ll_oedema_no);
        ll_oedema_no.setEnabled(false);

        if(sheetModel.getLl_oedema_1().equals("0")){
            ll_oedema_no.setChecked(true);
        }else{
            ll_oedema_yes.setChecked(true);
        }

        ll_oedema_right = findViewById(R.id.ll_oedema_right);
        ll_oedema_right.setEnabled(false);
        ll_oedema_left = findViewById(R.id.ll_oedema_left);
        ll_oedema_left.setEnabled(false);
        ll_oedema_bilateral = findViewById(R.id.ll_oedema_bilateral);
        ll_oedema_bilateral.setEnabled(false);

        if(sheetModel.getLl_oedema_2().equals("0")){
            ll_oedema_right.setChecked(true);
        }else if(sheetModel.getLl_oedema_2().equals("1")){
            ll_oedema_left.setChecked(true);
        }else{
            ll_oedema_bilateral.setChecked(true);
        }

        ll_oedema_pitting = findViewById(R.id.ll_oedema_pitting);
        ll_oedema_pitting.setEnabled(false);
        ll_oedema_nonpitting = findViewById(R.id.ll_oedema_nonpitting);
        ll_oedema_nonpitting.setEnabled(false);

        if(sheetModel.getLl_oedema_3().equals("0")){
            ll_oedema_nonpitting.setChecked(true);
        }else{
            ll_oedema_pitting.setChecked(true);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AssessmentSheet2AfterSecion.this,AssessmentSheetAfterSecion.class));
            }
        });



    }
}
