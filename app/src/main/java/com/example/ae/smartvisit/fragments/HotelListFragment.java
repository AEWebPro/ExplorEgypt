package com.example.ae.smartvisit.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.adapters.RecyclerAdapterHome;
import com.example.ae.smartvisit.modules.PlaceDataModel;

import java.util.ArrayList;

public class HotelListFragment extends BaseFragment{

    ArrayList<PlaceDataModel> placesList;
    private RecyclerAdapterHome adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_model_list, container,false);
        setHasOptionsMenu(true);

        placesList = new ArrayList<>();
        for(int i = 0; i < 100; i++ ){
            placesList.add(i, new PlaceDataModel("place name " + Integer.toString(i),"","","",i,"",""));
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_hotel_places_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(application, 2));

        adapter = new RecyclerAdapterHome(getActivity());
        adapter.addPlaces(placesList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        MenuItem item = menu.findItem(R.id.activity_home_search);
        SearchView searchView = (SearchView) item.getActionView();

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
        adapter = new RecyclerAdapterHome(getActivity());
        adapter.addPlaces(filteredModelList);
        recyclerView.setLayoutManager(new GridLayoutManager(application, 2));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return filteredModelList;
    }
}
