package com.example.reconchainapp.user.signUp;

import com.example.reconchainapp.user.login.loginAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class signUpResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("token")
    @Expose
    private String token;

    private signUpAdapter user;
    public signUpAdapter user() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public signUpAdapter getUser() {
        return user;
    }

    public void setUser(signUpAdapter user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
