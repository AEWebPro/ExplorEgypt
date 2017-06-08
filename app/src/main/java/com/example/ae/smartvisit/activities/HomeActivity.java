package com.example.ae.smartvisit.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.adapters.CustomPageAdapter;
import com.example.ae.smartvisit.fragments.CategoriesFragment;
import com.example.ae.smartvisit.fragments.ExploreFragment;

public class HomeActivity extends BaseActivity {
    private Fragment fragment;
    private ViewPager viewPager;
    // private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        getSupportActionBar().setTitle("Home");

        /*//get spinner
        spinner = (Spinner) findViewById(R.id.places_spinner);

        // set Spinner Adapter
        final ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.drop_down_list, R.layout.spinner_list_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        //Click
        spinner.setOnItemSelectedListener(this);*/


        //fragment = new PlacesListFragment();
        //getSupportFragmentManager().beginTransaction().replace(R.id.activity_home_fragment_container, fragment).commit();


        viewPager = (ViewPager) findViewById(R.id.home_viewPager);
        setupViewPager();
        //viewPager.setAdapter(new CustomPageAdapter());
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

/*
    public void manageSpinner(boolean state){
        if(!state){
            toolbar.removeView(spinner);
        }else {
            toolbar.addView(spinner);
        }
    }*/

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);

        return true;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }
/*
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        switch (position) {
            case 0:
                if (fragment.getClass().getSimpleName().equals(PlacesListFragment.class.getSimpleName())) {
                    break;
                } else {
                    fragment = new PlacesListFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.activity_home_fragment_container, fragment).commit();
                    break;
                }
            case 1:
                if (fragment.getClass().getSimpleName().equals(EntertainmentPlacesFragment.class.getSimpleName())) {
                    break;
                } else {
                    fragment = new EntertainmentPlacesFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.activity_home_fragment_container, fragment).commit();
                    break;
                }
            case 2:
                if (fragment.getClass().getSimpleName().equals(RestaurantsListFragment.class.getSimpleName())) {
                    break;
                } else {
                    fragment = new RestaurantsListFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.activity_home_fragment_container, fragment).commit();
                    break;
                }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
}