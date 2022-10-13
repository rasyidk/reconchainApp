package com.example.reconchainapp.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reconchainapp.R;

public class logInActivity extends AppCompatActivity {

    EditText et_username, et_password;
    Button bt_login,bt_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();


        //Init
        et_password = findViewById(R.id.login_et_password);
        et_username = findViewById(R.id.login_et_username);
        bt_login = findViewById(R.id.login_bt_login);
        bt_signup = findViewById(R.id.login_bt_signup);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_username.getText().toString().equals("") || et_password.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Fill the Blank!",Toast.LENGTH_LONG).show();
                }else {
                    Intent i =  new Intent(logInActivity.this, logInActivity.class);
                    startActivity(i);
                }
            }
        });

        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(logInActivity.this, signupActivity.class);
                startActivity(i);
            }
        });
    }
}