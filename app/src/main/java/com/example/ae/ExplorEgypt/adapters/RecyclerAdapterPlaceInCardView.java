package com.example.ae.ExplorEgypt.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.activities.DetailView;
import com.example.ae.ExplorEgypt.modules.PlaceDataModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapterPlaceInCardView extends RecyclerView.Adapter<RecyclerAdapterPlaceInCardView.CardViewViewHolder> {

    private ArrayList<PlaceDataModel> placesSelected;
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<PlaceDataModel> mPlaceModel;

    public RecyclerAdapterPlaceInCardView(Context context) {
        placesSelected = new ArrayList<>();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void addPlaces(ArrayList<PlaceDataModel> places) {
        placesSelected.addAll(places);
        notifyDataSetChanged();
    }

    public void itemSelected(Object placeClicked, View view) {
        int position = placesSelected.indexOf(placeClicked);
        //Toast.makeText(context,"Item of index :" + position + "Is clicked!", Toast.LENGTH_SHORT).show();

        Intent passedIntent = new Intent(context, DetailView.class);
        passedIntent.putExtra("placeClicked", placesSelected.get(position));
        passedIntent.putExtra("parent_activity", "HOME");

        /*if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((HomeActivity) context,
                    Pair.create(view, "selectedPlace")
            ).toBundle();
            context.startActivity(passedIntent, bundle);
        } else {
            context.startActivity(passedIntent);
        }*/
         context.startActivity(passedIntent);

    }

    @Override
    public CardViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_home_card_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object placeClicked = (Object) view.getTag();
                itemSelected(placeClicked, view);
            }
        });
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardViewViewHolder holder, int position) {
        PlaceDataModel place = placesSelected.get(position);
        holder.placeTitle.setText(place.getName());
        holder.placeDescription.setText(place.getDescription());
        String[] imagesList = place.getImageUrl().split(",");

        if (imagesList[0] != "" ){
            Picasso.with(context).load(imagesList[0]).centerCrop().fit().into(holder.placeImage, new Callback() {
                @Override
                public void onSuccess() {
                    holder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError() {

                }
            });
        }
        holder.itemView.setTag(place);
    }

    public void setFilter(ArrayList<PlaceDataModel> placeModel) {
        mPlaceModel = new ArrayList<>();
        mPlaceModel.addAll(placeModel);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return placesSelected.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        private ImageView placeImage;
        private ProgressBar progressBar;
        private TextView placeTitle;
        private TextView placeDescription;

        public CardViewViewHolder(View itemView) {
            super(itemView);

            placeImage = (ImageView) itemView.findViewById(R.id.card_view_item_image);
            placeTitle = (TextView) itemView.findViewById(R.id.card_view_place_title);
            placeDescription = (TextView) itemView.findViewById(R.id.card_view_place_desc);
            progressBar = (ProgressBar) itemView.findViewById(R.id.card_view_image_progressPar);
        }
    }
}
