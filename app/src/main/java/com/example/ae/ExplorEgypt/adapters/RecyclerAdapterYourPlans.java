package com.example.ae.ExplorEgypt.adapters;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.activities.YourPlanDetails;
import com.example.ae.ExplorEgypt.infrastructure.Receiver;
import com.example.ae.ExplorEgypt.modules.Plan;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;


public class RecyclerAdapterYourPlans extends RecyclerView.Adapter<RecyclerAdapterYourPlans.PlanViewHolder> {
    private ArrayList<Plan> myPlans;
    private Context context;
    private SparseBooleanArray mSelectedItemsIds;

    public RecyclerAdapterYourPlans(ArrayList<Plan> myPlans, Context context) {
        this.myPlans = myPlans;
        this.context = context;
        mSelectedItemsIds = new SparseBooleanArray();
    }

    @Override
    public PlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recommand_programs_list, parent, false);
        return new PlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PlanViewHolder planViewHolder, final int position) {


        final Plan plan = myPlans.get(position);
        planViewHolder.planTitle.setText(plan.getPlanName());
        planViewHolder.planStartDate.setText("o start date:" + plan.getPlanStartDate());
        String[] imagesList = plan.getPairOfData().get(0).getPlace().get(0).getImageUrl().split(",");

        Picasso.with(context)
                .load(imagesList[0])
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
                });
       /* planViewHolder.planIsActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,Integer.toString(position) +  " is clicked",Toast.LENGTH_SHORT).show();
                //TODO: add a Notification when is active
            }
        });*/

        if (plan.isActive()) {
            planViewHolder.planIsActive.setChecked(true);
        }

        planViewHolder.planIsActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (planViewHolder.planIsActive.isChecked()) {
                    Calendar planAlaramDay = Calendar.getInstance();

                    planAlaramDay.add(Calendar.DATE,1);

                    Intent intent = new Intent(context, Receiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 001, intent, 0);

                    AlarmManager am = (AlarmManager)context.getSystemService(ALARM_SERVICE);
                    am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000 * 3, pendingIntent);

                    Toast.makeText(context, plan.getPlanName() + " is Activated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, plan.getPlanName() + " is Deactivated", Toast.LENGTH_SHORT).show();
                }
            }
        });


        /** Change background color of the selected items in list view  **/
        planViewHolder.blackView
                .setAlpha(mSelectedItemsIds.get(position) ? 0.4f
                        : 0.2f);

        planViewHolder.planBgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent planIntent = new Intent(context, YourPlanDetails.class);
                planIntent.putExtra("PlanDetails", plan);
                context.startActivity(planIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myPlans.size();
    }

    public class PlanViewHolder extends RecyclerView.ViewHolder {
        private TextView planTitle;
        private TextView planStartDate;
        private ImageView planBgImage;
        private Switch planIsActive;
        private ProgressBar planProgress;
        private View blackView;

        public PlanViewHolder(View itemView) {
            super(itemView);

            planTitle = (TextView) itemView.findViewById(R.id.activity_program_list_plan_title);
            planStartDate = (TextView) itemView.findViewById(R.id.activity_program_list_plan_startDate);
            planIsActive = (Switch) itemView.findViewById(R.id.activity_program_list_active_radio_btn);
            planBgImage = (ImageView) itemView.findViewById(R.id.activity_program_list_image_bg);
            planProgress = (ProgressBar) itemView.findViewById(R.id.activity_program_list_image_progress);
            blackView = (View) itemView.findViewById(R.id.view_blacklayer);
        }
    }


    /***
     * Methods required for do selections, remove selections, etc.
     */

    //Toggle selection methods
    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }


    //Remove selected selections
    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }


    //Put or delete selected position into SparseBooleanArray
    public void selectView(int position, boolean value) {
        if (value) {
            mSelectedItemsIds.put(position, value);

        } else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();
    }

    //Get total selected count
    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    //Return all selected ids
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }


}
