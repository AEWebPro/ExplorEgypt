package com.example.ae.smartvisit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.adapters.RecyclerAdapterSelecteItemList;
import com.example.ae.smartvisit.modules.PlaceDataModel;
import com.example.ae.smartvisit.modules.SessionPlan;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PlanItemsList extends BaseActivity {

    @Bind(R.id.activity_places_recycler_view)
    RecyclerView activityPlacesRecyclerView;

    private String typeToDisplay;
    private ArrayList<PlaceDataModel> placesList;
    private RecyclerAdapterSelecteItemList adapter;

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
        placesList = new ArrayList<>();
        String[] namesArray = new String[6];
        String image = null;
        Random randomIndex = new Random();
        if (typeToDisplay.equals("Places")) {
            namesArray = getResources().getStringArray(R.array.sights_names);
            image = getResources().getString(R.string.pyramids_image);
        } else if (typeToDisplay.equals("Hotels")) {
            namesArray = getResources().getStringArray(R.array.Hotels_names);
            image = getResources().getString(R.string.hotel_image);
        } else if (typeToDisplay.equals("Restaurants")) {
            namesArray = getResources().getStringArray(R.array.restaurants_names);
            image = getResources().getString(R.string.retaurant_image);
        }

        for (int i = 0; i < 15; i++) {
            //placesList.add(i, new PlaceDataModel("Hotel name " + Integer.toString(i), getString(R.string.temp_text), getString(R.string.hotel_image), "Cairo", i, "", ""));
            placesList.add(i,new PlaceDataModel(
                    namesArray[randomIndex.nextInt(6)]
                    , getString(R.string.temp_text)
                    , image
                    , "Cairo", i, "", ""));

        }

        adapter = new RecyclerAdapterSelecteItemList(placesList, this, typeToDisplay);
        activityPlacesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        activityPlacesRecyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.save_discard_plan, menu);

        final MenuItem searchItem = menu.findItem(R.id.menu_plan_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setIconifiedByDefault(true);
        searchView.setQueryHint("Name or City");

        // Detect SearchView Expanding
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItemsVisibility(menu, searchItem, false);
            }
        });
        // Detect SearchView close
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setItemsVisibility(menu, searchItem, true);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final ArrayList<PlaceDataModel> filteredModelList = filter(placesList, newText);
                if (filteredModelList.size() > 0) {
                    adapter.setFilter(filteredModelList);
                    return true;
                } else {
                    Toast.makeText(application, "Not Found", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_plan_delete) {
            //TODO Save the object of the plan to the DB
            Toast.makeText(getBaseContext(), "Deleted", Toast.LENGTH_SHORT).show();
            SessionPlan.getSessionPlanInstance().destroyInstance();

            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.menu_plan_save) {
            //TODO Convert the object to the plan type and save in the DB
            Toast.makeText(getBaseContext(), "SessionPlan is saved!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }

    private void setItemsVisibility(final Menu menu, final MenuItem exception,
                                    final boolean visible) {
        for (int i = 0; i < menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (item != exception)
                item.setVisible(visible);
        }
    }

    private ArrayList<PlaceDataModel> filter(ArrayList<PlaceDataModel> models, String query) {
        query = query.toLowerCase();

        final ArrayList<PlaceDataModel> filteredModelList = new ArrayList<>();
        for (PlaceDataModel model : models) {
            final String text = model.getName().toLowerCase();
            final String textCity = model.getCity().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
            if (textCity.contains(query)) {
                filteredModelList.add(model);
            }
        }

        adapter = new RecyclerAdapterSelecteItemList(filteredModelList, this, typeToDisplay);

        activityPlacesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        activityPlacesRecyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        return filteredModelList;
    }
}