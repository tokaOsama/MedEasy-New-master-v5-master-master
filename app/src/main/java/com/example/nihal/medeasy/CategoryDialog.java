package com.example.nihal.medeasy;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.nihal.medeasy.Adapters.CategoriesAdapter;
import com.example.nihal.medeasy.Models.CategoriesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class CategoryDialog extends Dialog {
    public Context a;
    public Dialog dialog;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseDatabase database111;
    DatabaseReference myRef111;
    FirebaseDatabase database1;
    DatabaseReference myRef1;
    ArrayList<CategoriesModel> categories;
    CategoriesAdapter categoriesAdapter;
    ArrayList<String> ids;
    Button save,cancel;

    public CategoryDialog(Context a) {
        super(a);
        this.a = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog = this;
        setContentView(R.layout.add_categories);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
        todelete();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Categories");

        // start add category
        categories = new ArrayList<>();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Hawk.contains("ids")){
                    Toast.makeText(a, "Please choose a category", Toast.LENGTH_SHORT).show();
                }
                else {
                    ids = Hawk.get("ids");
                    Log.d("TTT", "onClick: " + ids.size());
                    database1 = FirebaseDatabase.getInstance();
                    myRef1 = database1.getReference("Users");

                    for (int i = 0; i < ids.size(); i++)
                        myRef1.child(Hawk.get("userID") + "").child("checkedCatgry").child(i + "").setValue(ids.get(i));
                    Hawk.delete("ids");
                    dismiss();
                }
            }
        });

      /*  String category[] = {"Sport","Healthy Food","Music","Yoga","Diet"};
        String category1[] = {"0","1","0","0","1"};
*/
       /* for(int i = 0; i < category.length; i++){
            CategoriesModel categoriesModel = new CategoriesModel(category[i],category1[i]);
            categories.add(categoriesModel);
        }*/
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(a, LinearLayoutManager.VERTICAL, false));
        categoriesAdapter = new CategoriesAdapter(categories, a);
        recyclerView.setAdapter(categoriesAdapter);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("TTTT", "onDataChange: " + dataSnapshot.toString());


                for (DataSnapshot dataSnap : dataSnapshot.getChildren())
                    categories.add(dataSnap.getValue(CategoriesModel.class));
                categoriesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void todelete(){
        database111 = FirebaseDatabase.getInstance();
        myRef111 = database111.getReference("Users");
        myRef111.child("1UbTozyso3SR8ZY7y0O6mZxTVqd2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("TTTT", "onDataChange: " );
                Log.d("TTTT", "onDataChange: " + dataSnapshot.toString());


                for (DataSnapshot dataSnap : dataSnapshot.getChildren()){
                    Log.d("TTTT", "onDataChange: "+dataSnap.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("TTT", "onCancelled: ");
            }
        });


    }
}
