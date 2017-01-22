package com.example.ae.smartvisit.services;

import android.util.Log;

import com.example.ae.smartvisit.infrastructure.MyApplication;

public class Module {
    public static void register(MyApplication application){
        Log.e("Module", "Live Register method is called" );
    }
}