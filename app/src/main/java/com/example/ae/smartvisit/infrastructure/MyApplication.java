package com.example.ae.smartvisit.infrastructure;


import android.app.Application;
import android.util.Log;



public class MyApplication extends Application{

    //public MyApplication(){}

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("onCreate: ","My application is called" );
    }
}
