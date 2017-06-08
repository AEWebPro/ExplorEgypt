package com.example.ae.smartvisit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.adapters.RecyclerAdapterPlaceInCardView;
import com.example.ae.smartvisit.modules.PlaceDataModel;
import com.example.ae.smartvisit.modules.RequestParameters;
import com.example.ae.smartvisit.modules.TableRequest;
import com.example.ae.smartvisit.rest.OurApiClient;
import com.example.ae.smartvisit.rest.TestApiEndPoint;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed.E on 6/2/2017.
 */

public class ExploreFragment extends BaseFragment {

    private static final String TAG = "@@@@@@";
    ArrayList<PlaceDataModel> placesList;

    private ProgressBar homeRecyclerProgressPar;
    private RecyclerAdapterPlaceInCardView adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_fragment, container, false);

        homeRecyclerProgressPar = (ProgressBar) view.findViewById(R.id.explore_recycler_progressPar);
        recyclerView = (RecyclerView) view.findViewById(R.id.explore_places_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new RecyclerAdapterPlaceInCardView(getContext());
        recyclerView.setAdapter(adapter);

        getPlacesList();

        return view;
    }

    private void getPlacesList() {
        final TestApiEndPoint ourApiEndPoint = OurApiClient
                .getClient().create(TestApiEndPoint.class);

        RequestParameters Parameters = new RequestParameters();
        TableRequest tableRequest = new TableRequest("GET", "places", Parameters);
        String request = new Gson().toJson(tableRequest);

        /*Call<ArrayList<String>> testCall = ourApiEndPoint.checkIfIsAvailabe(tableRequest);
        testCall.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                if(response.isSuccessful()){
                    if(!response.body().isEmpty())
                    {
                        String data = response.body().toString();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {

            }
        });*/
        Call<ArrayList<PlaceDataModel>> mCall = ourApiEndPoint.getPlacesService(tableRequest);
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
    }



}
