package com.example.ae.smartvisit.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.infrastructure.MyApplication;

public class BaseActivity extends AppCompatActivity {
    protected MyApplication application;
    protected boolean isTablet;
    protected Toolbar toolbar;
    protected DrawerLayout navDrawer;
    protected ActionBarDrawerToggle toggle;
    protected NavigationView navigationView;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        navDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (navDrawer != null) {
            navigationView = (NavigationView) findViewById(R.id.activity_navigation_view);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();
                    if (id == R.id.nav_home) {
                        startActivity(new Intent(application, HomeActivity.class));
                        navDrawer.closeDrawer(GravityCompat.START);
                        finish();
                    } else if (id == R.id.nav_plans) {

                        if (!navigationView.getMenu().findItem(R.id.nav_create_plan).isVisible()) {
                            navigationView.getMenu().setGroupVisible(R.id.nav_plans_group, true);
                            // navigationView.getMenu().findItem(R.id.nav_create_plan).getActionView()
                            //       .setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_down_in));

                        } else {
                            navigationView.getMenu().setGroupVisible(R.id.nav_plans_group, false);
                        }

                    } else if (id == R.id.nav_services) {
                        startActivity(new Intent(application, Services.class));
                        navDrawer.closeDrawer(GravityCompat.START);
                        finish();
                    } else if (id == R.id.nav_create_plan) {
                        Toast.makeText(application, "Create a Plan", Toast.LENGTH_SHORT).show();
                    } else if (id == R.id.nav_your_plans) {
                        startActivity(new Intent(application, YourPlanes.class));
                        navDrawer.closeDrawer(GravityCompat.START);
                        finish();
                    } else if (id == R.id.nav_recommended_plans) {
                        startActivity(new Intent(application, RecommandPrograms.class));
                        navDrawer.closeDrawer(GravityCompat.START);
                        finish();
                    }else if(id == R.id.nav_logout){
                        startActivity(new Intent(application, LoginActivity.class));
                        navDrawer.closeDrawer(GravityCompat.START);
                        finish();
                    }

                    return true;
                }
            });


            toggle = new ActionBarDrawerToggle(this, navDrawer, R.string.open, R.string.close);

            navDrawer.addDrawerListener(toggle);
            toggle.syncState();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("onCreate: ", "My Base Activity is called");
        application = (MyApplication) getApplication();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        toolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    //for the click to finish the keypad
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

}
