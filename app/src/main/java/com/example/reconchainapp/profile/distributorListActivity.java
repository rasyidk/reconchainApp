package com.example.reconchainapp.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.reconchainapp.R;
import com.example.reconchainapp.splashScreenActivity;
import com.example.reconchainapp.user.signupActivity;

public class distributorListActivity extends AppCompatActivity {

    Button bt_tods, bt_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributor_list);
        getSupportActionBar().hide();


        bt_back = findViewById(R.id.dl_bt_back);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(distributorListActivity.this, profileActivity.class);
                startActivity(i);
            }
        });
    }
}