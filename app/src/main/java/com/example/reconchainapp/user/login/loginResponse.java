package com.example.reconchainapp.user.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class loginResponse {

    private loginAdapter user;
    public loginAdapter user() {
        return user;
    }
    @SerializedName("token")
    @Expose
    private String token;

    public loginAdapter getUser() {
        return user;
    }

    public void setUser(loginAdapter user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
