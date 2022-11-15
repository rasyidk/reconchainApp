package com.example.reconchainapp.ui.home.DR;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class distributorRequestData {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("id")
    @Expose
    private int id;


    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("email")
    @Expose
    private String email;

    public distributorRequestData(String username, int id, String name, String location, String email) {
        this.username = username;
        this.id = id;
        this.name = name;
        this.location = location;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
