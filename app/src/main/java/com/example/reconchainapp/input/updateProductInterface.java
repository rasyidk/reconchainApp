package com.example.reconchainapp.input;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface updateProductInterface {

    @POST("deploy/update-product")
    Call<updateProductResponse> inputData(@Header("Authorization") String authHeader, @Body HashMap body);



    @GET("deploy/location/{id}")
    Call<longlatResponse> getLongitudeLatitude(@Header("Authorization") String authHeader, @Path("id") String id);
}
