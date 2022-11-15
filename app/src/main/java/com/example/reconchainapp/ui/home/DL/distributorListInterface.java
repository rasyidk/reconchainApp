package com.example.reconchainapp.ui.home.DL;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface distributorListInterface {

    @GET("user/distributor-list")
    Call<distributorListResponse> getDistributorList(@Header("Authorization") String authHeader);

}
