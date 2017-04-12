package com.example.ae.smartvisit.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.activities.HomeActivity;
import com.example.ae.smartvisit.adapters.RecyclerAdapterPlaceInCardView;
import com.example.ae.smartvisit.modules.PlaceDataModel;

import java.util.ArrayList;
import java.util.Random;

public class HotelListFragment extends BaseFragment{

    ArrayList<PlaceDataModel> placesList;
    private RecyclerAdapterPlaceInCardView adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_model_list, container,false);
        setHasOptionsMenu(true);
        String[] namesArray = getResources().getStringArray(R.array.Hotels_names);
        Random randomIndex = new Random();

        placesList = new ArrayList<>();
        for(int i = 0; i < 100; i++ ){
            placesList.add(i, new PlaceDataModel(namesArray[randomIndex.nextInt(6)],getString(R.string.temp_text),getString(R.string.hotel_image),"",i,"",""));
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_all_places_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        adapter = new RecyclerAdapterPlaceInCardView(getContext());
        adapter.addPlaces(placesList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        MenuItem item = menu.findItem(R.id.activity_home_search);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Enter name");

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).manageSpinner(false);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                ((HomeActivity)getActivity()).manageSpinner(true);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return filteredModelList;
    }
}
