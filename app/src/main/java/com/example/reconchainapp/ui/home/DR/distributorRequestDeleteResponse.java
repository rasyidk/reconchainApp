package com.example.reconchainapp.ui.home.DR;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class distributorRequestDeleteResponse {

    @SerializedName("message")
    @Expose
    String message;

    public distributorRequestDeleteResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
