package com.example.ae.ExplorEgypt.infrastructure;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.example.ae.ExplorEgypt.modules.PairOfDayAndPlace;
import com.example.ae.ExplorEgypt.modules.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class HelperClass implements Comparable {

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


    //Order the list of pairs and collect the duplicated ones.
    public static ArrayList<PairOfDayAndPlace> orderThePairs(ArrayList<PairOfDayAndPlace> passedPair) {
        ArrayList<PairOfDayAndPlace> returnPairsList = new ArrayList<>();

        for (int i = 0; i < passedPair.size(); i++) {
            boolean isUnique = true;
            PairOfDayAndPlace uniquePair = new PairOfDayAndPlace();
            uniquePair.setDay(passedPair.get(i).getDay());
            uniquePair.addPlaceToList(passedPair.get(i).getPlace().get(0));

            for (int j = i + 1; j < passedPair.size(); j++) {
                if (passedPair.get(i).getDay() == passedPair.get(j).getDay()) {
                    uniquePair.addPlaceToList(passedPair.get(j).getPlace().get(0));
                }
            }

            if (!returnPairsList.isEmpty()) {
                for (int k = 0; k < returnPairsList.size(); k++) {
                    if (uniquePair.getDay() == returnPairsList.get(k).getDay()) {
                        isUnique = false;
                    }
                }
            }

            if (isUnique) {
                returnPairsList.add(uniquePair);
            }
        }

        //sort the list by the day order
        Collections.sort(returnPairsList, new Comparator<PairOfDayAndPlace>() {
            @Override
            public int compare(PairOfDayAndPlace pairOfDayAndPlace, PairOfDayAndPlace t1) {
                return  pairOfDayAndPlace.getDay() - t1.getDay();
            }
        });

        return returnPairsList;
    }


    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}
