package com.example.reconchainapp.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reconchainapp.R;

public class signupActivity extends AppCompatActivity {

    String[] listRole;
    EditText et_role,et_company,et_username,et_email,et_password,et_confirmpass,et_location;
    Button bt_signup, bt_login;
    int pos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

//        initialization
        et_username = findViewById(R.id.signup_et_username);
        et_email = findViewById(R.id.signup_et_email);
        et_password = findViewById(R.id.signup_et_password);
        et_confirmpass =  findViewById(R.id.signup_et_confirmpass);
        et_role =  findViewById(R.id.signup_et_role);
        et_company = findViewById(R.id.signup_et_company);
        et_location =findViewById(R.id.signup_et_location);

        bt_login =findViewById(R.id.signup_bt_login);
        bt_signup = findViewById(R.id.signup_bt_signup);

        //Bt Action
        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_signup_act();
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(signupActivity.this, logInActivity.class);
                startActivity(i);
            }
        });

        et_company.setVisibility(View.GONE);
        et_role.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listRole = new String[]{"Produsen","Distributor"};
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(signupActivity.this);
                mBuilder.setTitle("Choose Role");

                mBuilder.setSingleChoiceItems(listRole, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        et_role.setText(listRole[i]);
                        pos = i;
                    }
                });
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                mBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        et_company.setVisibility(View.VISIBLE);
                        Log.d("asas :", String.valueOf(pos));
                        if (pos == 0){
                            et_company.setHint("Company Name");
                        }else {
                            et_company.setHint("Company Code");
                        }
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
    }

    private void bt_signup_act() {

        if (
                et_username.getText().toString().equals("") ||
                et_email.getText().toString().equals("") ||
                et_password.getText().toString().equals("") ||
                et_confirmpass.getText().toString().equals("") ||
                et_company.getText().toString().equals("") ||
                et_role.getText().toString().equals("")||
                et_location.getText().toString().equals(""))
        {

            Toast.makeText(getApplicationContext(),"Cant Empty!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"All Filled!",Toast.LENGTH_LONG).show();
        }

    }
}