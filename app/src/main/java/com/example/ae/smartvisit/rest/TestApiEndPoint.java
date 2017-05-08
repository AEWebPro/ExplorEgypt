package com.example.ae.smartvisit.rest;

import com.example.ae.smartvisit.modules.PlaceDataModel;
import com.example.ae.smartvisit.modules.TableRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TestApiEndPoint {

    @GET("/service.php")
    Call<PlaceDataModel> getAPlace(@Query("Operation") String operation, @Query("table") String tableName, @Query("RequestParameters") PlaceDataModel place);

    @POST("/service.php")
    @FormUrlEncoded
    Call<PlaceDataModel> getPlaceFromServer(@FieldMap Map<String, String> requestMap);


    @POST("/service.php")
    Call<ArrayList<PlaceDataModel>> getPlacesService(@Body TableRequest body);
}