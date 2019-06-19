package com.example.nihal.medeasy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.nihal.medeasy.Adapters.LabResultAdapter;
import com.example.nihal.medeasy.Models.LabResultModle;

import java.util.ArrayList;
import java.util.List;

public class LabResultsAvtivity extends AppCompatActivity {

    Spinner spinner;
    List<String> listOfLabs;
    RecyclerView recyclerView;

    List<LabResultModle> labResultModles = new ArrayList<>();

    LabResultAdapter adapter = new LabResultAdapter(labResultModles);

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_results_avtivity);

        recyclerView = findViewById(R.id.labRec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        spinner = findViewById(R.id.labSpinner);

        prepareList();

        ArrayAdapter<String> spinAdaptor = new ArrayAdapter<>(LabResultsAvtivity.this, android.R.layout.simple_spinner_item, listOfLabs);

        spinner.setAdapter(spinAdaptor);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0){
                    labResultModles.clear();
                    recyclerView.setAdapter(adapter);
                    // adapter.notifyDataSetChanged();
                }
                if (position==1){
                    labResultModles.clear();
                    labResultModles.add(new LabResultModle("WBCS","","",""));
                    labResultModles.add(new LabResultModle("HB(gm%)","","",""));
                    labResultModles.add(new LabResultModle("HCT(%)","","",""));
                    labResultModles.add(new LabResultModle("Platelets","","",""));
                    recyclerView.setAdapter(adapter);
                    // adapter.notifyDataSetChanged();
                }
                if (position==2){
                    labResultModles.clear();
                    labResultModles.add(new LabResultModle("PT","","",""));
                    labResultModles.add(new LabResultModle("PTT","","",""));
                    labResultModles.add(new LabResultModle("INR","","",""));
                    labResultModles.add(new LabResultModle("DDIMER","","",""));
                    recyclerView.setAdapter(adapter);
                }
                if (position==3){
                    labResultModles.clear();
                    labResultModles.add(new LabResultModle("Total protein","","",""));
                    labResultModles.add(new LabResultModle("Albumin","","",""));
                    labResultModles.add(new LabResultModle("T.Bilirubin","","",""));
                    labResultModles.add(new LabResultModle("D.Bilirubin","","",""));
                    labResultModles.add(new LabResultModle("SGOT","","",""));
                    labResultModles.add(new LabResultModle("SGPT","","",""));
                    recyclerView.setAdapter(adapter);
                }
                if (position==4){
                    labResultModles.clear();
                    labResultModles.add(new LabResultModle("UREA","","",""));
                    labResultModles.add(new LabResultModle("Creatnine","","",""));
                    labResultModles.add(new LabResultModle("Uric acid","","",""));
                    recyclerView.setAdapter(adapter);
                }
                if (position==5){
                    labResultModles.clear();
                    labResultModles.add(new LabResultModle("Na+","","",""));
                    labResultModles.add(new LabResultModle("K+","","",""));
                    labResultModles.add(new LabResultModle("Ca++","","",""));
                    labResultModles.add(new LabResultModle("Mg+","","",""));
                    recyclerView.setAdapter(adapter);
                }
                if (position==6){
                    labResultModles.clear();
                    labResultModles.add(new LabResultModle("CPK","","",""));
                    labResultModles.add(new LabResultModle("CPK-MB","","",""));
                    labResultModles.add(new LabResultModle("LDH","","",""));
                    labResultModles.add(new LabResultModle("Troponine","","",""));
                    recyclerView.setAdapter(adapter);
                }
                if (position==7){
                    labResultModles.clear();
                    labResultModles.add(new LabResultModle("Cholesterol","","",""));
                    labResultModles.add(new LabResultModle("Triglycerides","","",""));
                    labResultModles.add(new LabResultModle("LDL","","",""));
                    labResultModles.add(new LabResultModle("HDL","","",""));
                    recyclerView.setAdapter(adapter);
                }
                if (position==8){
                    labResultModles.clear();
                    labResultModles.add(new LabResultModle("PH","","",""));
                    labResultModles.add(new LabResultModle("PCO2","","",""));
                    labResultModles.add(new LabResultModle("O2sat","","",""));
                    labResultModles.add(new LabResultModle("HCO3","","",""));
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

/*
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                */
/**Store a Value of Selected Item**//*

                String value = parent.getItemAtPosition(position).toString();
                */
/**Show Snackbar for Selected Item**//*

                Snackbar.make(view, value, Snackbar.LENGTH_SHORT).show();
            }

        });
*/

    }

    private void prepareList() {
        listOfLabs = new ArrayList<>();
        listOfLabs.add("25tar lab");
        listOfLabs.add("CBC");
        listOfLabs.add("conagulation");
        listOfLabs.add("Liver");
        listOfLabs.add("Kidney");
        listOfLabs.add("Electrolytes");
        listOfLabs.add("Cardiac");
        listOfLabs.add("Lipid");
        listOfLabs.add("ABG");

    }
}
