package com.example.ae.smartvisit.adapters;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.activities.DayDetailsActivity;
import com.example.ae.smartvisit.activities.DetailView;
import com.example.ae.smartvisit.activities.HomeActivity;
import com.example.ae.smartvisit.modules.PairOfDayAndPlace;
import com.example.ae.smartvisit.modules.PlaceDataModel;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapterPlanDetails extends RecyclerView.Adapter<RecyclerAdapterPlanDetails.PlanDetailsViewHolder>{

    private Context context;
    private ArrayList<PairOfDayAndPlace> listOfPairs;


    public RecyclerAdapterPlanDetails(Context context, ArrayList<PairOfDayAndPlace> listOfPairs) {
        this.context = context;
        this.listOfPairs = listOfPairs;
    }

    @Override
    public PlanDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.your_plan_detail_item, parent, false);

        return new PlanDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlanDetailsViewHolder holder, int position) {
        final PairOfDayAndPlace pair = listOfPairs.get(position);

        holder.dayTextView.setText("Day " + Integer.toString(pair.getDay()));

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                150, 150);

        for (int i = 0; i < pair.getPlace().size(); i++) {


            final RoundedImageView placeImage = new RoundedImageView(context);
            placeImage.setId(i + 1);
            placeImage.setOval(true);
            placeImage.setCornerRadius(30);
            placeImage.setLayoutParams(params);
            placeImage.setPadding(4,4,4,6);

            Picasso.with(context).load(pair.getPlace().get(i).getImageUrl()).centerCrop().fit().into(placeImage);

            final int finalI = i;
            placeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "open place  " + placeImage.getId(), Toast.LENGTH_SHORT).show();
                    
                    Intent passedIntent = new Intent(context, DetailView.class);
                    passedIntent.putExtra("placeClicked", pair.getPlace().get(finalI));
                    passedIntent.putExtra("parent_activity", "HOME");
                    context.startActivity(passedIntent);
                }
            });
            
            holder.placesContainer.addView(placeImage);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passedIntent = new Intent(context, DayDetailsActivity.class);
                passedIntent.putExtra("pair_details", pair);
                context.startActivity(passedIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfPairs.size();
    }

    public class PlanDetailsViewHolder extends RecyclerView.ViewHolder{
        private TextView dayTextView;
        private LinearLayout placesContainer;
        public PlanDetailsViewHolder(View itemView) {
            super(itemView);

            dayTextView = (TextView) itemView.findViewById(R.id.plan_detail_item_day_text);
            placesContainer = (LinearLayout) itemView.findViewById(R.id.plan_detail_day_items_container);
        }
    }

}
