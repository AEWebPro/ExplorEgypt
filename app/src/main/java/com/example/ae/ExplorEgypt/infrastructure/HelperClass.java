package com.example.ae.ExplorEgypt.infrastructure;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.ae.ExplorEgypt.modules.User;
import com.google.gson.Gson;


public class HelperClass {

    public static void saveUserPref(Context context, String name, User savedUser){
        Gson gson = new Gson();
        String data = gson.toJson(savedUser);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(name,data).apply();
    }


    public static User getUserFromPref(Context context, String name){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String userAsString = preferences.getString(name, "");
        Gson gson = new Gson();
        User activeUser = gson.fromJson(userAsString, User.class);

        return activeUser;
    }

    public static void emptyUserPref(Context context, String name){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(name,"").apply();
    }

    public static boolean checkIfUserIsLogged(Context context, String name){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String userAsString = preferences.getString(name, "");
        if(userAsString.equals("")){
            return false;
        }else
            return true;
    }

}
