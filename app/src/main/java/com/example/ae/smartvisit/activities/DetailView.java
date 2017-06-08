package com.example.ae.smartvisit.activities;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.app.ActionBar.LayoutParams;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.adapters.RecyclerAdapterPlacesWithPics;
import com.example.ae.smartvisit.adapters.RecyclerAdapterPictures;
import com.example.ae.smartvisit.infrastructure.MyMapView;
import com.example.ae.smartvisit.modules.PairOfDayAndPlace;
import com.example.ae.smartvisit.modules.PlaceDataModel;
import com.example.ae.smartvisit.modules.RequestParameters;
import com.example.ae.smartvisit.modules.SessionPlan;
import com.example.ae.smartvisit.modules.TableRequest;
import com.example.ae.smartvisit.rest.OurApiClient;
import com.example.ae.smartvisit.rest.TestApiEndPoint;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailView extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "@@@";
    private boolean isClicked = false;
    private RecyclerView picturesRecycler;
    private RecyclerView similarPlacesRecycler;
    private View contact_button;
    private View share_button;
    private Button favorite_button;
    private TextView addressTextView;
    private TextView overViewTextView;
    private RatingBar rateBar;
    private FloatingActionButton floatAddBtn;
    private PlaceDataModel placeDisplayed;
    private GoogleMap mMap;
    private MyMapView mMapView;

    private String parentActivity;
    private Boolean isAdded;
    private RecyclerAdapterPlacesWithPics adapterPlaces;

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
        overViewTextView = (TextView) findViewById(R.id.activity_detail_view_description);
        rateBar = (RatingBar) findViewById(R.id.activity_detail_view_rating_starts);

        String[] placeImagesUrls = placeDisplayed.getImageUrl().split(",");
        Picasso.with(this).load(placeImagesUrls[0]).placeholder(R.mipmap.ic_launcher).into(placeImage);

        addressTextView.setText(placeDisplayed.getAddress());
        overViewTextView.setText(placeDisplayed.getDescription());
        rateBar.setRating(placeDisplayed.getRate());
        MapsInitializer.initialize(this);
        mMapView = (MyMapView) findViewById(R.id.detail_map_view);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                //get the coordinates
                String[] latLng = placeDisplayed.getLocationCoordinates().split(",");
                double latitude = Double.parseDouble(latLng[0]);
                double longitude = Double.parseDouble(latLng[1]);

                // Add a marker in Pyramids and move the camera
                LatLng location = new LatLng(latitude, longitude);
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
            floatAddBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.accent)));
            isAdded = false;
            ArrayList<PairOfDayAndPlace> pairsInPlan = SessionPlan.getSessionPlanInstance().getPairOfData();

            for (int i = 0; i < pairsInPlan.size(); i++) {
                if (placeDisplayed.getName().equals(pairsInPlan.get(i).getPlace().get(0).getName())) {
                    isAdded = true;
                    floatAddBtn.setImageDrawable(getResources().getDrawable(R.drawable.delete_24));
                    floatAddBtn.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
                }
            }
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
        String[] urlPicsString = placeDisplayed.getImageUrl().split(", ");
        ArrayList<String> picsUrls = new ArrayList<>();
        for (int i = 0; i < urlPicsString.length; i++)
            picsUrls.add(urlPicsString[i]);

        picturesRecycler = (RecyclerView) findViewById(R.id.activity_detail_view_pics_recycler);
        RecyclerAdapterPictures adapterPictures = new RecyclerAdapterPictures(this, picsUrls);
        picturesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        picturesRecycler.setAdapter(adapterPictures);
        picturesRecycler.hasFixedSize();
    }


    private void setupSimilarPlacesRecycler() {

        adapterPlaces = new RecyclerAdapterPlacesWithPics(this);
        similarPlacesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        similarPlacesRecycler.setAdapter(adapterPlaces);
        similarPlacesRecycler.hasFixedSize();

        getPlacesList();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.activity_detail_view_contact_button) {
            // custom dialog
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_contacts);
            dialog.setTitle("Contacts info.");


            TextView phoneNumber = (TextView) dialog.findViewById(R.id.dialog_contact_num);
            TextView websiteLink = (TextView) dialog.findViewById(R.id.dialog_email_add);
            TextView socialLink = (TextView) dialog.findViewById(R.id.dialog_social_add);
            phoneNumber.setText(placeDisplayed.getContactNumber());
            websiteLink.setText(placeDisplayed.getWebsite());
            socialLink.setText(placeDisplayed.getSocial());

            dialog.show();

        } else if (id == R.id.activity_detail_view_share_button) {

            //Share intent when clicked
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, placeDisplayed);
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "Choose"));
        } else if (id == R.id.activity_detail_view_fav_button) {
            /*if (!isClicked) {
                isClicked = true;

            } else {
                isClicked = false;
                favorite_button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_favorite_black_24dp, 0, 0);
                favorite_button.setTextColor(getResources().getColor(R.color.primary_text));
                favorite_button.getBackground().clearColorFilter();
            }*/
            checkIfIsFavourite();

        } else if (id == R.id.activity_detail_add_floatbtn) {
            final SessionPlan workingSessionPlan = SessionPlan.getSessionPlanInstance();
            if (!isAdded) {
                int duration = CreatePlanActivity.getDuration(workingSessionPlan.getPlanStartDate(), workingSessionPlan.getPlanEndDate());

                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_choose_day);
                dialog.setTitle("Choose a day");
                dialog.show();

                LinearLayout container = (LinearLayout) dialog.findViewById(R.id.dialog_choose_day_container);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

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
                            List<PlaceDataModel> placeDataModels = new ArrayList<PlaceDataModel>();
                            placeDataModels.add(placeDisplayed);

                            PairOfDayAndPlace newPair = new PairOfDayAndPlace(dayBtn.getId(), placeDataModels);
                            workingSessionPlan.addPlaceToPlan(newPair);
                            floatAddBtn.setImageDrawable(getResources().getDrawable(R.drawable.delete_24));
                            floatAddBtn.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));

                            isAdded = true;
                            dialog.cancel();
                            Toast.makeText(DetailView.this, "Added to day " + dayBtn.getId(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    container.addView(dayBtn);
                }


                dialog.findViewById(R.id.dialog_choose_day_close_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        this);

                // set title
                alertDialogBuilder.setTitle("Remove place");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Sure you want to remove the place")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                // if this button is clicked, remove it from the plan
                                workingSessionPlan.removePlaceFromPlan(placeDisplayed.getName());
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    floatAddBtn.setImageDrawable(getDrawable(R.drawable.ic_add_black_24dp));
                                    floatAddBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.accent)));
                                }else {
                                    floatAddBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
                                    floatAddBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.accent)));
                                }
                                isAdded = false;
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        }
    }


    private void getPlacesList(){
        final TestApiEndPoint ourApiEndPoint = OurApiClient
                .getClient().create(TestApiEndPoint.class);

        RequestParameters Parameters = new RequestParameters();
        Parameters.setGroupId(String.valueOf(placeDisplayed.getGroupId()));
        TableRequest tableRequest = new TableRequest("GET", "places", Parameters);
        String request = new Gson().toJson(tableRequest);

        Call<ArrayList<PlaceDataModel>> mCall = ourApiEndPoint.getPlacesService(tableRequest);
        mCall.enqueue(new Callback<ArrayList<PlaceDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PlaceDataModel>> call, Response<ArrayList<PlaceDataModel>> response) {
                if (response.isSuccessful()) {
                    if(!response.body().isEmpty()) {
                        int position = -1;
                        ArrayList<PlaceDataModel> listOfPlaces = response.body();
                        for(int i = 0; i < listOfPlaces.size(); i++){
                            PlaceDataModel place = listOfPlaces.get(i);
                            if(place.getName().equals(placeDisplayed.getName()))
                                position = i;
                        }

                        if(position != -1)
                            listOfPlaces.remove(position);

                        adapterPlaces.addPlaces(listOfPlaces);
                        adapterPlaces.notifyDataSetChanged();
                    }
                } else {
                    Log.d(TAG, "Failed---");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PlaceDataModel>> call, Throwable t) {

            }
        });
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

    public void checkIfIsFavourite(){
        final TestApiEndPoint ourApiEndPoint = OurApiClient
                .getClient().create(TestApiEndPoint.class);

        RequestParameters parameters = new RequestParameters();
        parameters.setUser_id("700");
        parameters.setPlace_id(Integer.toString(placeDisplayed.getId()));
        TableRequest tableRequest = new TableRequest("GET", "favourite", parameters);
        String request = new Gson().toJson(tableRequest);

        Call<String> mCall = ourApiEndPoint.checkIfIsFavourite(tableRequest);
        mCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    if(response.body().isEmpty()){
                        addToFavourite();
                    }else{
                        String value = response.body().toString();
                        Toast.makeText(getApplicationContext(), "Will be deleted", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
     /*   Call<ArrayList<PlaceDataModel>> mCall = ourApiEndPoint.getPlacesService(tableRequest);
        mCall.enqueue(new Callback<ArrayList<PlaceDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PlaceDataModel>> call, Response<ArrayList<PlaceDataModel>> response) {
                if (response.isSuccessful()) {
                    if (!response.body().isEmpty()) {
                        //Log.d(TAG, "success---" + response.body().get(0).getName());
                        homeRecyclerProgressPar.setVisibility(View.GONE);
                        placesList = response.body();
                        adapter.addPlaces(placesList);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Log.d(TAG, "Failed---");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PlaceDataModel>> call, Throwable t) {

            }
        });
        */

    }

    void addToFavourite(){
        final TestApiEndPoint ourApiEndPoint = OurApiClient
                .getClient().create(TestApiEndPoint.class);

        RequestParameters parameters = new RequestParameters();
        parameters.setUser_id("700");
        parameters.setPlace_id(Integer.toString(placeDisplayed.getId()));
        TableRequest tableRequest = new TableRequest("POST", "favourite", parameters);
        String request = new Gson().toJson(tableRequest);

        Call<ResponseBody> mCall = ourApiEndPoint.sendData(tableRequest);
        mCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    favorite_button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_favorite_accent_24dp, 0, 0);
                    favorite_button.setTextColor(getResources().getColor(R.color.accent));
                    favorite_button.getBackground().setColorFilter(getResources().getColor(R.color.primary), PorterDuff.Mode.MULTIPLY);
                }else {
                    Toast.makeText(getApplicationContext(), "Error try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

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