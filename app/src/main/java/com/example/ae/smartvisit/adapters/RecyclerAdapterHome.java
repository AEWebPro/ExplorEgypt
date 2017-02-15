package com.example.ae.smartvisit.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.activities.DetailView;
import com.example.ae.smartvisit.activities.HomeActivity;
import com.example.ae.smartvisit.modules.PlaceDataModel;

import java.util.ArrayList;

public class RecyclerAdapterHome extends RecyclerView.Adapter<RecyclerAdapterHome.PlaceVeiwHolder> {

    private ArrayList<PlaceDataModel> placesSelected;
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<PlaceDataModel> mPlaceModel;

    public RecyclerAdapterHome(Context context)
    {
        placesSelected = new ArrayList<>();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addPlaces(ArrayList<PlaceDataModel> places){
        placesSelected.addAll(places);
        notifyDataSetChanged();
    }
    public void itemSelected(Object placeClicked){
        int position = placesSelected.indexOf(placeClicked);
        //Toast.makeText(context,"Item of index :" + position + "Is clicked!", Toast.LENGTH_SHORT).show();
        Intent passedIntent = new Intent(context, new DetailView().getClass());
        passedIntent.putExtra("placeClicked", placesSelected.get(position));
        context.startActivity(passedIntent);
    }

    @Override
    public int getItemCount() {
        return placesSelected.size();
    }

    @Override
    public PlaceVeiwHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_home, parent,false);
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
    public void onBindViewHolder(PlaceVeiwHolder holder, int position) {
        PlaceDataModel place = placesSelected.get(position);
        holder.titleTextView.setText( place.getName());
        holder.itemView.setTag( place);
    }

    public void setFilter(ArrayList<PlaceDataModel> placeModel) {
        mPlaceModel = new ArrayList<>();
        mPlaceModel.addAll(placeModel);
        notifyDataSetChanged();
    }

    protected class PlaceVeiwHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private ImageView pictureImageView;

        public PlaceVeiwHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.list_item_home_title);
            pictureImageView = (ImageView) itemView.findViewById(R.id.list_item_home_picture);
        }
    }

}
