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
import com.example.ae.ExplorEgypt.modules.PairOfDayAndPlace;
import com.example.ae.ExplorEgypt.modules.Plan;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YourPlanDetails extends BaseActivity implements Comparable{

    @Bind(R.id.activity_your_plan_detail_startDate_text)
    TextView activityYourPlanDetailStartDateText;
    @Bind(R.id.activity_your_plan_detail_recyclerView)
    RecyclerView activityYourPlanDetailRecyclerView;

    private Plan planeDisplayed;

    private RecyclerAdapterPlanDetails adapter;
    private ArrayList<PairOfDayAndPlace> orderedPairs;


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

        orderedPairs = orderThePairs(planeDisplayed.getPairOfData());
        String planJson = new Gson().toJson(orderedPairs);

        activityYourPlanDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new RecyclerAdapterPlanDetails(this, orderedPairs);
        activityYourPlanDetailRecyclerView.setAdapter(adapter);

        toolbar.setTitle(planeDisplayed.getPlanName());
        getSupportActionBar().setTitle(planeDisplayed.getPlanName());
    }


    //Order the list of pairs and collect the duplicated ones.
    public ArrayList<PairOfDayAndPlace> orderThePairs(ArrayList<PairOfDayAndPlace> passedPair) {
        ArrayList<PairOfDayAndPlace> returnPairsList = new ArrayList<>();

        for (int i = 0; i < passedPair.size(); i++) {
            boolean isUnique = true;
            PairOfDayAndPlace uniquePair = new PairOfDayAndPlace();
            uniquePair.setDay(passedPair.get(i).getDay());
            uniquePair.addPlaceToList(passedPair.get(i).getPlace().get(0));

            for (int j = i + 1; j < passedPair.size(); j++) {
                if (passedPair.get(i).getDay() == passedPair.get(j).getDay()) {
                    uniquePair.addPlaceToList(passedPair.get(j).getPlace().get(0));
                }
            }

            if (!returnPairsList.isEmpty()) {
                for (int k = 0; k < returnPairsList.size(); k++) {
                    if (uniquePair.getDay() == returnPairsList.get(k).getDay()) {
                        isUnique = false;
                    }
                }
            }

            if (isUnique) {
                returnPairsList.add(uniquePair);
            }
        }

        //sort the list by the day order
        Collections.sort(returnPairsList, new Comparator<PairOfDayAndPlace>() {
                    @Override
                    public int compare(PairOfDayAndPlace pairOfDayAndPlace, PairOfDayAndPlace t1) {
                        return  pairOfDayAndPlace.getDay() - t1.getDay();
                    }
                });

        return returnPairsList;
    }
    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }

}
