package com.example.nihal.medeasy.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nihal.medeasy.DoctorHome;
import com.example.nihal.medeasy.Models.CategoriesModel;
import com.example.nihal.medeasy.Models.DoctorHomeModel;
import com.example.nihal.medeasy.R;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorHolder> {
    ArrayList<DoctorHomeModel> doctorModelArrayList;
    Context context;
    ArrayList<String> ids;
    private DoctorAdapter.OnItemClick mOnItemClick;

    public DoctorAdapter(ArrayList<DoctorHomeModel> doctorModelArrayList, Context context, DoctorAdapter.OnItemClick mOnItemClick) {
        this.doctorModelArrayList = doctorModelArrayList;
        this.context = context;
        this.mOnItemClick = mOnItemClick;
    }

    @NonNull
    @Override
    public DoctorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.doctor_item, parent, false);
        return new DoctorAdapter.DoctorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorHolder holder, final int position) {
        final DoctorHomeModel doctorHomeModel = doctorModelArrayList.get(position);
        holder.click_text.setText(doctorHomeModel.getText());
        if (doctorHomeModel.getImage() == 0) {
            holder.click_image.setImageDrawable(context.getResources().getDrawable(R.drawable.file)) ; //Url of the image to load.
        } else if (doctorHomeModel.getImage() == 1) {
            holder.click_image.setImageDrawable(context.getResources().getDrawable(R.drawable.injection)) ; //Url of the image to load.

        } else if (doctorHomeModel.getImage() == 2) {
            holder.click_image.setImageDrawable(context.getResources().getDrawable(R.drawable.analysis))  ;//Url of the image to load.

        } else if (doctorHomeModel.getImage() == 3) {
            holder.click_image.setImageDrawable(context.getResources().getDrawable(R.drawable.patient)) ; //Url of the image to load.

        }
        else if (doctorHomeModel.getImage() == 4) {
            holder.click_image.setImageDrawable(context.getResources().getDrawable(R.drawable.blood_bag)) ; //Url of the image to load.

        }
        else {
            holder.click_image.setImageDrawable(context.getResources().getDrawable(R.drawable.file))  ;//Url of the image to load.

        }


    }

    @Override
    public int getItemCount() {
        return doctorModelArrayList.size();
    }

    class DoctorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView click_text;
        ImageView click_image;


        public DoctorHolder(@NonNull View itemView) {
            super(itemView);
            click_text = itemView.findViewById(R.id.click_text);
            click_image = itemView.findViewById(R.id.click_image);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            mOnItemClick.setOnItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClick {
        void setOnItemClick(int position);
    }


}
