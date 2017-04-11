package com.example.ae.smartvisit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.modules.Plan;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.textAppearanceMedium;

public class SelectTypeToAddActivity extends BaseActivity {


    public static final String PLACE_TYPE = "PLACE_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_place_to_add);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setTitle(Plan.getPlanInstance().getPlanName());

        ButterKnife.bind(this);
    }

    @OnClick(R.id.select_place_sight_btn)
    public void openPlacesList(){
        Intent intent = new Intent(this, PlanItemsList.class);
        intent.putExtra(PLACE_TYPE,"Places");
        startActivity(intent);
    }

    @OnClick(R.id.select_place_hotels_btn)
    public void openHotelsList(){
        Intent intent = new Intent(this, PlanItemsList.class);
        intent.putExtra(PLACE_TYPE,"Hotels");
        startActivity(intent);
    }


    @OnClick(R.id.select_place_restaurant_btn)
    public void openRestaurantsList(){
        Intent intent = new Intent(this, PlanItemsList.class);
        intent.putExtra(PLACE_TYPE,"Restaurants");
        startActivity(intent);
    }

}