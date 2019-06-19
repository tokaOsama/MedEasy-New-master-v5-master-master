package com.example.nihal.medeasy.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nihal.medeasy.Models.SynderModel;
import com.example.nihal.medeasy.R;

import java.util.List;

public class SynderModelAdapter extends RecyclerView.Adapter<SynderModelAdapter.SynderHolder> {


    List<SynderModel> synderModelList;

    // el Contstrucor
    public SynderModelAdapter(List<SynderModel> models) {

        this.synderModelList = models;
    }

    @NonNull
    @Override
    public SynderHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.synder_model, viewGroup, false);
        SynderHolder holder = new SynderHolder(row);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SynderHolder synderHolder, int i) {

        SynderModel model = synderModelList.get(i);
        synderHolder.syndrome.setText(model.getSyndrome());
        synderHolder.drug.setText(model.getDrug());
        synderHolder.date.setText(model.getDate());
    }

    @Override
    public int getItemCount() {
        return synderModelList.size();
    }


    /*********************                         ********************/

    /*********************                         ********************/


    public class SynderHolder extends RecyclerView.ViewHolder {
        TextView syndrome, drug, date;

        public SynderHolder(@NonNull View itemView) {

            super(itemView);
            syndrome = itemView.findViewById(R.id.syndrome);
            drug = itemView.findViewById(R.id.drug);
            date = itemView.findViewById(R.id.date);


        }


    }

}
