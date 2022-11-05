package com.example.reconchainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.reconchainapp.user.login.logInActivity;

import java.util.Timer;
import java.util.TimerTask;

public class splashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences preferences = splashScreenActivity.this.getSharedPreferences("sharedPreferencesLogin", Context.MODE_PRIVATE);
        String login = preferences.getString("login","");

        getSupportActionBar().hide();
        Timer t =new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {

                if (login.equals("yes")){
                    Intent i =  new Intent(splashScreenActivity.this, navBottomActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i =  new Intent(splashScreenActivity.this, logInActivity.class);
                    startActivity(i);
                    finish();
                }


            }
        },2000);
    }
}