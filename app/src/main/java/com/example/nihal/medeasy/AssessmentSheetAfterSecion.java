package com.example.nihal.medeasy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nihal.medeasy.Models.AssessmentSheetModel;
import com.example.nihal.medeasy.Models.Drugs;

public class AssessmentSheetAfterSecion extends AppCompatActivity {


    private EditText yourComplaint;
    private LinearLayout pastHistory, go_profile;
    private LinearLayout hideLinearLayoutPastHistory ,GoToProfilePatient;
    private EditText pasthistory_editText;
    CheckBox DM_yes, DM_no, Smoking_yes, Smoking_no, hypertension_yes, hypertension_no, dyslipidemia_yes, dyslipidemia_no;
    CheckBox generalLook_normal, generalLook_pale, generalLook_cyanosed, generalLook_sweaty;
    String DM, Smoking, hypertension, dyslipidemia, generalLook;
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment__sheet__after_secion);

        //
        AssessmentSheetModel sheetModel=new AssessmentSheetModel("hxhjdxbdhnd"
        ,"djdjnd","","0","0","0","0"
                ,"4","3","dhdj","dhdbx","dndj"
                ,"1","","djdnd","djdj"
                ,"jdndn","dndm"
                ,"jdjfnd","1","0","1","2","1");
        //


        yourComplaint = findViewById(R.id.yourComplaint);
        yourComplaint.setEnabled(false);
        yourComplaint.setText(sheetModel.getYourComplaint());

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        DM_yes = findViewById(R.id.DM_yes);
        DM_yes.setEnabled(false);
        DM_no = findViewById(R.id.DM_no);
        DM_no.setEnabled(false);

        if (sheetModel.getDM().equals("0")){
            DM_no.setChecked(true);
        }else {
            DM_yes.setChecked(true);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Smoking_yes = findViewById(R.id.Smoking_yes);
        Smoking_yes.setEnabled(false);
        Smoking_no = findViewById(R.id.Smoking_no);
        Smoking_no.setEnabled(false);

        if (sheetModel.getSmoker().equals("0")){
            Smoking_no.setChecked(true);
        }else {
            Smoking_yes.setChecked(true);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        hypertension_yes = findViewById(R.id.hypertension_yes);
        hypertension_yes.setEnabled(false);
        hypertension_no = findViewById(R.id.hypertension_no);
        hypertension_no.setEnabled(false);

        if (sheetModel.getHypertension().equals("0")){
            hypertension_no.setChecked(true);
        }else {
            hypertension_yes.setChecked(true);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        dyslipidemia_yes = findViewById(R.id.dyslipidemia_yes);
        dyslipidemia_yes.setEnabled(false);
        dyslipidemia_no = findViewById(R.id.dyslipidemia_no);
        dyslipidemia_no.setEnabled(false);

        if (sheetModel.getDysliplidemia().equals("0")){
            dyslipidemia_yes.setChecked(true);
        }else {
            dyslipidemia_no.setChecked(true);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        generalLook_normal = findViewById(R.id.generalLook_normal);
        generalLook_normal.setEnabled(false);

        generalLook_pale = findViewById(R.id.generalLook_pale);
        generalLook_pale.setEnabled(false);

        generalLook_cyanosed = findViewById(R.id.generalLook_cyanosed);
        generalLook_cyanosed.setEnabled(false);

        generalLook_sweaty = findViewById(R.id.generalLook_sweaty);
        generalLook_sweaty.setEnabled(false);

        if (sheetModel.getGeneralLook().equals("1")){
            generalLook_normal.setChecked(true);
        }else if(sheetModel.getGeneralLook().equals("2")){
            generalLook_pale.setChecked(true);
        }else if(sheetModel.getGeneralLook().equals("3")){
            generalLook_cyanosed.setChecked(true);
        }else{
            generalLook_sweaty.setChecked(true);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //visible the editText in pastHistory Section
        pastHistory = findViewById(R.id.pastHistory_Section);
        pastHistory.setEnabled(false);

        hideLinearLayoutPastHistory = findViewById(R.id.hideLinearLayoutPastHistory);

        pasthistory_editText = findViewById(R.id.pasthistory_editText);
        pasthistory_editText.setEnabled(false);

        if (sheetModel.getPastHistory().isEmpty()){
            pasthistory_editText.setVisibility(View.INVISIBLE);
        }else {
            pasthistory_editText.setVisibility(View.VISIBLE);
            pasthistory_editText.setText(sheetModel.getPastHistory());
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        GoToProfilePatient = findViewById(R.id.GoToProfilePatient);
        GoToProfilePatient.setVisibility(View.GONE);

        go_profile = findViewById(R.id.go_profile);
        go_profile.setEnabled(false);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(AssessmentSheetAfterSecion.this,AssessmentSheet2AfterSecion.class));
             }
        });


    }
 }

