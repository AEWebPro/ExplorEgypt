package com.example.ae.smartvisit.activities;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.modules.PlaceDataModel;

public class DetailView extends AppCompatActivity{

    private boolean isClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);


        ImageView placeImage = (ImageView) findViewById(R.id.placeImage);
        final ImageButton favButton = (ImageButton) findViewById(R.id.favButton);
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClicked){
                    isClicked = true;
                    favButton.setColorFilter(Color.CYAN);
                    Toast.makeText(getApplication(), "is favourite", Toast.LENGTH_SHORT).show();
                }else{
                    isClicked = false;
                    favButton.setColorFilter(null);
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intentPassed = getIntent();
        PlaceDataModel placeDisplayed = (PlaceDataModel) intentPassed.getParcelableExtra("placeClicked");
        CollapsingToolbarLayout layout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        layout.setTitle(placeDisplayed.getName());
    }

}
