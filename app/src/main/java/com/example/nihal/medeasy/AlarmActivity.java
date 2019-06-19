package com.example.nihal.medeasy;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.nihal.medeasy.Adapters.AlarmAdapter;
import com.example.nihal.medeasy.Dialoge.AlarmDialoge;
import com.example.nihal.medeasy.Models.AlarmModel;

import java.util.ArrayList;

public class AlarmActivity extends AppCompatActivity {

    RecyclerView RV;
    AlarmAdapter alarmAdapter;
    ArrayList<AlarmModel> alarmModels;
    ImageView add,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        add=findViewById(R.id.add);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmDialoge cdd = new AlarmDialoge(AlarmActivity.this);
                cdd.setCancelable(false);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(cdd.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                cdd.getWindow().setAttributes(lp);

                cdd.show();
            }
        });
        RV=findViewById(R.id.RV);
        alarmModels=new ArrayList<>();
        alarmModels.add(new AlarmModel());
        alarmModels.add(new AlarmModel());
        alarmModels.add(new AlarmModel());
        alarmAdapter=new AlarmAdapter(alarmModels, this, new AlarmAdapter.OnItemClick() {
            @Override
            public void setOnItemClick(int position) {

            }
        });
        RV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        RV.setAdapter(alarmAdapter);
        alarmAdapter.notifyDataSetChanged();

    }
}
