package com.example.ae.ExplorEgypt.infrastructure;


import android.app.Application;
import android.util.Log;

import com.example.ae.ExplorEgypt.modules.User;


public class MyApplication extends Application{

    //public MyApplication(){}


    @Override
    public void onCreate() {
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/mobile_sans.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
        super.onCreate();
        Log.e("onCreate: ","My application is called" );


    }

}
