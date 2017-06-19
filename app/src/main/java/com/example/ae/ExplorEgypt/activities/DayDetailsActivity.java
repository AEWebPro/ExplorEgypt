package com.example.ae.ExplorEgypt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.adapters.RecyclerAdapterDayInfo;
import com.example.ae.ExplorEgypt.infrastructure.SimpleRecyclerDivider;
import com.example.ae.ExplorEgypt.modules.PairOfDayAndPlace;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DayDetailsActivity extends BaseActivity {

    @Bind(R.id.activity_day_details_recycler)
    RecyclerView activityDayDetailsRecycler;
    private PairOfDayAndPlace pairToDisplay;

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
        pairToDisplay = receivedIntent.getParcelableExtra("pair_details");

        activityDayDetailsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        activityDayDetailsRecycler.addItemDecoration(new SimpleRecyclerDivider(this));
        RecyclerAdapterDayInfo adapterDayInfo = new RecyclerAdapterDayInfo(this, pairToDisplay.getPlace());
        activityDayDetailsRecycler.setAdapter(adapterDayInfo);

        getSupportActionBar().setTitle("Day " + Integer.toString(pairToDisplay.getDay()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.plan_details_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.activity_plan_detail_map_icon){
            Intent passingIntent = new Intent(this, DayPlacesPathMapActivity.class);
            passingIntent.putExtra("pair_map", pairToDisplay);
            startActivity(passingIntent);
            return true;
        }

        return false;
    }
}
