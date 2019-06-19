package com.example.nihal.medeasy.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nihal.medeasy.Models.AlarmModel;
import com.example.nihal.medeasy.R;

import java.util.ArrayList;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {
    private ArrayList<AlarmModel> alarmModels;
    private AlarmAdapter.OnItemClick mOnItemClick;
    private Context context;

    public AlarmAdapter(ArrayList<AlarmModel> alarmModels, Context context, AlarmAdapter.OnItemClick mOnItemClick) {
        this.alarmModels = alarmModels;
        this.mOnItemClick = mOnItemClick;
        this.context = context;
    }


    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.alarm_item, viewGroup, false);
        return new AlarmAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AlarmModel itemMode = alarmModels.get(i);

    }

    @Override
    public int getItemCount() {
        return alarmModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ImageType;
        TextView TextTitle, TextDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnItemClick.setOnItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClick {
        void setOnItemClick(int position);
    }
}
