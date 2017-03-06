package com.example.ae.smartvisit.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.fragments.HotelListFragment;
import com.example.ae.smartvisit.fragments.TouristicPlacesFragment;

public class HomeActivity extends BaseActivity {
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        getSupportActionBar().setTitle("Home");

        fragment = new HotelListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_home_fragment_container, fragment).commit();
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
        if (id == R.id.activity_home_sort) {
            if (fragment.getClass().getSimpleName().equals(HotelListFragment.class.getSimpleName())) {
                fragment = new TouristicPlacesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_home_fragment_container,
                        fragment).commit();
                return true;
            } else {
                fragment = new HotelListFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_home_fragment_container,
                        fragment).commit();
                return true;
            }
        }
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }
}