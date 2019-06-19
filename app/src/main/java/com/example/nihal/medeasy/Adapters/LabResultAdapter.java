package com.example.nihal.medeasy.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nihal.medeasy.Models.LabResultModle;
import com.example.nihal.medeasy.R;

import java.util.List;

public class LabResultAdapter extends RecyclerView.Adapter<LabResultAdapter.LabResultHolder> {

    List<LabResultModle> labResultModleList;

    public LabResultAdapter(List<LabResultModle> labResultModleList) {
        this.labResultModleList = labResultModleList;
    }

    @NonNull
    @Override
    public LabResultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lab_modle, viewGroup, false);
        LabResultHolder holder = new LabResultHolder(row);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LabResultHolder labResultHolder, int i) {

        LabResultModle modle = labResultModleList.get(i);
        labResultHolder.textView.setText(modle.getName());
        labResultHolder.editText.setText(modle.getVar());


    }

    @Override
    public int getItemCount() {

        return labResultModleList.size();
    }

    class LabResultHolder extends RecyclerView.ViewHolder {

        TextView textView;
        EditText editText;
        CheckBox pos, nav;

        public LabResultHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.labName);
            editText = itemView.findViewById(R.id.labVar);
            pos = itemView.findViewById(R.id.labPos);
            nav = itemView.findViewById(R.id.labNav);

        }


    }
}
