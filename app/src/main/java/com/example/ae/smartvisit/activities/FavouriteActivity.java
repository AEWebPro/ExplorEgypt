package com.example.ae.smartvisit.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.adapters.RecyclerAdapterPlaceInCardView;
import com.example.ae.smartvisit.modules.PlaceDataModel;
import com.example.ae.smartvisit.modules.RequestParameters;
import com.example.ae.smartvisit.modules.TableRequest;
import com.example.ae.smartvisit.rest.OurApiClient;
import com.example.ae.smartvisit.rest.TestApiEndPoint;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        ButterKnife.bind(this);

        RecyclerAdapterPlaceInCardView adapter = new RecyclerAdapterPlaceInCardView(this);
        activityFavouriteRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        activityFavouriteRecyclerView.setAdapter(adapter);

        getPlacesList();
    }


    private void getPlacesList() {
        final TestApiEndPoint ourApiEndPoint = OurApiClient
                .getClient().create(TestApiEndPoint.class);

        RequestParameters Parameters = new RequestParameters();
        Parameters.setUser_id(application.getUser().getId());
        TableRequest tableRequest = new TableRequest("GET", "favourite", Parameters);
        String request = new Gson().toJson(tableRequest);

     /*   Call<ArrayList<PlaceDataModel>> mCall = ourApiEndPoint.getPlacesService(tableRequest);
        mCall.enqueue(new Callback<ArrayList<PlaceDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PlaceDataModel>> call, Response<ArrayList<PlaceDataModel>> response) {
                if (response.isSuccessful()) {
                    if (!response.body().isEmpty()) {
                        //Log.d(TAG, "success---" + response.body().get(0).getName());
                        homeRecyclerProgressPar.setVisibility(View.GONE);
                        placesList = response.body();
                        adapter.addPlaces(placesList);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Log.d(TAG, "Failed---");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PlaceDataModel>> call, Throwable t) {

            }
        });
        */
    }

}
