package com.example.ae.ExplorEgypt.infrastructure;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.activities.YourPlanes;
import com.example.ae.ExplorEgypt.adapters.RecyclerAdapterYourPlans;
import com.example.ae.ExplorEgypt.modules.Plan;

import java.util.ArrayList;

/**
 * Created by ahmed.E on 4/24/2017.
 */

public class Toolbar_ActionMode_Callback implements ActionMode.Callback {

    private Context context;
    private RecyclerAdapterYourPlans recyclerView_adapter;
    private ArrayList<Plan> message_models;
    private YourPlanes yourPlanes;


    public Toolbar_ActionMode_Callback(Context context, RecyclerAdapterYourPlans recyclerView_adapter, ArrayList<Plan> message_models, YourPlanes yourPlanes) {
        this.context = context;
        this.recyclerView_adapter = recyclerView_adapter;
        this.message_models = message_models;
        this.yourPlanes = yourPlanes;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.your_plan_menu, menu);//Inflate the menu over action mode
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

        //Sometimes the meu will not be visible so for that we need to set their visibility manually in this method
        //So here show action menu according to SDK Levels
        if (Build.VERSION.SDK_INT < 11) {
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.activity_your_plans_menu_edit), MenuItemCompat.SHOW_AS_ACTION_NEVER);
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.activity_your_plans_menu_delete), MenuItemCompat.SHOW_AS_ACTION_NEVER);
        } else {
            menu.findItem(R.id.activity_your_plans_menu_edit).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.findItem(R.id.activity_your_plans_menu_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_your_plans_menu_delete:
                    if (yourPlanes != null){
                        yourPlanes.deleteRows();
                    }

                break;

            case R.id.activity_your_plans_menu_edit:
                    if(yourPlanes != null)
                        yourPlanes.editPlan();

        }
        return false;
    }


    @Override
    public void onDestroyActionMode(ActionMode mode) {

        //When action mode destroyed remove selected selections and set action mode to null

        if (yourPlanes != null){
            yourPlanes.setNullToActionMode();
        }
    }
}
