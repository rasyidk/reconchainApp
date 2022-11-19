package com.example.reconchainapp.input;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface inputProductInterface {

    @POST("deploy/deploy-product")
    Call<inputProductResponse> inputData(@Header("Authorization") String authHeader, @Body HashMap body);

}
