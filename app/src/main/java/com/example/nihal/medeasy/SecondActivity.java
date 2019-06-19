package com.example.nihal.medeasy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.nihal.medeasy.Adapters.DrugsAdapter;
import com.example.nihal.medeasy.Models.Drugs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {
    String x;
    private DatabaseReference mDatabase;
    ArrayList<Drugs> drugs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Drugs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


         drugs = new ArrayList<>();
        String drugname[] = {"Actemra", "Losartan", "Meloxicam", "Zinacef", "Doxycycline"};
        String drugdose[] = {"50g", "90g", "50g", "75g", "60g"};
        String drugperday[] = {"2 in day", "2 in day", "1 in day", "2 in day", "3 in day"};
        String drugduration[] = {"6 monthes", "3 monthes", "1 month", "8 monthes", "6 monthes"};


        for (int i = 0; i < drugname.length; i++) {

            Drugs drugs1 = new Drugs(drugname[i], drugdose[i], drugperday[i], drugduration[i], drugduration[i]);

            drugs.add(drugs1);

        }
        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DrugsAdapter adapter = new DrugsAdapter(drugs);
        recyclerview.setAdapter(adapter);

        retrieveData();
    }


    public void retrieveData() {
        mDatabase.child("Users").child("1UbTozyso3SR8ZY7y0O6mZxTVqd2").child("Roshetat")
                .addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (final DataSnapshot dataSnap : dataSnapshot.getChildren()) {

                     x=dataSnap.getKey();
                    Log.d("TTTTTTTTT", "onDataChange: "+dataSnap.getChildren());
                    mDatabase.child("Users").child("1UbTozyso3SR8ZY7y0O6mZxTVqd2")
                            .child("Roshetat").child(dataSnap.getKey()).child("Rosheta").child("Medicine")
                            .addValueEventListener(new ValueEventListener() {

                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot11) {
                                    Log.d("rosheta2", "onDataChange: " + dataSnapshot11);

                                    // l 7d hena kda ana m3aya el keys elly elly 2abl el med 3la tool

                                    for (final DataSnapshot dataSnap11 : dataSnapshot11.getChildren()) {
                                        Log.d("TTT", "onDataChange: "+dataSnap11.getChildren());

                                        mDatabase.child("Users").child("1UbTozyso3SR8ZY7y0O6mZxTVqd2")
                                                .child("Roshetat").child(x).child("Rosheta").child("Medicine")
                                                .child(dataSnap11.getKey()).addValueEventListener(new ValueEventListener() {

                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot33) {
                                                Log.d("Medi", "onDataChange: " + dataSnapshot33.getValue());

                                                for (final DataSnapshot dataSnaps : dataSnapshot33.getChildren()) {
                                                    Drugs drugs= (Drugs) dataSnaps.getValue();
                                                    Log.d("Medi", "onDataChange: " + drugs.getTo());
                                                }



                                                }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
