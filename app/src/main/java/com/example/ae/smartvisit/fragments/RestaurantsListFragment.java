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
import com.example.ae.smartvisit.activities.HomeActivity;
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

public class RestaurantsListFragment extends BaseFragment {
    private static final String TAG = "@@@";
    ArrayList<PlaceDataModel> placesList;
    private ProgressBar homeRecyclerProgressPar;
    private RecyclerView recyclerView;
    private RecyclerAdapterPlaceInCardView adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_model_list, container, false);
        setHasOptionsMenu(true);

        homeRecyclerProgressPar = (ProgressBar) view.findViewById(R.id.home_recycler_progressPar);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_all_places_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new RecyclerAdapterPlaceInCardView(getContext());
        recyclerView.setAdapter(adapter);

        getPlacesList();

        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        MenuItem item = menu.findItem(R.id.activity_home_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Place name");
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).manageSpinner(false);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                ((HomeActivity) getActivity()).manageSpinner(true);
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
        } else {
            return;
        }
        super.onPrepareOptionsMenu(menu);
    }

    private ArrayList<PlaceDataModel> filter(ArrayList<PlaceDataModel> models, String query) {
        query = query.toLowerCase();

        final ArrayList<PlaceDataModel> filteredModelList = new ArrayList<>();
        for (PlaceDataModel model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);

            }
        }
        adapter = new RecyclerAdapterPlaceInCardView(getActivity());
        adapter.addPlaces(filteredModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return filteredModelList;
    }


    private void getPlacesList() {
        final TestApiEndPoint ourApiEndPoint = OurApiClient
                .getClient().create(TestApiEndPoint.class);

        RequestParameters Parameters = new RequestParameters();
        Parameters.setGroupId("3");
        TableRequest tableRequest = new TableRequest("GET", "places", Parameters);
        String request = new Gson().toJson(tableRequest);

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
