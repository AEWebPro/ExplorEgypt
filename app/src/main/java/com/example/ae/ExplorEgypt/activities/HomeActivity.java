package com.example.ae.ExplorEgypt.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.adapters.CustomPageAdapter;
import com.example.ae.ExplorEgypt.fragments.CategoriesFragment;
import com.example.ae.ExplorEgypt.fragments.ExploreFragment;

public class HomeActivity extends BaseActivity {
    private Fragment fragment;
    private ViewPager viewPager;
    // private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        getSupportActionBar().setTitle("Home");



        viewPager = (ViewPager) findViewById(R.id.home_viewPager);
        setupViewPager();
    }

    //setup the view pager add the fragments and titles
    private void setupViewPager() {

        CategoriesFragment categoriesFragment = new CategoriesFragment();
        ExploreFragment exploreFragment = new ExploreFragment();

        CustomPageAdapter customPageAdapter = new CustomPageAdapter(getSupportFragmentManager());
        customPageAdapter.addFragment(exploreFragment, "Explore");
        customPageAdapter.addFragment(categoriesFragment , "Categories");


        viewPager.setAdapter(customPageAdapter);
        TabLayout homeTabLayout = (TabLayout) findViewById(R.id.home_tabLayout);
        homeTabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }

}