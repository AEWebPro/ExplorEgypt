package com.example.ae.smartvisit.rest;

import com.example.ae.smartvisit.modules.CurrencyRateModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface CurrencyRateEndPoint {

    //http://apilayer.net/api/live?access_key=19f68d5f37264b4644937800fb3bd9f3&currencies=EGP

    @GET("/api/live?access_key=19f68d5f37264b4644937800fb3bd9f3")
    Call<CurrencyRateModel> getRate(@Query("currencies") String type);
}
