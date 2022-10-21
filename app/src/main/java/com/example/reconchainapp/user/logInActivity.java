package com.example.reconchainapp.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reconchainapp.R;
import com.example.reconchainapp.landingActivity;

public class logInActivity extends AppCompatActivity {

    EditText et_username, et_password;
    Button bt_login,bt_signup;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();


        et_password = findViewById(R.id.login_et_password);
        et_username = findViewById(R.id.login_et_username);
        bt_login = findViewById(R.id.login_bt_login);
        bt_signup = findViewById(R.id.login_bt_signup);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences valuser = logInActivity.this.getSharedPreferences("valuser", Context.MODE_PRIVATE);

                if (et_username.getText().toString().equals("") || et_password.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"field cant be empty!",Toast.LENGTH_LONG).show();
                }else {
                    String username, password;
                    username = et_username.getText().toString();
                    password = et_password.getText().toString();
                    if (username.equals("produsen") && password.equals("123")){
                        Intent i =  new Intent(logInActivity.this, landingActivity.class);
                        startActivity(i);
                        finish();

                        SharedPreferences.Editor editor = valuser.edit();
                        editor.putString("username", username);
                        editor.putString("name", "pudyasta");
                        editor.putString("role", "produsen");
                        editor.putString("addr", "Jl. Persatuan, Bulaksumur, Sleman, Yogyakarta");
                        editor.putString("password","123");
                        editor.putString("company","PT Maju Indonesia");
                        editor.putString("id", "12122SSSS");
                        editor.apply();

                    }else if (username.equals("distributor") && password.equals("123")){
                        Intent i =  new Intent(logInActivity.this, landingActivity.class);
                        startActivity(i);
                        finish();

                        SharedPreferences.Editor editor = valuser.edit();
                        editor.putString("username", username);
                        editor.putString("name", "rasyidk");
                        editor.putString("password","12345678");
                        editor.putString("addr", "Jl. Damai, Baki, Sukoharjo");
                        editor.putString("role", "distributor");
                        editor.putString("id", "688AA8800");
                        editor.apply();

                    }else {
                        Toast.makeText(getApplicationContext(),"your account is not registered yet!", Toast.LENGTH_LONG).show();
                    }


                }
            }
        });

        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(logInActivity.this, signupActivity.class);
                i.putExtra("role", "");
                startActivity(i);
            }
        });
    }


}