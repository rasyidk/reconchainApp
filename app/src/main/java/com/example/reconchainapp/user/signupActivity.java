package com.example.reconchainapp.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reconchainapp.R;
import com.example.reconchainapp.user.login.logInActivity;

public class signupActivity extends AppCompatActivity {

    String[] listRole;
    String strx = "";
    String x = "";
    EditText et_role,et_company,et_username,et_email,et_password,et_confirmpass,et_location,et_name;
    Button bt_signup, bt_login;
    int pos = 0;

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
        et_name =  findViewById(R.id.signup_et_name);

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
                finish();
            }
        });

        Intent intent = getIntent();
        String addr = intent.getStringExtra("addr");
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");
        String confirmPassword = intent.getStringExtra("confirmPassword");
        String role = intent.getStringExtra("role");
        String company = intent.getStringExtra("company");
        String name = intent.getStringExtra("name");

        et_location.setText(addr);
        et_username.setText(username);
        et_email.setText(email);
        et_password.setText(password);
        et_confirmpass.setText(confirmPassword);
        et_role.setText(role);
        et_company.setText(company);
        et_name.setText(name);

        try {
            if (role.equals("Distributor")){
                et_company.setVisibility(View.VISIBLE);
                et_company.setText(company);
            }else if(role.equals("Produsen")){

                et_company.setVisibility(View.VISIBLE);
                et_company.setText(company);
            }
            else {

                et_company.setVisibility(View.GONE);
            }
//            if (role.equals("Produsen")) {
//                et_company.setVisibility(View.VISIBLE);
//                et_company.setHint("Company Name");
//                et_company.setText(company);
//            } else if (role.equals("Distributor")) {
//                et_company.setVisibility(View.VISIBLE);
//                et_company.setHint("Company Code");
//                et_company.setText(company);
//            }
        }catch (Exception e){
            Log.d("errrrrrrr", e.toString());
        }




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

        et_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =  new Intent(signupActivity.this, pickLocationActivity.class);


                i.putExtra("name", et_name.getText().toString());
                i.putExtra("email", et_email.getText().toString());
                i.putExtra("username", et_username.getText().toString());
                i.putExtra("password", et_password.getText().toString());
                i.putExtra("confirmPassword", et_confirmpass.getText().toString());
                i.putExtra("addr", et_location.getText().toString());
                i.putExtra("role", et_role.getText().toString());
                i.putExtra("company", et_company.getText().toString());
                startActivity(i);
            }
        });
    }

    private void bt_signup_act() {

        if (
                et_name.getText().toString().equals("") ||
                et_username.getText().toString().equals("") ||
                et_email.getText().toString().equals("") ||
                et_password.getText().toString().equals("") ||
                et_confirmpass.getText().toString().equals("") ||
                et_company.getText().toString().equals("") ||
                et_role.getText().toString().equals("")||
                et_location.getText().toString().equals(""))
        {

            Toast.makeText(getApplicationContext(),"field cant be empty!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"account has been created succesfully!",Toast.LENGTH_LONG).show();
        }

    }
}