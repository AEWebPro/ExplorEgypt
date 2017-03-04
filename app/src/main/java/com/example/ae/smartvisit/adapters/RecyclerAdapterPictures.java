package com.example.ae.smartvisit.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.activities.PictureContainerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmed on 02/03/2017.
 */

public class RecyclerAdapterPictures extends RecyclerView.Adapter<RecyclerAdapterPictures.PictureViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> imagesURL;
    private String imageUrl;

    public RecyclerAdapterPictures (Context context, ArrayList<String> imagesURL){
        this.context = context;
        this.imagesURL = imagesURL;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewHolder = inflater.inflate(R.layout.single_pic_item, parent,false);
        return new PictureViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        imageUrl = imagesURL.get(position);
        Picasso.with(context).load(imageUrl).resize(100,100).centerCrop().placeholder(R.mipmap.ic_launcher).into( holder.picture);
    }

    @Override
    public int getItemCount() {
        return imagesURL.size();
    }

    protected class PictureViewHolder extends RecyclerView.ViewHolder{
        ImageView picture;

        public PictureViewHolder(View itemView) {
            super(itemView);

            picture = (ImageView) itemView.findViewById(R.id.picture_item);
            picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = PictureContainerActivity.newIntent(context, imageUrl);
                    context.startActivity(intent);
                }
            });
        }
    }
}

