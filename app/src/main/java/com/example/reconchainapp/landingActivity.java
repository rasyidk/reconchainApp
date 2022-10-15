package com.example.reconchainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.reconchainapp.profile.profileActivity;

public class landingActivity extends AppCompatActivity {

    Button bt_getstarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        getSupportActionBar().hide();
        bt_getstarted = findViewById(R.id.landing_bt_getstarted);

        bt_getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(landingActivity.this, navBottomActivity.class);
                startActivity(i);
            }
        });

    }
}