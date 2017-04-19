package com.example.ae.smartvisit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.adapters.RecyclerAdapterDayInfo;
import com.example.ae.smartvisit.infrastructure.SimpleRecyclerDivider;
import com.example.ae.smartvisit.modules.PairOfDayAndPlace;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DayDetailsActivity extends BaseActivity {

    @Bind(R.id.activity_day_details_recycler)
    RecyclerView activityDayDetailsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_details);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent receivedIntent = getIntent();
        PairOfDayAndPlace pairToDisplay = receivedIntent.getParcelableExtra("pair_details");

        activityDayDetailsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        activityDayDetailsRecycler.addItemDecoration(new SimpleRecyclerDivider(this));
        RecyclerAdapterDayInfo adapterDayInfo = new RecyclerAdapterDayInfo(this, pairToDisplay.getPlace());
        activityDayDetailsRecycler.setAdapter(adapterDayInfo);

        getSupportActionBar().setTitle("Day " + Integer.toString(pairToDisplay.getDay()));
    }
}
