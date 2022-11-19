package com.example.reconchainapp.input;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class longlatResponse {


    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    public longlatResponse(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
