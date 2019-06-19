package com.example.nihal.medeasy.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nihal.medeasy.Adapters.PostAdapter;
import com.example.nihal.medeasy.Models.PostModel;
import com.example.nihal.medeasy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    RecyclerView recyclerView  ;
    DatabaseReference myRef ;
    List<PostModel> postModelList = new ArrayList<>();
    PostAdapter adapter = new PostAdapter(postModelList) ;


    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_post, container, false);

        myRef=FirebaseDatabase.getInstance().getReference("Posts") ;

        recyclerView = view.findViewById(R.id.postRecyclerView) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        getDataFromFireBase();


        return view;
    }


    public void getDataFromFireBase (){

        Query query = myRef.orderByKey()  ;

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postModelList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    PostModel postModel = snapshot.getValue(PostModel.class) ;
                    postModelList.add(postModel) ;
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}