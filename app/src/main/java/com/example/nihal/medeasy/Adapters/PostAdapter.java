package com.example.nihal.medeasy.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.nihal.medeasy.Models.PostModel;
import com.example.nihal.medeasy.R;

import java.util.List;
import java.util.zip.Inflater;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder>   {

    List<PostModel> postModelList ;

    public PostAdapter(List<PostModel> postModelList) {

        this.postModelList = postModelList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post,viewGroup,false) ;
        PostHolder holder = new PostHolder(row) ;
        return holder ;

    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder postHolder, int i) {

        PostModel model = postModelList.get(i) ;
        postHolder.title.setText(model.getTitle());
        postHolder.description.setText(model.getDescription());
        //    postHolder.image.setText(model.getImage());


    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {
        TextView title, description/*, image*/;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
//            image = itemView.findViewById(R.id.image);

        }
    }
}