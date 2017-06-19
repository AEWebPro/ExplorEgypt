package com.example.ae.ExplorEgypt.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.adapters.RecyclerAdapterPlaceInCardView;
import com.example.ae.ExplorEgypt.infrastructure.HelperClass;
import com.example.ae.ExplorEgypt.modules.PlaceDataModel;
import com.example.ae.ExplorEgypt.modules.RequestParameters;
import com.example.ae.ExplorEgypt.modules.TableRequest;
import com.example.ae.ExplorEgypt.modules.User;
import com.example.ae.ExplorEgypt.rest.OurApiClient;
import com.example.ae.ExplorEgypt.rest.TestApiEndPoint;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteActivity extends BaseActivity {

    @Bind(R.id.activity_favourite_recycler_view)
    RecyclerView activityFavouriteRecyclerView;
    private RecyclerAdapterPlaceInCardView adapter;
    private ArrayList<PlaceDataModel> placesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Services");


        adapter = new RecyclerAdapterPlaceInCardView(this);
        activityFavouriteRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        activityFavouriteRecyclerView.setAdapter(adapter);

        getPlacesList();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }

    private void getPlacesList() {
        final TestApiEndPoint ourApiEndPoint = OurApiClient
                .getClient().create(TestApiEndPoint.class);

        RequestParameters Parameters = new RequestParameters();
        User activeUser = HelperClass.getUserFromPref(getApplicationContext(), "user");
        Parameters.setUser_id(activeUser.getId());
        TableRequest tableRequest = new TableRequest("GET", "favourite", Parameters);
        String request = new Gson().toJson(tableRequest);

        Call<ArrayList<PlaceDataModel>> mCall = ourApiEndPoint.getPlacesService(tableRequest);
        mCall.enqueue(new Callback<ArrayList<PlaceDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PlaceDataModel>> call, Response<ArrayList<PlaceDataModel>> response) {
                if (response.isSuccessful()) {
                    if (!response.body().isEmpty()) {
                        //Log.d(TAG, "success---" + response.body().get(0).getName());
                        placesList = response.body();
                        adapter.addPlaces(placesList);
                        adapter.notifyDataSetChanged();
                    } else {
                        //Log.d(TAG, "Failed---");
                        Toast.makeText(application, "No favorites add!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PlaceDataModel>> call, Throwable t) {

            }
        });
    }

}
