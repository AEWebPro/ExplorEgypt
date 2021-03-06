package com.example.ae.ExplorEgypt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.map.HospitalsMapsActivity;

public class Services extends BaseActivity implements View.OnClickListener {

    ImageButton img1, img2, img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        getSupportActionBar().setTitle("Services");

        img1 = (ImageButton) findViewById(R.id.img1);
        img2 = (ImageButton) findViewById(R.id.img2);
        img3 = (ImageButton) findViewById(R.id.img3);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == img1) {
            Intent i = new Intent(Services.this, Embassy.class);
            startActivity(i);
        } else if (v == img2) {
            Intent i = new Intent(Services.this, HospitalsMapsActivity.class);
            startActivity(i);
        } else if (v == img3) {
            Intent i = new Intent(Services.this, CurrencyActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }
}
