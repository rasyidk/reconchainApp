package com.example.reconchainapp.profile;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.reconchainapp.R;

public class editProfileActivity extends AppCompatActivity {

    EditText et_name,et_newpassword, et_company, et_location;
    TextView tv_company;
    Button bt_save,bt_back;

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
        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().hide();

        et_name = findViewById(R.id.ep_et_name);
        et_newpassword = findViewById(R.id.ep_et_newpassword);
        et_company = findViewById(R.id.ep_et_company);
        et_location = findViewById(R.id.ep_et_location);
        tv_company = findViewById(R.id.ep_tv_company);
        bt_save= findViewById(R.id.ep_bt_save);
        bt_back = findViewById(R.id.ep_btback);

        SharedPreferences preferences = editProfileActivity.this.getSharedPreferences("valuser", Context.MODE_PRIVATE);
        String name = preferences.getString("name","");
        String role = preferences.getString("role","");
        String company = preferences.getString("company","");
        String addr = preferences.getString("addr","");
        String password = preferences.getString("password","");
        String id = preferences.getString("id","");

        if (role.equals("distributor")){
            tv_company.setVisibility(View.GONE);
            et_company.setVisibility(View.GONE);
        }

        et_name.setText(name);
        et_newpassword.setText(password);
        et_company.setText(company);
        et_location.setText(addr);

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (role.equals("produsen")){
                    if (et_name.getText().toString().equals("") ||
                            et_newpassword.getText().toString().equals("") ||
                            et_company.getText().toString().equals("") ||
                            et_location.getText().toString().equals("")
                    ){
                        Toast.makeText(getApplicationContext(), "Field Cant be Empty!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "your account updated successfully", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if (et_name.getText().toString().equals("") ||
                            et_newpassword.getText().toString().equals("") ||
                            et_location.getText().toString().equals("")
                    ){
                        Toast.makeText(getApplicationContext(), "Field Cant be Empty!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "your account updated successfully", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(editProfileActivity.this, profileActivity.class);
                startActivity(i);
            }
        });

    }
}