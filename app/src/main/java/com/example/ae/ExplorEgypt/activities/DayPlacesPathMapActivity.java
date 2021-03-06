package com.example.ae.ExplorEgypt.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.modules.PairOfDayAndPlace;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.List;

public class DayPlacesPathMapActivity extends FragmentActivity implements OnMapReadyCallback, RoutingListener {

    private GoogleMap mMap;

    private List<Polyline> polylines;
   private ArrayList<LatLng> placesLocation;
    private ArrayList<String> placesTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_places_path_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        Intent recievedIntent = getIntent();
        PairOfDayAndPlace pairRecived = recievedIntent.getParcelableExtra("pair_map");

        placesTitles = new ArrayList<>();
        placesLocation = new ArrayList<>();
        for  (int i = 0; i < pairRecived.getPlace().size(); i++){
            placesTitles.add(pairRecived.getPlace().get(i).getName());
            String[] latLng = pairRecived.getPlace().get(i).getLocationCoordinates().split(",");
            double latitude = Double.parseDouble(latLng[0]);
            double longitude = Double.parseDouble(latLng[1]);
            placesLocation.add(new LatLng(latitude, longitude));
        }

        polylines = new ArrayList<>();


        /*destTitle = "";
        startTitle = "Pyramids of Giza";
        waypointTitle = "Carfore store";

        start = new LatLng(29.978919, 31.134891);
        waypoint = new LatLng(29.987751, 31.141250);
        end = new LatLng(29.975050, 31.140986);*/

        Routing routing = new Routing.Builder()
                .travelMode(Routing.TravelMode.WALKING)
                .withListener(this)
                .waypoints(placesLocation.get(0), placesLocation.get(1), placesLocation.get(2))
                .build();
        routing.execute();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(29.976316, 30.948149);
        mMap.addMarker(new MarkerOptions().position(sydney).title(" October 6 University"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onRoutingFailure(RouteException e) {
        Toast.makeText(this, "Cannot make a path for the selected places check the city!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {

        CameraUpdate center = CameraUpdateFactory.newLatLng(placesLocation.get(0));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);


        if(polylines.size()>0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int i = 0; i <route.size(); i++) {

            //In case of more than 5 alternative routes

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = mMap.addPolyline(polyOptions);
            polylines.add(polyline);

            Toast.makeText(getApplicationContext(),"Route "+ (i+1) +": distance - "+ route.get(i).getDistanceValue()+": duration - "+ route.get(i).getDurationValue(),Toast.LENGTH_SHORT).show();
        }

        addMarker(placesTitles.get(0), placesLocation.get(0));
        addMarker(placesTitles.get(1), placesLocation.get(1));
        addMarker(placesTitles.get(2), placesLocation.get(2));


    }

    @Override
    public void onRoutingCancelled() {

    }

    // create a bubble icon and add it to the marker then the map
    private void addMarker(String title, LatLng position){

        TextView text = new TextView(this);
        text.setText(title);
        text.setTextColor(Color.CYAN);
        IconGenerator generator = new IconGenerator(this);
        generator.setBackground(this.getResources().getDrawable(R.drawable.amu_bubble_mask));
        generator.setContentView(text);
        Bitmap icon = generator.makeIcon();

        // Start marker
        MarkerOptions options = new MarkerOptions();
        options.position(position);
        options.icon(BitmapDescriptorFactory.fromBitmap(icon));
        mMap.addMarker(options);

    }
}
