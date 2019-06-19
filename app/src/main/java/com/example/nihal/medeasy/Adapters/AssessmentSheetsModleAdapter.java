package com.example.nihal.medeasy.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nihal.medeasy.Models.AssessmentSheetsModle;
import com.example.nihal.medeasy.R;

import java.util.List;

public class AssessmentSheetsModleAdapter extends RecyclerView.Adapter < AssessmentSheetsModleAdapter.AssessmentSheetsModleAdapterHolder > {

    List<AssessmentSheetsModle> assessmentSheetsModleList;

    AssessmentSheetsModleAdapter(List<AssessmentSheetsModle> modles){
        this.assessmentSheetsModleList = modles ;
    }

    @NonNull
    @Override
    public AssessmentSheetsModleAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.assessment_sheets_modle_s,viewGroup,false) ;
        AssessmentSheetsModleAdapterHolder holder = new AssessmentSheetsModleAdapterHolder(row) ;
        return holder ;
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentSheetsModleAdapterHolder assessmentSheetsModleAdapterHolder, int i) {

        AssessmentSheetsModle modle = assessmentSheetsModleList.get(i)  ;
        assessmentSheetsModleAdapterHolder.elt5ssos.setText(modle.getElt5ssos());
        assessmentSheetsModleAdapterHolder.description.setText(modle.getDescription());
        assessmentSheetsModleAdapterHolder.drug.setText(modle.getDrug());
        assessmentSheetsModleAdapterHolder.date.setText(modle.getDate());
    }

    @Override
    public int getItemCount() {
        return assessmentSheetsModleList.size();
    }

                        /*** class el Holder ***/

    public  class AssessmentSheetsModleAdapterHolder extends RecyclerView.ViewHolder {
        TextView elt5ssos, description, drug, date;

        public AssessmentSheetsModleAdapterHolder(@NonNull View itemView) {
            super(itemView);
            elt5ssos = itemView.findViewById(R.id.elt5ssos);
            description = itemView.findViewById(R.id.description);
            drug = itemView.findViewById(R.id.drug);
            date = itemView.findViewById(R.id.date);
        }
    }

}
