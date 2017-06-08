package com.example.ae.smartvisit.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.fragments.BaseFragment;
import com.example.ae.smartvisit.fragments.PlacesListFragment;


public class HomeCatListActivity extends BaseActivity {

    private BaseFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_list_activity);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        String catToDisplay = getIntent().getStringExtra("category");
        //displayFragment(catToDisplay);

        Bundle typeBundle = new Bundle();
        typeBundle.putString("type",catToDisplay);

        fragment = new PlacesListFragment();
        fragment.setArguments(typeBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.cat_list_container, fragment).commit();

        getSupportActionBar().setTitle(catToDisplay);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);

        return true;
    }
}
