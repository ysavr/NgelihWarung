package com.mythcon.savr.ngelihwarung.Remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SAVR on 15/03/2018.
 */

public interface GeoCordinates {
    @GET("maps/api/geocode/json")
    Call<String> getGeoCode(@Query("address") String address);

    @GET("maps/api/directions/json")
    Call<String> getDirection(@Query("origin") String origin,@Query("destination") String destination);
}
