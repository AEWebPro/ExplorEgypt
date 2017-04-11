package com.example.ae.smartvisit.adapters;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.activities.DetailView;
import com.example.ae.smartvisit.activities.HomeActivity;
import com.example.ae.smartvisit.modules.PlaceDataModel;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapterSelecteItemList extends RecyclerView.Adapter<RecyclerAdapterSelecteItemList.ItemInListAdapter> {
    List<PlaceDataModel> listOfItems;
    Context context;
    String typeToDisplay;

    public RecyclerAdapterSelecteItemList(List<PlaceDataModel> listOfItems, Context context, String typeToDisplay) {
        this.listOfItems = listOfItems;
        this.context = context;
        this.typeToDisplay = typeToDisplay;
    }

    @Override
    public ItemInListAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_place_item, parent, false);
        return new ItemInListAdapter(view);
    }

    @Override
    public void onBindViewHolder(ItemInListAdapter holder, final int position) {
        int placeHolder = 0;
        if (typeToDisplay.equals("Places")) {
            placeHolder = R.drawable.place_sight;
        } else if (typeToDisplay.equals("Hotels")) {
            placeHolder = R.drawable.hotel_icon;
        } else if (typeToDisplay.equals("Restaurants")) {
            placeHolder = R.drawable.res_icon;
        } else {
            placeHolder = R.mipmap.ic_launcher;
        }

        final PlaceDataModel place = listOfItems.get(position);
        Picasso.with(context).load(place.getImageUrl()).resize(110, 110).centerCrop()
                .placeholder(placeHolder).into(holder.itemImage);
        holder.itemTitle.setText(place.getName());
        holder.itemCity.setText(place.getCity());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent passedIntent = new Intent(context, new DetailView().getClass());
                passedIntent.putExtra("placeClicked", place);
                passedIntent.putExtra("parent_activity", "SELECT_PLACE");
                context.startActivity(passedIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    public static class ItemInListAdapter extends RecyclerView.ViewHolder {

        RoundedImageView itemImage;
        TextView itemTitle;
        TextView itemCity;

        public ItemInListAdapter(View itemView) {
            super(itemView);

            itemImage = (RoundedImageView) itemView.findViewById(R.id.item_select_image_view);
            itemTitle = (TextView) itemView.findViewById(R.id.item_select_place_title);
            itemCity = (TextView) itemView.findViewById(R.id.item_select_place_city);
        }
    }
}
