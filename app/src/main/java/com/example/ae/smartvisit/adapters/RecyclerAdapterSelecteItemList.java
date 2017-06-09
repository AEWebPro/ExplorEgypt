package com.example.ae.smartvisit.adapters;


import android.content.Context;
import android.content.Intent;
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

public class RecyclerAdapterSelecteItemList extends RecyclerView.Adapter<RecyclerAdapterSelecteItemList.ItemInListAdapter> {
    ArrayList<PlaceDataModel> listOfItems;
    Context context;
    String typeToDisplay;
    ArrayList<PlaceDataModel> filteredPlaces;

    public RecyclerAdapterSelecteItemList(ArrayList<PlaceDataModel> listOfItems, Context context, String typeToDisplay) {
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
        int groubId ;
        if (typeToDisplay.equals("Pharaonic")) {
            groubId = R.drawable.pharaonic;
        } else if (typeToDisplay.equals("Hotel")) {
            groubId = R.drawable.hotel;
        } else if (typeToDisplay.equals("Restaurant")) {
            groubId = R.drawable.restaurant;
        }else if (typeToDisplay.equals("Religious")) {
            groubId = R.drawable.islamic;
        }else if (typeToDisplay.equals("Natural Parks")) {
            groubId = R.drawable.nature_reserve;
        }else if (typeToDisplay.equals("Beaches")) {
            groubId = R.drawable.beach;
        }else if (typeToDisplay.equals("Nightclubs")) {
            groubId = R.drawable.nightclub;
        }else if (typeToDisplay.equals("Entertainment")) {
            groubId = R.drawable.entertainment;
        }else
        {
            groubId = R.drawable.pyramids;
        }

        final PlaceDataModel place = listOfItems.get(position);
        String[] imagesList = place.getImageUrl().split(",");

        Picasso.with(context).load(imagesList[0]).resize(110, 110).centerCrop()
                .placeholder(groubId).into(holder.itemImage);
        holder.itemTitle.setText(place.getName());

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


    public void setFilter(List<PlaceDataModel> placeModel) {
        filteredPlaces = new ArrayList<>();
        filteredPlaces.addAll(placeModel);
        notifyDataSetChanged();
    }

    public static class ItemInListAdapter extends RecyclerView.ViewHolder {

        RoundedImageView itemImage;
        TextView itemTitle;

        public ItemInListAdapter(View itemView) {
            super(itemView);

            itemImage = (RoundedImageView) itemView.findViewById(R.id.item_select_image_view);
            itemTitle = (TextView) itemView.findViewById(R.id.item_select_place_title);
        }
    }
}
