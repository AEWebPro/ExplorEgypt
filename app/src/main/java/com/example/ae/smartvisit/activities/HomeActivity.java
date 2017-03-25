package com.example.ae.smartvisit.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.fragments.HotelListFragment;
import com.example.ae.smartvisit.fragments.RestaurantsListFragment;
import com.example.ae.smartvisit.fragments.TouristicPlacesFragment;

public class HomeActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    private Fragment fragment;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        getSupportActionBar().setTitle(null);

        //get spinner
        spinner = (Spinner) findViewById(R.id.places_spinner);

        // set Spinner Adapter
        final ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.drop_down_list, R.layout.spinner_list_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        //Click
        spinner.setOnItemSelectedListener(this);

        fragment = new HotelListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_home_fragment_container, fragment).commit();

    }

    public void manageSpinner(boolean state){
        if(!state){
            toolbar.removeView(spinner);
        }else {
            toolbar.addView(spinner);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);

        return true;
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        switch (position) {
            case 0:
                if (fragment.getClass().getSimpleName().equals(HotelListFragment.class.getSimpleName())) {
                    break;
                } else {
                    fragment = new HotelListFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.activity_home_fragment_container, fragment).commit();
                    break;
                }
            case 1:
                if (fragment.getClass().getSimpleName().equals(TouristicPlacesFragment.class.getSimpleName())) {
                    break;
                } else {
                    fragment = new TouristicPlacesFragment();
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

    }
}