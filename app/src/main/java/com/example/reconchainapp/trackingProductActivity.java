package com.example.reconchainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.reconchainapp.user.logInActivity;

public class trackingProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_product);

        getSupportActionBar().hide();

        Button btback;
        btback = findViewById(R.id.tp_bt_back);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(trackingProductActivity.this, navBottomActivity.class);
                startActivity(i);
            }
        });
    }
}