package com.example.reconchainapp.user.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reconchainapp.R;
import com.example.reconchainapp.api.retrofitapi;
import com.example.reconchainapp.landingActivity;
import com.example.reconchainapp.user.signUp.signupActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                    login(username,password);
//                    if (username.equals("produsen") && password.equals("123")){
//                        Intent i =  new Intent(logInActivity.this, landingActivity.class);
//                        startActivity(i);
//                        finish();
//
//                        SharedPreferences.Editor editor = valuser.edit();
//                        editor.putString("username", username);
//                        editor.putString("name", "pudyasta");
//                        editor.putString("role", "produsen");
//                        editor.putString("addr", "Jl. Persatuan, Bulaksumur, Sleman, Yogyakarta");
//                        editor.putString("password","123");
//                        editor.putString("company","PT Maju Indonesia");
//                        editor.putString("id", "12122SSSS");
//                        editor.apply();
//
//                    }else if (username.equals("distributor") && password.equals("123")){
//                        Intent i =  new Intent(logInActivity.this, landingActivity.class);
//                        startActivity(i);
//                        finish();
//
//                        SharedPreferences.Editor editor = valuser.edit();
//                        editor.putString("username", username);
//                        editor.putString("name", "rasyidk");
//                        editor.putString("password","12345678");
//                        editor.putString("addr", "Jl. Damai, Baki, Sukoharjo");
//                        editor.putString("role", "distributor");
//                        editor.putString("id", "688AA8800");
//                        editor.apply();
//
//                    }else {
//                        Toast.makeText(getApplicationContext(),"your account is not registered yet!", Toast.LENGTH_LONG).show();
//                    }


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

    private void login(String username, String password) {

        loginInterface api = retrofitapi.getClient(getApplicationContext()).create(loginInterface.class);
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("username",username);
        hashMap.put("password",password);

        SharedPreferences sharedPreferencesLogin = logInActivity.this.getSharedPreferences("sharedPreferencesLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorLogin = sharedPreferencesLogin.edit();

        SharedPreferences sharedPreferencesUser = logInActivity.this.getSharedPreferences("sharedPreferencesUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorUser = sharedPreferencesUser.edit();
        try {
            Call<loginResponse> call = api.logIn(hashMap);
            call.enqueue(new Callback<loginResponse>() {
                @Override
                public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {

                    if (response.code() == 200){
                        Toast.makeText(getApplicationContext(), "Successfuly Login!", Toast.LENGTH_SHORT).show();

                        //LOGIN SHARED PREF
                        editorLogin.putString("login", "yes");
                        editorLogin.apply();

                        //USER SHARED PREF
                        editorUser.putString("token", response.body().getToken());
                        editorUser.putString("name", response.body().getUser().getName());
                        editorUser.putString("username", response.body().getUser().getUsername());
                        editorUser.putString("password",response.body().getUser().getPassword());
                        editorUser.putString("email",response.body().getUser().getEmail());
                        editorUser.putString("role", response.body().getUser().getRole());
                        editorUser.putString("company", response.body().getUser().getCompany());
                        editorUser.putString("location", response.body().getUser().getLocation());
//                        editorUser.putString("longitude", response.body().getUser().getLongitude());
//                        editorUser.putString("latitude", response.body().getUser().getLatitude());
                        editorUser.putString("id", "688AA8800");
                        editorUser.apply();


                        Log.d("name", response.body().getUser().getName());
                        Log.d("username", response.body().getUser().getUsername());
                        Log.d("passowrd", response.body().getUser().getPassword());
                        Log.d("email", response.body().getUser().getEmail());
                        Log.d("role", response.body().getUser().getRole());
                        Log.d("company", response.body().getUser().getCompany());
                        Log.d("companycode", response.body().getUser().getCompanycode());
                        Log.d("location", response.body().getUser().getLocation());
                        Log.d("longitude", response.body().getUser().getLongitude());
                        Log.d("latitude", response.body().getUser().getLatitude());

                        Intent i =  new Intent(logInActivity.this, landingActivity.class);
                        startActivity(i);
                        finish();

                    }else {
                        Toast.makeText(getApplicationContext(), "Invalid Account!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<loginResponse> call, Throwable t) {

                }
            });

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}