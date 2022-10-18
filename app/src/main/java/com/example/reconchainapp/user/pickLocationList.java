package com.example.reconchainapp.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class pickLocationList {

    @SerializedName("place_id")
    @Expose
    private String place_id;

    @SerializedName("display_name")
    @Expose
    private String display_name;

    @SerializedName("boundingbox")
    @Expose
    private List boundingbox;


    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public List getBoundingbox() {
        return boundingbox;
    }

    public void setBoundingbox(List boundingbox) {
        this.boundingbox = boundingbox;
    }

}
