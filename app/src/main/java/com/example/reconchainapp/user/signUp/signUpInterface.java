package com.example.reconchainapp.user.signUp;

import com.example.reconchainapp.user.login.loginResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface signUpInterface {

    @POST("user/reg")
    Call<signUpResponse> signUp(@Body HashMap body);

}
