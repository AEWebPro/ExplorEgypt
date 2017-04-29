package com.example.ae.smartvisit.adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.activities.DetailView;
import com.example.ae.smartvisit.modules.PlaceDataModel;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterDayInfo extends RecyclerView.Adapter<RecyclerAdapterDayInfo.DayViewHolder>{

    private Context context;
    private List<PlaceDataModel> placesInDay;

    public RecyclerAdapterDayInfo(Context context, List<PlaceDataModel> placesInDay) {
        this.context = context;
        this.placesInDay = placesInDay;
    }

    @Override
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_of_day_details, parent, false);

        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DayViewHolder holder, int position) {
        final PlaceDataModel place = placesInDay.get(position);

        holder.placeOrder.setText(Integer.toString(position +1) + "- ");
        holder.placeTitle.setText(place.getName());

        Picasso.with(context).load(place.getImageUrl()).resize(110, 110).centerCrop()
                .placeholder(R.mipmap.ic_launcher).into(holder.placePicture);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passedIntent = new Intent(context, DetailView.class);
                passedIntent.putExtra("placeClicked", place);
                passedIntent.putExtra("parent_activity", "HOME");
                context.startActivity(passedIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return placesInDay.size();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder {
        TextView placeOrder;
        TextView placeTitle;
        RoundedImageView placePicture;

        public DayViewHolder(View itemView) {
            super(itemView);

            placeOrder = (TextView) itemView.findViewById(R.id.item_day_place_number);
            placeTitle = (TextView) itemView.findViewById(R.id.item_day_place_title);
            placePicture = (RoundedImageView) itemView.findViewById(R.id.item_day_image_view);

        }
    }
}