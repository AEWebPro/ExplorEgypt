package com.example.ae.smartvisit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.adapters.RecyclerAdapterCategories;
import com.example.ae.smartvisit.modules.CategoryItem;

import java.util.ArrayList;

/**
 * Created by ahmed.E on 6/2/2017.
 */

public class CategoriesFragment extends BaseFragment {


    private RecyclerView recyclerView;

    public static CategoriesFragment newInstance(){
        return new CategoriesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        setHasOptionsMenu(false);

        recyclerView = (RecyclerView) view.findViewById(R.id.categories_recycle_view);
        setupCatRecycler();

        return view;
    }

    void setupCatRecycler(){
        ArrayList<CategoryItem> categoryItems = new ArrayList<>();

        categoryItems.add(new CategoryItem(R.drawable.restaurant, "Restaurant"));
        categoryItems.add(new CategoryItem(R.drawable.hotel, "Hotel"));
        categoryItems.add(new CategoryItem(R.drawable.pharaonic, "Pharaonic"));
        categoryItems.add(new CategoryItem(R.drawable.islamic, "Islamic"));
        categoryItems.add(new CategoryItem(R.drawable.nature_reserve, "Natural Parks"));
        categoryItems.add(new CategoryItem(R.drawable.beach, "Beaches"));
        categoryItems.add(new CategoryItem(R.drawable.nightclub, "Nightclubs"));
        categoryItems.add(new CategoryItem(R.drawable.entertainment, "Entertainment"));

        RecyclerAdapterCategories adapterCategories = new RecyclerAdapterCategories(getActivity(), categoryItems, 0);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapterCategories);
    }
}
