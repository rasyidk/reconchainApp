package com.example.reconchainapp.ui.home.DR;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class distributorRequestResponse {


//    @SerializedName("data")
//    @Expose
//    private List data;
//
//    public distributorRequestData getData() {
//        return (distributorRequestData) data;
//    }
//
//    public void setData(distributorRequestData data) {
//        this.data = (List) data;
//    }

    @SerializedName("data")
    @Expose
    private List<distributorRequestData> results = new ArrayList<distributorRequestData>();

    public List<distributorRequestData> getResults() {
        return results;
    }


}
