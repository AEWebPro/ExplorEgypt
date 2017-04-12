package com.example.ae.smartvisit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
    public void openPlacesList() {
        Intent intent = new Intent(this, PlanItemsList.class);
        intent.putExtra(PLACE_TYPE, "Places");
        startActivity(intent);
    }

    @OnClick(R.id.select_place_hotels_btn)
    public void openHotelsList() {
        Intent intent = new Intent(this, PlanItemsList.class);
        intent.putExtra(PLACE_TYPE, "Hotels");
        startActivity(intent);
    }


    @OnClick(R.id.select_place_restaurant_btn)
    public void openRestaurantsList() {
        Intent intent = new Intent(this, PlanItemsList.class);
        intent.putExtra(PLACE_TYPE, "Restaurants");
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_discard_plan, menu);
        menu.findItem(R.id.menu_plan_search).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_plan_save) {
            //TODO Save the object of the plan to the DB
            Toast.makeText(getBaseContext(), "Plan is saved!", Toast.LENGTH_SHORT).show();
            Plan.getPlanInstance().destroyInstance();

            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;

        } else if (id == R.id.menu_plan_delete) {
            //TODO Save the object of the plan to the DB
            Toast.makeText(getBaseContext(), "Deleted", Toast.LENGTH_SHORT).show();
            Plan.getPlanInstance().destroyInstance();

            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return false;
    }
}
