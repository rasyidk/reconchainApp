package com.example.reconchainapp.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface pickLocationInterface {

    @GET
    Call<List<pickLocationList>> getContacts(@Url String url);



}
