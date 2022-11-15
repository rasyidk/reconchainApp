package com.example.reconchainapp.ui.home.DL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class distributorListResponse {


    @SerializedName("data")
    @Expose
    private List<distributorListRequestData> results = new ArrayList<distributorListRequestData>();

    public List<distributorListRequestData> getResults() {
        return results;
    }


}
