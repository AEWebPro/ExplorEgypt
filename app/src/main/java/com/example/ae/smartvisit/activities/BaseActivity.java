package com.example.ae.smartvisit.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ae.smartvisit.infrastructure.MyApplication;

public class BaseActivity extends AppCompatActivity {
    protected MyApplication application;
    protected boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("onCreate: ","My Base Activity is called" );
        application =  (MyApplication) getApplication();
    }
}
