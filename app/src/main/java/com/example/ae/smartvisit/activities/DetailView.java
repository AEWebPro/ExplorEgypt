package com.example.ae.smartvisit.activities;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.adapters.RecyclerAdapterHome;
import com.example.ae.smartvisit.adapters.RecyclerAdapterPictures;
import com.example.ae.smartvisit.modules.PlaceDataModel;

import java.util.ArrayList;

public class DetailView extends AppCompatActivity implements View.OnClickListener {

    private boolean isClicked = false;
    private RecyclerView picturesRecycler;
    private RecyclerView similarPlacesRecycler;
    private FloatingActionButton favActionButton;
    private View map_button;
    private View contact_button;
    private View share_button;
    private TextView addressTextView;
    private TextView titleTextView;
    private PlaceDataModel placeDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);


        ImageView placeImage = (ImageView) findViewById(R.id.placeImage);
        favActionButton = (FloatingActionButton) findViewById(R.id.detail_activity_action_button);
        map_button = findViewById(R.id.activity_detail_view_map_button);
        contact_button = findViewById(R.id.activity_detail_view_contact_button);
        share_button = findViewById(R.id.activity_detail_view_share_button);
        similarPlacesRecycler = (RecyclerView) findViewById(R.id.activity_detail_view_similar_places_recycler);
        addressTextView = (TextView) findViewById(R.id.activity_detail_view_address);
        titleTextView = (TextView) findViewById(R.id.activity_detail_view_place_title);

        map_button.setOnClickListener(this);
        contact_button.setOnClickListener(this);
        share_button.setOnClickListener(this);


        favActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClicked){
                    isClicked = true;
                    favActionButton.setImageResource(R.drawable.fav_icon);
                    favActionButton.setColorFilter(Color.CYAN);
                    }else{
                    isClicked = false;
                    favActionButton.setImageResource(R.drawable.unfav_icon);
                    favActionButton.setColorFilter(null);
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
        placeDisplayed = (PlaceDataModel) intentPassed.getParcelableExtra("placeClicked");
        CollapsingToolbarLayout layout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        layout.setTitle(placeDisplayed.getName());
        setupPicsRecycler();
        setupSimilarPlacesRecycler();
    }

    private void setupPicsRecycler(){
        ArrayList<String> picsUrls = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        picsUrls.add("http://i.imgur.com/DvpvklR.png");

        picturesRecycler = (RecyclerView) findViewById(R.id.activity_detail_view_pics_recycler);
        RecyclerAdapterPictures adapterPictures = new RecyclerAdapterPictures(this, picsUrls);
        picturesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
        picturesRecycler.setAdapter(adapterPictures);
        picturesRecycler.hasFixedSize();
    }


    private void setupSimilarPlacesRecycler(){
        ArrayList<PlaceDataModel> placesRelatedList = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            placesRelatedList.add(new PlaceDataModel("place name " + Integer.toString(i),"","","",i,"",""));

        RecyclerAdapterHome adapterPlaces = new RecyclerAdapterHome(this);
        adapterPlaces.addPlaces(placesRelatedList);
        similarPlacesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
        similarPlacesRecycler.setAdapter(adapterPlaces);
        similarPlacesRecycler.hasFixedSize();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.activity_detail_view_map_button){
            Toast.makeText(this, "Open the Location on Map ", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.activity_detail_view_contact_button){
            // custom dialog
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_contacts);
            dialog.setTitle("Contacts info.");
            dialog.show();

        }else if(id == R.id.activity_detail_view_share_button){

            //Share intent when clicked
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, placeDisplayed);
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "Choose"));
        }
    }
 }

