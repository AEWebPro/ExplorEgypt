package com.example.ae.smartvisit.activities;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.infrastructure.MyApplication;

public class BaseActivity extends AppCompatActivity {
    protected MyApplication application;
    protected boolean isTablet;
    protected Toolbar toolbar;
    protected  DrawerLayout navDrawer;
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

         navDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("onCreate: ","My Base Activity is called" );
        application =  (MyApplication) getApplication();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        toolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!navDrawer.isDrawerOpen(GravityCompat.START)){
                    navDrawer.openDrawer(GravityCompat.START);
                }else{
                    navDrawer.closeDrawer(GravityCompat.START);
                }
            }
        });

        findViewById(R.id.nav_drawer_createplan_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BaseActivity.this, "Go to Create A plan Activity",Toast.LENGTH_SHORT).show();
                navDrawer.closeDrawer(GravityCompat.START);
            }
        });


        findViewById(R.id.nav_drawer_services_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BaseActivity.this, "Go to Services Activity",Toast.LENGTH_SHORT).show();
                navDrawer.closeDrawer(GravityCompat.START);
            }
        });


        findViewById(R.id.nav_drawer_settings_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BaseActivity.this, "Go to Settings Activity",Toast.LENGTH_SHORT).show();
                navDrawer.closeDrawer(GravityCompat.START);
            }
        });


        findViewById(R.id.nav_drawer_log_out_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BaseActivity.this, "Logged out!",Toast.LENGTH_SHORT).show();
                navDrawer.closeDrawer(GravityCompat.START);
            }
        });


    }
}
