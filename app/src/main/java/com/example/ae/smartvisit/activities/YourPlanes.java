package com.example.ae.smartvisit.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.adapters.RecyclerAdapterYourPlans;
import com.example.ae.smartvisit.infrastructure.RecyclerClick_Listener;
import com.example.ae.smartvisit.infrastructure.RecyclerTouchListener;
import com.example.ae.smartvisit.infrastructure.Toolbar_ActionMode_Callback;
import com.example.ae.smartvisit.modules.PairOfDayAndPlace;
import com.example.ae.smartvisit.modules.PlaceDataModel;
import com.example.ae.smartvisit.modules.Plan;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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

        ArrayList<PlaceDataModel> listOfPlaces1 = new ArrayList<>();
        listOfPlaces1.add(new PlaceDataModel("Hotel",getString(R.string.temp_text),getString(R.string.hotel_image),"","","1","",""));

        ArrayList<PlaceDataModel> listOfPlaces2 = new ArrayList<>();
        listOfPlaces2.add(new PlaceDataModel("Pyramid",getString(R.string.temp_text),getString(R.string.pyramids_image),"","","2","",""));

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

        plansList = (RecyclerView) findViewById(R.id.activity_your_plans_recyclerView);
        plansList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerAdapterYourPlans(myPlans, this);
        plansList.setAdapter(adapter);

        implementRecyclerViewClickListeners();

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
