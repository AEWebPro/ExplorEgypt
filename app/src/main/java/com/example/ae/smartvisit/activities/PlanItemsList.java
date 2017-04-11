package com.example.ae.smartvisit.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.adapters.RecyclerAdapterSelecteItemList;
import com.example.ae.smartvisit.modules.PlaceDataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


class PlanItemsList extends BaseActivity {

    @Bind(R.id.activity_places_recycler_view)
    RecyclerView activityPlacesRecyclerView;
    private String typeToDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        typeToDisplay = getIntent().getExtras().getString(SelectTypeToAddActivity.PLACE_TYPE);
        getSupportActionBar().setTitle(typeToDisplay);

        setUpAdapter();
    }

    private void setUpAdapter() {
        List<PlaceDataModel> placesList = new ArrayList<>();
        for(int i = 0; i < 15; i++ ){
            placesList.add(i, new PlaceDataModel("Hotel name " + Integer.toString(i),getString(R.string.temp_text),getString(R.string.hotel_image),"Cairo",i,"",""));
        }

        RecyclerAdapterSelecteItemList adapter = new RecyclerAdapterSelecteItemList(placesList, this, typeToDisplay);
        activityPlacesRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        activityPlacesRecyclerView.setAdapter(adapter);
    }
}
