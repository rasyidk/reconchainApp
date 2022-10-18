package com.example.reconchainapp.profile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reconchainapp.R;
import com.example.reconchainapp.input.inputProductActivity;
import com.example.reconchainapp.navBottomActivity;
import com.example.reconchainapp.splashScreenActivity;
import com.example.reconchainapp.user.logInActivity;

public class profileActivity extends AppCompatActivity {

    RelativeLayout todl,rel_logout;
    TextView tv_name, tv_id,tv_role;
    Button pr_btback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences preferences = profileActivity.this.getSharedPreferences("valuser", Context.MODE_PRIVATE);
        String name = preferences.getString("name","");
        String role = preferences.getString("role","");
        String id = preferences.getString("id","");
        getSupportActionBar().hide();


        pr_btback = findViewById(R.id.pr_btback);
        todl = findViewById(R.id.pr_rel_todl);
        rel_logout = findViewById(R.id.pr_rel_logout);

        tv_name =  findViewById(R.id.pr_tv_nama);
        tv_id = findViewById(R.id.pr_tv_id);
        tv_role = findViewById(R.id.pr_tv_role);

        tv_name.setText(name);
        tv_id.setText(id);
        tv_role.setText(role);

        pr_btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(profileActivity.this, navBottomActivity.class);
                startActivity(i);
            }
        });
        todl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(profileActivity.this, distributorListActivity.class);
                startActivity(i);
            }
        });

        if (role.equals("distributor")){
            todl.setVisibility(View.GONE);
        }

        rel_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

    private void logout() {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent i =  new Intent(profileActivity.this, logInActivity.class);
                        startActivity(i);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(profileActivity.this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}