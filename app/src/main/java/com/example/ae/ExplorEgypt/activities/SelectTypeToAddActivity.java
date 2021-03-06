package com.example.ae.ExplorEgypt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.adapters.RecyclerAdapterCategories;
import com.example.ae.ExplorEgypt.infrastructure.HelperClass;
import com.example.ae.ExplorEgypt.modules.CategoryItem;
import com.example.ae.ExplorEgypt.modules.Plan;
import com.example.ae.ExplorEgypt.modules.SessionPlan;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SelectTypeToAddActivity extends BaseActivity {


    public static final String PLACE_TYPE = "PLACE_TYPE";
    @Bind(R.id.categories_recycle_view)
    RecyclerView categoriesRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_place_to_add);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setTitle(SessionPlan.getSessionPlanInstance().getPlanName());

        setupCatRecycler();
    }

    void setupCatRecycler() {
        ArrayList<CategoryItem> categoryItems = new ArrayList<>();

        categoryItems.add(new CategoryItem(R.drawable.restaurant, "Restaurant"));
        categoryItems.add(new CategoryItem(R.drawable.hotel, "Hotel"));
        categoryItems.add(new CategoryItem(R.drawable.pharaonic, "Pharaonic"));
        categoryItems.add(new CategoryItem(R.drawable.islamic, "Islamic"));
        categoryItems.add(new CategoryItem(R.drawable.nature_reserve, "Natural Parks"));
        categoryItems.add(new CategoryItem(R.drawable.beach, "Beaches"));
        categoryItems.add(new CategoryItem(R.drawable.nightclub, "Nightclubs"));
        categoryItems.add(new CategoryItem(R.drawable.entertainment, "Entertainment"));

        RecyclerAdapterCategories adapterCategories = new RecyclerAdapterCategories(this, categoryItems, 1);
        categoriesRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
        categoriesRecycleView.setAdapter(adapterCategories);
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
            //Save the plan to the server
            Toast.makeText(getBaseContext(), "Plan is created!", Toast.LENGTH_SHORT).show();

            Plan savedPlan = new Plan(
                    SessionPlan.getSessionPlanInstance().getPlanName(),
                    SessionPlan.getSessionPlanInstance().getPlanStartDate(),
                    SessionPlan.getSessionPlanInstance().getPlanEndDate(),
                    false,
                    HelperClass.orderThePairs(SessionPlan.getSessionPlanInstance().getPairOfData()));

            Intent intent = new Intent(this, YourPlanes.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;

        } else if (id == R.id.menu_plan_delete) {
            //Delete the plan
            Toast.makeText(getBaseContext(), "Deleted", Toast.LENGTH_SHORT).show();
            SessionPlan.getSessionPlanInstance().destroyInstance();

            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return false;
    }
}
