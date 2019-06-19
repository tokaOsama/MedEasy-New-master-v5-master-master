package com.example.nihal.medeasy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nihal.medeasy.Adapters.SynderModelAdapter;
import com.example.nihal.medeasy.Models.SynderModel;
import com.example.nihal.medeasy.widget.CustomDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SyndrActivity extends AppCompatActivity implements CustomDialog.CustomDialogListener {

    FirebaseAuth auth;
    FirebaseDatabase database ;
    DatabaseReference myRef ;
    RecyclerView recyclerView;

    List<SynderModel> synderModelList = new ArrayList<>();
    SynderModelAdapter adapter = new SynderModelAdapter(synderModelList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syndr);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance() ;
        myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("SyndrList") ;

        Query dataOrderedByKey = myRef.orderByKey();

        recyclerView = findViewById(R.id.syndromeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();  // method of the dialog
            }
        });


        {
            dataOrderedByKey.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Log.d("ttttt", "onDataChange: "+dataSnapshot.toString());

                    synderModelList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        SynderModel synderModel = snapshot.getValue(SynderModel.class) ;
                        synderModelList.add(synderModel) ;
                        recyclerView.setAdapter(adapter);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(SyndrActivity.this, "  Fail", Toast.LENGTH_SHORT).show();

                }
            });

        }
        //----------------------------------------------------------------------------------------------
        /** RecyclerViewSwipeItem **/

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                SynderModel search = synderModelList.get(viewHolder.getAdapterPosition());
                Query applesQuery = myRef.orderByChild("syndrome").equalTo(search.getSyndrome());


                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot searchSnapshot: dataSnapshot.getChildren()) {
                            searchSnapshot.getRef().removeValue();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

        }).attachToRecyclerView(recyclerView);

    }

    //----------------------------------------------------------------------------------------------
    //dy me el CustomDialog
    @Override
    public void applyText(String syndrome, String drug) {

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(" EEE, d MMM yyyy , HH:mm a");
        String output = dateFormat.format(currentTime);

        SynderModel model = new SynderModel(syndrome, drug, output);

        synderModelList.add(model);
//        recyclerView.setAdapter(adapter) ;
        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("SyndrList").push().setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SyndrActivity.this, "  Successfull  ", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(SyndrActivity.this, "  Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

        recyclerView.setAdapter(adapter);
    }

    // method of the dialog

    private void openDialog() {
        CustomDialog customDialog = new CustomDialog();
        customDialog.show(getSupportFragmentManager(), "CustomDialogTest");


    }

}