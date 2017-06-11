package com.example.ae.ExplorEgypt.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.infrastructure.MyMapView;
import com.example.ae.ExplorEgypt.modules.PlaceDataModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;


public class EmbassyInformation extends BaseActivity {

    @Bind(R.id.activity_embassy_info_contact_no)
    TextView activityEmbassyInfoContactNo;
    @Bind(R.id.activity_embassy_info_address)
    TextView activityEmbassyInfoAddress;

    private GoogleMap mMap;
    private MyMapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embassy_info);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        final PlaceDataModel placeDataModel = getIntent().getParcelableExtra("embassy");
        activityEmbassyInfoContactNo.setText("Contact number: " + placeDataModel.getContactNumber());
        activityEmbassyInfoAddress.setText("Address : " + placeDataModel.getAddress());



        MapsInitializer.initialize(this);
        mMapView = (MyMapView) findViewById(R.id.embassy_map_view);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                //get the coordinates
                String[] latLng = placeDataModel.getLocationCoordinates().split(",");
                double latitude = Double.parseDouble(latLng[0]);
                double longitude = Double.parseDouble(latLng[1]);

                // Add a marker in Pyramids and move the camera
                LatLng location = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(location).title(placeDataModel.getName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                mMapView.onResume();

            }
        });
        getSupportActionBar().setTitle(placeDataModel.getName());
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
