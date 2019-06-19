package com.example.nihal.medeasy.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nihal.medeasy.Models.Drugs;
import com.example.nihal.medeasy.R;

import java.util.ArrayList;

public class AddMedcineAdapter extends RecyclerView.Adapter<AddMedcineAdapter.MedHolder> {

    ArrayList<Drugs> drugs;
    AddMedcineAdapter.OnItemClick onItemClick;


    public AddMedcineAdapter(ArrayList<Drugs> drugs,AddMedcineAdapter.OnItemClick onItemClick) {
        this.drugs = drugs;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public MedHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.medicine_input_item, viewGroup, false);
        //DrugsHolder holder = new DrugsHolder(row);
        return new AddMedcineAdapter.MedHolder(row);

    }

    @Override
    public void onBindViewHolder(@NonNull MedHolder medHolder, int i) {
        medHolder.name.setText(drugs.get(i).name+" "+drugs.get(i).getTperd());
        medHolder.dose.setText(drugs.get(i).dose);
//        Log.d("TTT", "onClick: " + drugs.get(i).getFrom());
//        Log.d("TTT", "onClick: " + drugs.get(i).getTo());

        medHolder.date.setText(drugs.get(i).getFrom() + " to " + drugs.get(i).getTo());
    }

    @Override
    public int getItemCount() {
        return drugs.size();
    }

    public class MedHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, dose, date;

        public MedHolder(@NonNull View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.name);
            dose = itemView.findViewById(R.id.dose);
            date = itemView.findViewById(R.id.drug);
            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            onItemClick.setOnItemClick(getAdapterPosition());
        }

    }
    public interface OnItemClick {
        void setOnItemClick(int position);
    }
}
