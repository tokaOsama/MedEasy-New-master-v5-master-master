package com.example.nihal.medeasy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nihal.medeasy.Adapters.CategoriesAdapter;
import com.example.nihal.medeasy.Adapters.DoctorAdapter;
import com.example.nihal.medeasy.Models.DoctorHomeModel;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import cn.iwgang.countdownview.CountdownView;

public class DoctorHome extends AppCompatActivity {

    DoctorAdapter doctorAdapter;
    ArrayList<DoctorHomeModel> docModel;
    RecyclerView recyclerView;
    TextView logout;
    CountdownView mCvCountdownView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        mCvCountdownView = (CountdownView) findViewById(R.id.cv_countdownViewTest1);
        mCvCountdownView.start(1800000); // Millisecond
        mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                //Toast.makeText(DoctorLogin.this, "teeeeeesssssssttttt", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DoctorHome.this,DoctorLogin.class));
                finish();
            }
        });
        docModel = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorHome.this,DoctorLogin.class));
                finish();

            }
        });

        docModel.add(new DoctorHomeModel("Medical History", 0));
        docModel.add(new DoctorHomeModel("Medicine", 1));
        docModel.add(new DoctorHomeModel("Analysis", 2));
        docModel.add(new DoctorHomeModel("Profile", 3));
        docModel.add(new DoctorHomeModel("Blood Type", 4));

        doctorAdapter = new DoctorAdapter(docModel, this, new DoctorAdapter.OnItemClick() {
            @Override
            public void setOnItemClick(int position) {

                if (position == 0) {

                    startActivity(new Intent(DoctorHome.this, AssessmentSheet.class));
                } else if (position == 1) {
                    startActivity(new Intent(DoctorHome.this, DocSectionMedince.class));

                } else if (position == 2) {
                    startActivity(new Intent(DoctorHome.this, LabResultsAvtivity.class));

                } else if (position == 3) {
                    startActivity(new Intent(DoctorHome.this, DocSectionMedince.class));
                }
                  else if (position == 4) {
                    startActivity(new Intent(DoctorHome.this, DocSectionMedince.class));
                }
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(doctorAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Hawk.contains("Key")) {
            Hawk.delete("Key");
        }
    }

    @Override
    public void onBackPressed() {
    }
}