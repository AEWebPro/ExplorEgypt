package com.example.ae.smartvisit.activities;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.app.ActionBar.LayoutParams;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.adapters.RecyclerAdapterPlacesWithPics;
import com.example.ae.smartvisit.adapters.RecyclerAdapterPictures;
import com.example.ae.smartvisit.infrastructure.MyMapView;
import com.example.ae.smartvisit.modules.PairOfDayAndPlace;
import com.example.ae.smartvisit.modules.PlaceDataModel;
import com.example.ae.smartvisit.modules.Plan;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailView extends AppCompatActivity implements View.OnClickListener {

    private boolean isClicked = false;
    private RecyclerView picturesRecycler;
    private RecyclerView similarPlacesRecycler;
    private View contact_button;
    private View share_button;
    private Button favorite_button;
    private TextView addressTextView;
    private TextView titleTextView;
    private FloatingActionButton floatAddBtn;
    private PlaceDataModel placeDisplayed;
    private GoogleMap mMap;
    private MyMapView mMapView;

    private String parentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Intent intentPassed = getIntent();
        placeDisplayed = (PlaceDataModel) intentPassed.getParcelableExtra("placeClicked");

        CollapsingToolbarLayout layout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        layout.setTitle(placeDisplayed.getName());

        ImageView placeImage = (ImageView) findViewById(R.id.placeImage);
        contact_button = findViewById(R.id.activity_detail_view_contact_button);
        share_button = findViewById(R.id.activity_detail_view_share_button);
        favorite_button = (Button) findViewById(R.id.activity_detail_view_fav_button);
        similarPlacesRecycler = (RecyclerView) findViewById(R.id.activity_detail_view_similar_places_recycler);
        addressTextView = (TextView) findViewById(R.id.activity_detail_view_address);
        floatAddBtn = (FloatingActionButton) findViewById(R.id.activity_detail_add_floatbtn);

        Picasso.with(this).load(placeDisplayed.getImageUrl()).placeholder(R.mipmap.ic_launcher).into(placeImage);
        //Picasso.with(this).load(getString(R.string.pyramids_image)).placeholder(R.mipmap.ic_launcher).into( placeImage);


        MapsInitializer.initialize(this);
        mMapView = (MyMapView) findViewById(R.id.detail_map_view);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                // Add a marker in Pyramids and move the camera
                LatLng location = new LatLng(29.978919, 31.134891);
                mMap.addMarker(new MarkerOptions().position(location).title("Pyramids of Giza"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                mMapView.onResume();

            }
        });
        contact_button.setOnClickListener(this);
        share_button.setOnClickListener(this);
        favorite_button.setOnClickListener(this);

        parentActivity = intentPassed.getStringExtra("parent_activity");
        //check the parent ADD mode or Display mode
        if (parentActivity.equals("HOME")) {
            floatAddBtn.hide();
        } else {
            floatAddBtn.setEnabled(true);
        }

        floatAddBtn.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setupPicsRecycler();
        setupSimilarPlacesRecycler();
        picturesRecycler.setNestedScrollingEnabled(false);
        similarPlacesRecycler.setNestedScrollingEnabled(false);
    }

    private void setupPicsRecycler() {
        String[] urlPicsString = getResources().getStringArray(R.array.pyramids_array_images);
        ArrayList<String> picsUrls = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            picsUrls.add(urlPicsString[i]);

        picturesRecycler = (RecyclerView) findViewById(R.id.activity_detail_view_pics_recycler);
        RecyclerAdapterPictures adapterPictures = new RecyclerAdapterPictures(this, picsUrls);
        picturesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        picturesRecycler.setAdapter(adapterPictures);
        picturesRecycler.hasFixedSize();
    }


    private void setupSimilarPlacesRecycler() {
        ArrayList<PlaceDataModel> placesRelatedList = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            placesRelatedList.add(new PlaceDataModel("place name " + Integer.toString(i), "", "", "", i, "", ""));

        RecyclerAdapterPlacesWithPics adapterPlaces = new RecyclerAdapterPlacesWithPics(this);
        adapterPlaces.addPlaces(placesRelatedList);
        similarPlacesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        similarPlacesRecycler.setAdapter(adapterPlaces);
        similarPlacesRecycler.hasFixedSize();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.activity_detail_view_contact_button) {
            // custom dialog
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_contacts);
            dialog.setTitle("Contacts info.");
            dialog.show();

        } else if (id == R.id.activity_detail_view_share_button) {

            //Share intent when clicked
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, placeDisplayed);
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "Choose"));
        } else if (id == R.id.activity_detail_view_fav_button) {
            if (!isClicked) {
                isClicked = true;
                favorite_button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_favorite_accent_24dp, 0, 0);
                favorite_button.setTextColor(getResources().getColor(R.color.accent));
                favorite_button.getBackground().setColorFilter(getResources().getColor(R.color.primary), PorterDuff.Mode.MULTIPLY);
            } else {
                isClicked = false;
                favorite_button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_favorite_black_24dp, 0, 0);
                favorite_button.setTextColor(getResources().getColor(R.color.primary_text));
                favorite_button.getBackground().clearColorFilter();
            }
        } else if (id == R.id.activity_detail_add_floatbtn) {
            final Plan workingPlan = Plan.getPlanInstance();
            int duration = CreatePlanActivity.getDuration(workingPlan.getPlanStartDate(), workingPlan.getPlanEndDate());

            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_choose_day);
            dialog.setTitle("Choose a day");
            dialog.show();

            LinearLayout container = (LinearLayout) dialog.findViewById(R.id.dialog_choose_day_container);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            /*LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    65,40);*/

            for (int i = 0; i < duration; i++) {


                final Button dayBtn = new Button(this);
                dayBtn.setId(i + 1);
                dayBtn.setText("Day " + (i + 1));
                dayBtn.setPadding(2, 4, 6, 4);
                dayBtn.setLayoutParams(params);
                dayBtn.setBackground(getResources().getDrawable(R.drawable.btn_circle));

                dayBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PairOfDayAndPlace newPair = new PairOfDayAndPlace(dayBtn.getId(), placeDisplayed);
                        workingPlan.addPlaceToPlan(newPair);
                        dialog.cancel();
                        Toast.makeText(DetailView.this, "Added to day " + dayBtn.getId(), Toast.LENGTH_SHORT).show();
                    }
                });
                container.addView(dayBtn);
            }

         /*   if (workingPlan.getPairOfData() != null) {
                for (PairOfDayAndPlace pair : workingPlan.getPairOfData()) {
                    Log.e("@@@@", pair.getPlace().getName());
                }
            }*/

            dialog.findViewById(R.id.dialog_choose_day_close_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //TODO: try to make the shared element return works
            supportFinishAfterTransition();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }
}

