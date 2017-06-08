package com.example.ae.smartvisit.infrastructure;


import android.app.Application;
import android.util.Log;

import com.example.ae.smartvisit.modules.User;


public class MyApplication extends Application{

    //public MyApplication(){}

    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("onCreate: ","My application is called" );

        user = new User();
        user.setId("700");
    }

    public User getUser() {
        return user;
    }
}
