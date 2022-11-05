package com.example.reconchainapp.user.login;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface loginInterface {

    @POST("user/login")
    Call<loginResponse> logIn(@Body HashMap body);

}
