package com.example.ae.ExplorEgypt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.adapters.RecyclerAdapterPlanDetails;
import com.example.ae.ExplorEgypt.infrastructure.HelperClass;
import com.example.ae.ExplorEgypt.modules.PairOfDayAndPlace;
import com.example.ae.ExplorEgypt.modules.Plan;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YourPlanDetails extends BaseActivity{

    @Bind(R.id.activity_your_plan_detail_startDate_text)
    TextView activityYourPlanDetailStartDateText;
    @Bind(R.id.activity_your_plan_detail_recyclerView)
    RecyclerView activityYourPlanDetailRecyclerView;

    private Plan planeDisplayed;

    private RecyclerAdapterPlanDetails adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_plan_detail);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        planeDisplayed = intent.getParcelableExtra("PlanDetails");


        activityYourPlanDetailStartDateText.setText("o the plan starts on :" + planeDisplayed.getPlanStartDate());

        activityYourPlanDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new RecyclerAdapterPlanDetails(this, HelperClass.orderThePairs(planeDisplayed.getPairOfData()));
        activityYourPlanDetailRecyclerView.setAdapter(adapter);

        toolbar.setTitle(planeDisplayed.getPlanName());
        getSupportActionBar().setTitle(planeDisplayed.getPlanName());
    }


}
