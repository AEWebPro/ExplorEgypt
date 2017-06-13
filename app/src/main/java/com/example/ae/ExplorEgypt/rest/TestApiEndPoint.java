package com.example.ae.ExplorEgypt.rest;

import com.example.ae.ExplorEgypt.modules.PlaceDataModel;
import com.example.ae.ExplorEgypt.modules.TableRequest;
import com.example.ae.ExplorEgypt.modules.User;
import com.example.ae.ExplorEgypt.modules.UserRequest;
import com.google.android.gms.location.places.Place;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TestApiEndPoint {

    @GET("/main.php")
    Call<PlaceDataModel> getAPlace(@Query("Operation") String operation, @Query("table") String tableName, @Query("RequestParameters") PlaceDataModel place);

    @POST("/main.php")
    @FormUrlEncoded
    Call<PlaceDataModel> getPlaceFromServer(@FieldMap Map<String, String> requestMap);

    @POST("/main.php")
    Call<ArrayList<PlaceDataModel>> getPlacesService(@Body TableRequest body);

    @POST("/main.php")
    Call<ResponseBody> registerAccount(@Body TableRequest body);

    @POST("/main.php")
    Call<ArrayList<PlaceDataModel>> checkIfIsFavourite(@Body TableRequest body);

    @POST("/main.php")
    Call<ResponseBody> sendData(@Body TableRequest body);

    @POST("/main.php")
    Call<ArrayList<String>> checkIfIsAvailabe(@Body TableRequest body);

    @POST("/main.php")
    Call<ArrayList<User>> userLogin(@Body UserRequest body);

    @POST("/main.php")
    Call<ResponseBody> userSignup(@Body UserRequest body);

}