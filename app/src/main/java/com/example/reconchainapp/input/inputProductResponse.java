package com.example.reconchainapp.input;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class inputProductResponse {

    @SerializedName("address")
    @Expose
    private String address;

    public inputProductResponse(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
