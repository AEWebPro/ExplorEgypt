package com.example.ae.ExplorEgypt.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.activities.DetailView;
import com.example.ae.ExplorEgypt.modules.PlaceDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapterPlacesWithPics extends RecyclerView.Adapter<RecyclerAdapterPlacesWithPics.PlaceVeiwHolder> {


    private ArrayList<PlaceDataModel> placesSelected;
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<PlaceDataModel> mPlaceModel;

    public RecyclerAdapterPlacesWithPics(Context context) {
        placesSelected = new ArrayList<>();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addPlaces(ArrayList<PlaceDataModel> places) {
        placesSelected.addAll(places);
        notifyDataSetChanged();
    }

    public void itemSelected(Object placeClicked) {
        int position = placesSelected.indexOf(placeClicked);
        //Toast.makeText(context,"Item of index :" + position + "Is clicked!", Toast.LENGTH_SHORT).show();
        Intent passedIntent = new Intent(context, new DetailView().getClass());
        passedIntent.putExtra("placeClicked", placesSelected.get(position));
        passedIntent.putExtra("parent_activity", "HOME");

        context.startActivity(passedIntent);
    }

    @Override
    public int getItemCount() {
        return placesSelected.size();
    }

    @Override
    public PlaceVeiwHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_pic_and_title, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object placeClicked = (Object) view.getTag();
                itemSelected(placeClicked);
            }
        });
        return new PlaceVeiwHolder(view);
    }

    @Override
    public void onBindViewHolder(final PlaceVeiwHolder holder, int position) {
        PlaceDataModel place = placesSelected.get(position);
        holder.placeTitle.setText(place.getName());
        String[] placeImagesUrls = placesSelected.get(position).getImageUrl().split(",");
        Picasso.with(context).load(placeImagesUrls[0]).centerCrop().fit().into(holder.pictureImageView);

        holder.itemView.setTag(place);
    }

    public void setFilter(ArrayList<PlaceDataModel> placeModel) {
        mPlaceModel = new ArrayList<>();
        mPlaceModel.addAll(placeModel);
        notifyDataSetChanged();
    }

    protected class PlaceVeiwHolder extends RecyclerView.ViewHolder {
        private TextView placeTitle;
        private ImageView pictureImageView;

        public PlaceVeiwHolder(View itemView) {
            super(itemView);

            placeTitle = (TextView) itemView.findViewById(R.id.list_item_home_title);
            pictureImageView = (ImageView) itemView.findViewById(R.id.list_item_home_picture);
        }
    }

}
