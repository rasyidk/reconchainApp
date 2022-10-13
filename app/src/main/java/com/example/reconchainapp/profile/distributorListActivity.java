package com.example.reconchainapp.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.reconchainapp.R;

public class distributorListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributor_list);
        getSupportActionBar().hide();
    }
}