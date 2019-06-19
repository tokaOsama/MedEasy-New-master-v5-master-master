package com.example.nihal.medeasy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.orhanobut.hawk.Hawk;

public class startingPage extends AppCompatActivity {

    LinearLayout doctor,patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_page);
        Hawk.init(this).build();
        patient=findViewById(R.id.patient);
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(startingPage.this,LoginActivity.class));
                Hawk.put("type","1");
            }
        });
        doctor=findViewById(R.id.doctor);
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(startingPage.this,LoginActivity.class));
                Hawk.put("type","2");

            }
        });
    }
}
