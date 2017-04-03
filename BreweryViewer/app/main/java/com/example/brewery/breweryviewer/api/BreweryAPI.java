package com.example.brewery.breweryviewer.api;


import com.example.brewery.breweryviewer.model.Brewery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BreweryAPI {

    @GET("/breweries")
    Call<List<Brewery>> loadAll();

    @GET("/brewery/{breweryId}")
    Call<Brewery> getBrewery(@Path("breweryId") String breweryId);

    @POST("/breweries")
    Call<Brewery> createBrewery(@Body Brewery brewery);

}
