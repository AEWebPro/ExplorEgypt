package com.example.ae.smartvisit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.adapters.RecyclerAdapterSelecteItemList;
import com.example.ae.smartvisit.modules.PlaceDataModel;
import com.example.ae.smartvisit.modules.RequestParameters;
import com.example.ae.smartvisit.modules.SessionPlan;
import com.example.ae.smartvisit.modules.TableRequest;
import com.example.ae.smartvisit.rest.OurApiClient;
import com.example.ae.smartvisit.rest.TestApiEndPoint;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlanItemsList extends BaseActivity {

    private RecyclerView activityPlacesRecyclerView;

    private String typeToDisplay;
    private ArrayList<PlaceDataModel> placesList;
    private RecyclerAdapterSelecteItemList adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.plan_list_progressBar);
        typeToDisplay = getIntent().getExtras().getString("category");
        activityPlacesRecyclerView = (RecyclerView) findViewById(R.id.activity_places_recycler_view);

        getSupportActionBar().setTitle(typeToDisplay);

        if (!isNetworkAvailable()) {
            Toast.makeText(this, "No internet connection!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        } else {
            getPlacesList();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.save_discard_plan, menu);

        final MenuItem searchItem = menu.findItem(R.id.menu_plan_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setIconifiedByDefault(true);
        searchView.setQueryHint("Enter Name");

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
        if (isNetworkAvailable()) {
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
        }

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
            if(isNetworkAvailable()) {
                //TODO Convert the object to the plan type and save in the DB
                Toast.makeText(getBaseContext(), "SessionPlan is saved!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            }else {
                Toast.makeText(application, "No internet connection!", Toast.LENGTH_SHORT).show();
                return false;
            }
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

            if (text.contains(query) || textCity.contains(query)) {
                filteredModelList.add(model);
            }
        }

        RecyclerAdapterSelecteItemList searchAdapter = new RecyclerAdapterSelecteItemList(filteredModelList, PlanItemsList.this, typeToDisplay);
        activityPlacesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        activityPlacesRecyclerView.setAdapter(searchAdapter);

        searchAdapter.notifyDataSetChanged();
        return filteredModelList;
    }


    private void getPlacesList() {
        final TestApiEndPoint ourApiEndPoint = OurApiClient
                .getClient().create(TestApiEndPoint.class);
        String groubId = "";

        if (typeToDisplay.equals("Pharaonic")) {
            groubId = "3";
        } else if (typeToDisplay.equals("Hotel")) {
            groubId = "2";
        } else if (typeToDisplay.equals("Restaurant")) {
            groubId = "1";
        }else if (typeToDisplay.equals("Islamic")) {
            groubId = "4";
        }else if (typeToDisplay.equals("Natural Parks")) {
            groubId = "5";
        }else if (typeToDisplay.equals("Beaches")) {
            groubId = "6";
        }else if (typeToDisplay.equals("Nightclubs")) {
            groubId = "7";
        }else if (typeToDisplay.equals("Entertainment")) {
            groubId = "8";
        }

            RequestParameters Parameters = new RequestParameters();
        Parameters.setGroupId(groubId);
        TableRequest tableRequest = new TableRequest("GET", "places", Parameters);
        String request = new Gson().toJson(tableRequest);

        Call<ArrayList<PlaceDataModel>> mCall = ourApiEndPoint.getPlacesService(tableRequest);
        mCall.enqueue(new Callback<ArrayList<PlaceDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PlaceDataModel>> call, Response<ArrayList<PlaceDataModel>> response) {
                if (response.isSuccessful()) {
                    if (!response.body().isEmpty()) {
                        //Log.d(TAG, "success---" + response.body().get(0).getName());
                        progressBar.setVisibility(View.GONE);
                        placesList = response.body();
                        adapter = new RecyclerAdapterSelecteItemList(placesList, PlanItemsList.this, typeToDisplay);

                        activityPlacesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                        activityPlacesRecyclerView.setAdapter(adapter);

                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<PlaceDataModel>> call, Throwable t) {

            }
        });
    }
}