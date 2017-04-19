package com.example.ae.smartvisit.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.modules.PairOfDayAndPlace;
import com.example.ae.smartvisit.modules.PlaceDataModel;
import com.example.ae.smartvisit.modules.Plan;
import com.example.ae.smartvisit.modules.SessionPlan;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class YourPlanes extends BaseActivity {

    ArrayList<PairOfDayAndPlace> placesInPlan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_planes);

        getSupportActionBar().setTitle("Your Plans");

        ArrayList<PlaceDataModel> listOfPlaces1 = new ArrayList<>();
        listOfPlaces1.add(new PlaceDataModel("Hotel",getString(R.string.temp_text),getString(R.string.hotel_image),"",1,"",""));

        ArrayList<PlaceDataModel> listOfPlaces2 = new ArrayList<>();
        listOfPlaces2.add(new PlaceDataModel("Pyramid",getString(R.string.temp_text),getString(R.string.pyramids_image),"",2,"",""));

        placesInPlan.add(new PairOfDayAndPlace(3,listOfPlaces1));

        placesInPlan.add(new PairOfDayAndPlace(1,listOfPlaces1));
        placesInPlan.add(new PairOfDayAndPlace(2,listOfPlaces2));
        placesInPlan.add(new PairOfDayAndPlace(2,listOfPlaces2));
        placesInPlan.add(new PairOfDayAndPlace(2,listOfPlaces1));

        placesInPlan.add(new PairOfDayAndPlace(1,listOfPlaces1));
        placesInPlan.add(new PairOfDayAndPlace(1,listOfPlaces1));
        placesInPlan.add(new PairOfDayAndPlace(1,listOfPlaces1));
        placesInPlan.add(new PairOfDayAndPlace(1,listOfPlaces1));


        ListView list = (ListView) findViewById(R.id.your_list);
        list.setAdapter(new ListResources(this, placesInPlan));

    }

    private class ListResources extends BaseAdapter {
        ArrayList<Plan> myPlans = new ArrayList<>();
        Context context;

        ListResources(Context context, ArrayList<PairOfDayAndPlace> listOfPairs) {
            this.context = context;
            myPlans.add(new Plan("Visit egypt", "22/5/2020","30/5/2020",false,listOfPairs));
            myPlans.add(new Plan("Giza is near", "1/8/2020","30/9/2020",true,listOfPairs));


        }

        @Override
        public int getCount() {
            return myPlans.size();
        }

        @Override
        public Object getItem(int position) {
            return myPlans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final PlanViewHolder planViewHolder;

            if(convertView == null){
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.activity_recommand_programs_list, parent, false);
                planViewHolder = new PlanViewHolder();

                planViewHolder.planTitle = (TextView) convertView.findViewById(R.id.activity_program_list_plan_title);
                planViewHolder.planStartDate = (TextView) convertView.findViewById(R.id.activity_program_list_plan_startDate);
                planViewHolder.planIsActive = (Switch) convertView.findViewById(R.id.activity_program_list_active_radio_btn);
                planViewHolder.planBgImage = (ImageView) convertView.findViewById(R.id.activity_program_list_image_bg);
                planViewHolder.planProgress = (ProgressBar) convertView.findViewById(R.id.activity_program_list_image_progress);
                convertView.setTag(planViewHolder);
            }else {
                planViewHolder = (PlanViewHolder) convertView.getTag();
            }

            final Plan plan = myPlans.get(position);
            planViewHolder.planTitle.setText( plan.getPlanName());
            planViewHolder.planStartDate.setText("o start date:" + plan.getPlanStartDate());
            Picasso.with(context)
                    .load(plan.getPairOfData().get(0).getPlace().get(0).getImageUrl())
                    .centerCrop()
                    .fit()
                    .into(planViewHolder.planBgImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            planViewHolder.planProgress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {

                        }
                    }) ;
            planViewHolder.planIsActive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getBaseContext(),Integer.toString(position) +  " is clicked",Toast.LENGTH_SHORT).show();
                }
            });

            planViewHolder.planBgImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent planIntent = new Intent(YourPlanes.this,YourPlanDetails.class);
                    planIntent.putExtra("PlanDetails", plan);
                    startActivity(planIntent);
                }
            });

            return convertView;
        }
    }

    private class PlanViewHolder{
        private TextView planTitle;
        private TextView planStartDate;
        private ImageView planBgImage;
        private Switch planIsActive;
        private ProgressBar planProgress;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }
}
