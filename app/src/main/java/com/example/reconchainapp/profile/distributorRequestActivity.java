package com.example.reconchainapp.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.reconchainapp.R;
import com.example.reconchainapp.navBottomActivity;
import com.example.reconchainapp.splashScreenActivity;
import com.example.reconchainapp.user.signupActivity;

public class distributorRequestActivity extends AppCompatActivity {

    Button bt_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributor_request);
        getSupportActionBar().hide();

        bt_back = findViewById(R.id.ds_bt_back);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(distributorRequestActivity.this, navBottomActivity.class);
                startActivity(i);
            }
        });
    }
}