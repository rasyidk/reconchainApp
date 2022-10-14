package com.example.reconchainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.reconchainapp.input.inputProductActivity;
import com.example.reconchainapp.profile.profileActivity;
import com.example.reconchainapp.user.signupActivity;

import java.util.Timer;
import java.util.TimerTask;

public class splashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        getSupportActionBar().hide();
        Timer t =new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i =  new Intent(splashScreenActivity.this, profileActivity.class);
                startActivity(i);
            }
        },500);
    }
}