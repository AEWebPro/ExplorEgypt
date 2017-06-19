package com.example.ae.ExplorEgypt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.adapters.RecyclerAdapterYourPlans;
import com.example.ae.ExplorEgypt.infrastructure.RecyclerClick_Listener;
import com.example.ae.ExplorEgypt.infrastructure.RecyclerTouchListener;
import com.example.ae.ExplorEgypt.infrastructure.Toolbar_ActionMode_Callback;
import com.example.ae.ExplorEgypt.modules.PairOfDayAndPlace;
import com.example.ae.ExplorEgypt.modules.PlaceDataModel;
import com.example.ae.ExplorEgypt.modules.Plan;
import com.google.gson.Gson;

import java.util.ArrayList;

public class YourPlanes extends BaseActivity {

    //Action Mode for toolbar
    private ActionMode mActionMode;
    private ArrayList<Plan> myPlans = new ArrayList<>();
    private RecyclerAdapterYourPlans adapter;
    private RecyclerView plansList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_planes);

        getSupportActionBar().setTitle("Your Plans");

        ArrayList<PlaceDataModel> d1place1 = new ArrayList<>();
        /*d1place1.add(new PlaceDataModel("Guardian Guest House",
                "A block from the Pyramids of Giza complex, this modest guesthouse is also a 10-minute walk from the ancient Khufu ship, now a museum." +
                        "The simple, air-conditioned rooms offer private bathrooms, free Wi-Fi and views of the Pyramids." +
                        " Room service is available.Freebies include breakfast tea, coffee and mineral water. " +
                        "There's also a terrace overlooking the Pyramids and Sphinx." ,
                "http://ucd.hwstatic.com/propertyimages/6/69554/1.jpg , https://media-cdn.tripadvisor.com/media/photo-s/09/fd/2d/88/guardian-guest-house.jpg",
                "1 Abou al hool street, Giza, Egypt, Nazlet el Samman 12561", "Giza",
                "29.975066,31.141047", "", "", "",0));*/

        ArrayList<PlaceDataModel> listOfPlaces1 = new ArrayList<>();
        listOfPlaces1.add(new PlaceDataModel("Hotel",getString(R.string.temp_text),getString(R.string.hotel_image),"","","29.967803, 31.129438","","","",0));

        ArrayList<PlaceDataModel> listOfPlaces2 = new ArrayList<>();
        listOfPlaces2.add(new PlaceDataModel("Pyramid",getString(R.string.temp_text),getString(R.string.pyramids_image),"","","29.967803, 31.129438","2","","",0));

        ArrayList<PairOfDayAndPlace> placesInPlan = new ArrayList<>();
        ArrayList<PairOfDayAndPlace> placesInPlan2 = new ArrayList<>();

        placesInPlan.add(new PairOfDayAndPlace(3,listOfPlaces1));

        placesInPlan.add(new PairOfDayAndPlace(1,listOfPlaces1));
        placesInPlan.add(new PairOfDayAndPlace(2,listOfPlaces2));
        placesInPlan.add(new PairOfDayAndPlace(2,listOfPlaces2));
        placesInPlan.add(new PairOfDayAndPlace(2,listOfPlaces1));

        placesInPlan.add(new PairOfDayAndPlace(1,listOfPlaces1));
        placesInPlan.add(new PairOfDayAndPlace(1,listOfPlaces1));
        placesInPlan.add(new PairOfDayAndPlace(1,listOfPlaces1));
        placesInPlan.add(new PairOfDayAndPlace(1,listOfPlaces1));

        placesInPlan2.add(new PairOfDayAndPlace(2,listOfPlaces2));
        placesInPlan2.add(new PairOfDayAndPlace(2,listOfPlaces2));
        placesInPlan2.add(new PairOfDayAndPlace(3,listOfPlaces1));

        placesInPlan2.add(new PairOfDayAndPlace(1,listOfPlaces1));
        placesInPlan2.add(new PairOfDayAndPlace(3,listOfPlaces1));
        placesInPlan2.add(new PairOfDayAndPlace(1,listOfPlaces1));

        myPlans.add(new Plan("Visit egypt", "22/5/2020","30/5/2020",false,placesInPlan));
        myPlans.add(new Plan("Giza is near", "1/8/2020","30/9/2020",true,placesInPlan2));

        String planJson = new Gson().toJson(myPlans);

        plansList = (RecyclerView) findViewById(R.id.activity_your_plans_recyclerView);
        plansList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerAdapterYourPlans(myPlans, this);
        plansList.setAdapter(adapter);

        implementRecyclerViewClickListeners();

        if(!isNetworkAvailable()){
            Toast.makeText(application, "No connection!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }


    //Implement item click and long click over recycler view
    private void implementRecyclerViewClickListeners() {
        plansList.
                addOnItemTouchListener(new RecyclerTouchListener(this, plansList, new RecyclerClick_Listener() {
                    @Override
                    public void onClick(View view, int position) {
                        //If ActionMode not null select item
                        if (mActionMode != null)
                            onListItemSelect(position);
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        //Select item on long click
                        onListItemSelect(position);
                    }
                }));
    }

    //List item select method
    private void onListItemSelect(int position) {
        adapter.toggleSelection(position);//Toggle the selection

        boolean hasCheckedItems = adapter.getSelectedCount() > 0;//Check if any items are already selected or not

        if (hasCheckedItems && mActionMode == null)
            // there are some selected items, start the actionMode
            mActionMode = startSupportActionMode(new Toolbar_ActionMode_Callback( this,adapter, myPlans, this));
        else if (!hasCheckedItems && mActionMode != null) {
            // there no selected items, finish the actionMode
            mActionMode.finish();
            adapter.removeSelection();
        }
            //for the edit option if selected more than one item
        if(mActionMode != null) {
            if (adapter.getSelectedCount() > 1) {
                mActionMode.getMenu().findItem(R.id.activity_your_plans_menu_edit).setVisible(false);
            } else {
                mActionMode.getMenu().findItem(R.id.activity_your_plans_menu_edit).setVisible(true);
            }
        }
        if (mActionMode != null)
            //set action mode title on item selection
            mActionMode.setTitle(String.valueOf(adapter
                    .getSelectedCount()) + " selected");
    }
    //Set action mode null after use
    public void setNullToActionMode() {
        if (mActionMode != null)
            mActionMode = null;
    }

    //Delete selected rows
    public void deleteRows() {
        SparseBooleanArray selected = adapter
                .getSelectedIds();//Get selected ids

        //Loop all selected ids
        for (int i = (selected.size() - 1); i >= 0; i--) {
            if (selected.valueAt(i)) {
                //If current id is selected remove the item via key
                myPlans.remove(selected.keyAt(i));
                //TODO: update the database with the deleted plan
                adapter.notifyDataSetChanged();//notify adapter

            }
        }
        Toast.makeText(this, selected.size() + " item deleted.", Toast.LENGTH_SHORT).show();//Show Toast
        mActionMode.finish();//Finish action mode after use

    }

    //Edit existing plan
    public void editPlan(){

        //TODO: think about how to handle this object after leaving the activity
        mActionMode.finish();
        setNullToActionMode();

        Intent editIntent = new Intent(getApplication(),CreatePlanActivity.class);
        SparseBooleanArray itemSelected = adapter.getSelectedIds();
        Plan passedPlan = myPlans.get(itemSelected.keyAt(0));
        editIntent.putExtra("edit_plan", passedPlan);
        startActivity(editIntent);
    }
}
