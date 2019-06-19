package com.example.nihal.medeasy;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.nihal.medeasy.Adapters.AddMedcineAdapter;
import com.example.nihal.medeasy.Models.AssessmentSheetModel;
import com.example.nihal.medeasy.Models.Drugs;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DocSectionMedince extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
{
    RecyclerView RV;
    AddMedcineAdapter adapter;
    ArrayList<Drugs> drugs;
    int flag;
    EditText add_Medicine, bar_day, dose, start_time, end_time;
    Button save, commit;
    FirebaseDatabase database1;
    DatabaseReference myRef1;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_section_medince);
        RV = findViewById(R.id.RV);
        drugs = new ArrayList<>();
        datePickerDialog=new DatePickerDialog(this,DocSectionMedince.this,2019,4,15);
        adapter = new AddMedcineAdapter(drugs, new AddMedcineAdapter.OnItemClick() {
            @Override
            public void setOnItemClick(final int position) {

                new AlertDialog.Builder(DocSectionMedince.this)
                        .setTitle("")
                        .setMessage("are you sure you want to delete this item ?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                drugs.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }

        });

        RV.setLayoutManager(new LinearLayoutManager(this));
        RV.setAdapter(adapter);
        Hawk.init(this).build();
        add_Medicine = findViewById(R.id.add_Medicine);
        bar_day = findViewById(R.id.bar_day);
        dose = findViewById(R.id.dose);
        start_time = findViewById(R.id.start_time);
        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
                flag=0;
            }
        });
        end_time = findViewById(R.id.end_time);
        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
                flag=1;
            }
        });
        save = findViewById(R.id.save);
        commit = findViewById(R.id.commit);





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String addMedicine = add_Medicine.getText().toString();
                final String barDay      = bar_day.getText().toString() ;
                final String Dose        = dose.getText().toString();
                final String startTime   = start_time.getText().toString() + "";
                final String endTime     = end_time.getText().toString() + "" ;

               if (addMedicine.isEmpty()) {

                    add_Medicine.setError(" Enter Medicine Name ");

                } else if (barDay.isEmpty()) {

                    bar_day.setError("Enter hoe many times");

                } else if (Dose.isEmpty()) {

                    dose.setError("Enter dose of Medicine");

                } else if (startTime.isEmpty()) {

                    start_time.setError("Enter Starting Time of Medicine");

                } else if (endTime.isEmpty()) {

                    end_time.setError("Enter Ending Time of Medicine");

                }else {

                   Drugs drug = new Drugs(addMedicine
                           , barDay
                           , Dose
                           , startTime
                           , endTime);

                   drugs.add(drug);
                   adapter.notifyDataSetChanged();
               }
            }
        });

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < drugs.size(); i++) {
                    Drugs drug = drugs.get(i);
                    writeOnDataBase(drug);
                }

            }
        });


    }

    public void writeOnDataBase(Drugs model) {
        database1 = FirebaseDatabase.getInstance();
        String key;
        myRef1 = database1.getReference("Users").child("1UbTozyso3SR8ZY7y0O6mZxTVqd2").child("Roshetat");
        if (Hawk.contains("Key")) {


            key = Hawk.get("Key");


        } else {
            key = myRef1.push().getKey();
            Hawk.put("Key", key);
        }

        myRef1.child(key).child("Rosheta").child("Medicine").push().setValue(model);
        //Log.d("TTTT", "writeOnDataBase: " + key);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if(flag==0) {
            start_time.setText(dayOfMonth + "/" + month +1 + "/" + year);
        }else {
            end_time.setText(dayOfMonth + "/" + month +1 + "/" + year);
        }

    }
}
