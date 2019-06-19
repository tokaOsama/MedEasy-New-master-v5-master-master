package com.example.nihal.medeasy.Adapters;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nihal.medeasy.Models.Drugs;
import com.example.nihal.medeasy.R;

import java.util.ArrayList;

public class DrugsAdapter extends RecyclerView.Adapter<DrugsAdapter.DrugsHolder> {


    ArrayList<Drugs> DrugslList;

    // Contstrucor
    public DrugsAdapter(ArrayList<Drugs> models) {

        this.DrugslList = models;
    }


    @NonNull
    @Override
    public DrugsHolder onCreateViewHolder( ViewGroup viewGroup, int i)
    {
       // return null;
       // AccessController parent;
        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.add_drugs, viewGroup, false);
        //DrugsHolder holder = new DrugsHolder(row);
        return new DrugsAdapter.DrugsHolder(row);

    }

    @Override
    public void onBindViewHolder( DrugsHolder viewHolder, int i) {


        Drugs model = DrugslList.get(i);
        viewHolder.drugdose.setText(model.getDose());
        viewHolder.drugduration.setText(model.getFrom()+" to "+model.getTo());
        viewHolder.drugname.setText(model.getName()+" "+model.getTperd());
        viewHolder.drugperd.setText(model.getTperd());

    }

    @Override
    public int getItemCount() {
        return DrugslList.size();
    }


    public class DrugsHolder extends RecyclerView.ViewHolder{

        TextView drugname,drugdose,drugperd,drugduration ;



        public DrugsHolder(@NonNull View itemView) {
            super(itemView);


            drugname = (TextView) itemView.findViewById(R.id.drugname);
            drugduration =  (TextView)itemView.findViewById(R.id.drugduration);
            drugdose = (TextView) itemView.findViewById(R.id.drugdose);
            drugperd = (TextView)  itemView.findViewById(R.id.drugperd);


        }
    }
}
