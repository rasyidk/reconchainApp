package com.example.reconchainapp.ui.home.DR;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface distributorRequestInterface {

    @GET("user/distributor-request")
    Call<distributorRequestResponse> getDistributorRequestList(@Header("Authorization") String authHeader);

    @PUT("user/confirm/{id}")
    Call<distributorRequestDeleteResponse> confirmDistributor(@Header("Authorization") String authHeader, @Path("id") String id);

}
