package com.example.nihal.medeasy.Dialoge;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.nihal.medeasy.BroadCast.AlarmService;
import com.example.nihal.medeasy.R;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class AlarmDialoge extends Dialog {
    Context context;
    Dialog d;
    TextView time,sat,sun,mon,tue,wed,thu,fri;
    String sats="1",suns="1",mons="1",tues="1",weds="1",thus="1",fris="1";
    Button save,cance;
    public AlarmDialoge(@NonNull Context context) {
        super(context);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        d = this;
        setContentView(R.layout.alarm_dialoge);
        time=findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timpPickerDialoge();
            }
        });

        sat=findViewById(R.id.sat);
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sats.equals("1")){
                    sat.setTextColor(Color.BLACK);
                    sat.setBackgroundColor(Color.WHITE);
                    sats="0";
                }else{
                    sat.setTextColor(Color.WHITE);
                    sat.setBackgroundResource(R.drawable.circle_bg);
                    sats="1";
                }

            }
        });
        sun=findViewById(R.id.sun);
        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(suns.equals("1")){
                    sun.setTextColor(Color.BLACK);
                    sun.setBackgroundColor(Color.WHITE);
                    suns="0";
                }else{
                    sun.setTextColor(Color.WHITE);
                    sun.setBackgroundResource(R.drawable.circle_bg);
                    suns="1";
                }

            }
        });
        mon=findViewById(R.id.mon);
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mons.equals("1")){
                    mon.setTextColor(Color.BLACK);
                    mon.setBackgroundColor(Color.WHITE);
                    mons="0";
                }else{
                    mon.setTextColor(Color.WHITE);
                    mon.setBackgroundResource(R.drawable.circle_bg);
                    mons="1";
                }

            }
        });
        tue=findViewById(R.id.tue);
        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tues.equals("1")){
                    tue.setTextColor(Color.BLACK);
                    tue.setBackgroundColor(Color.WHITE);
                    tues="0";
                }else{
                    tue.setTextColor(Color.WHITE);
                    tue.setBackgroundResource(R.drawable.circle_bg);
                    tues="1";
                }

            }
        });
        wed=findViewById(R.id.wed);
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weds.equals("1")){
                    wed.setTextColor(Color.BLACK);
                    wed.setBackgroundColor(Color.WHITE);
                    weds="0";
                }else{
                    wed.setTextColor(Color.WHITE);
                    wed.setBackgroundResource(R.drawable.circle_bg);
                    weds="1";
                }

            }
        });
        thu=findViewById(R.id.thu);
        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thus.equals("1")){
                    thu.setTextColor(Color.BLACK);
                    thu.setBackgroundColor(Color.WHITE);
                    thus="0";
                }else{
                    thu.setTextColor(Color.WHITE);
                    thu.setBackgroundResource(R.drawable.circle_bg);
                    thus="1";
                }

            }
        });
        fri=findViewById(R.id.fri);
        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fris.equals("1")){
                    fri.setTextColor(Color.BLACK);
                    fri.setBackgroundColor(Color.WHITE);
                    fris="0";
                }else{
                    fri.setTextColor(Color.WHITE);
                    fri.setBackgroundResource(R.drawable.circle_bg);
                    fris="1";
                }


            }
        });
        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AlarmService.class);
                PendingIntent p1=PendingIntent.getBroadcast(context,0, intent,0);
                AlarmManager a=(AlarmManager)context.getSystemService(ALARM_SERVICE);
                a.set(AlarmManager.RTC,System.currentTimeMillis() + 10000,p1);
            }
        });
        cance=findViewById(R.id.cance);
        cance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        //timpPickerDialoge();

    }
    private void timpPickerDialoge(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                time.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
}
