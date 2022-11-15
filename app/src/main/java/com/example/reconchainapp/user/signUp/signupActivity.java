package com.example.reconchainapp.user.signUp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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
import com.example.reconchainapp.user.login.logInActivity;
import com.example.reconchainapp.user.login.loginInterface;
import com.example.reconchainapp.user.pickLocationActivity;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signupActivity extends AppCompatActivity {

    String[] listRole;
    String strx = "";
    String x = "";
    EditText et_role,et_company,et_username,et_email,et_password,et_confirmpass,et_location,et_name;
    Button bt_signup, bt_login;
    String slong,slat;
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
        slong =  intent.getStringExtra("longitude");
        slat = intent.getStringExtra("latitude");

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
                listRole = new String[]{"producer","distributor"};
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
                et_location.getText().toString().equals("")
              )
        {

            Toast.makeText(getApplicationContext(),"field cant be empty!",Toast.LENGTH_LONG).show();
        }else{
            signUp();
//            Toast.makeText(getApplicationContext(),"account has been created succesfully!",Toast.LENGTH_LONG).show();
        }

    }

    private void signUp() {

        String name,username,email,password,confirmpass,company, role,location, longitude, latitude;


        name = et_name.getText().toString();
        username =  et_username.getText().toString();
        email = et_email.getText().toString();
        password = et_password.getText().toString();
        confirmpass = et_confirmpass.getText().toString();
        company = et_company.getText().toString();
        role = et_role.getText().toString();
        location = et_location.getText().toString();

        role = role.toLowerCase();

        Log.d("email validator", String.valueOf(emailValidator(email)));
        Log.d("rolex", role);

        if (emailValidator(email) ==  false){
            Toast.makeText(getApplicationContext(),"invalid email!", Toast.LENGTH_SHORT).show();
        }else{
            if (et_email.getText().toString().length() < 8) {
                Toast.makeText(getApplicationContext(), "Password minimum 8 character!", Toast.LENGTH_SHORT).show();
            }else {
            if (password.equals(confirmpass)){
                signUpAction(name,username,email,password,confirmpass,company,role,location);
            }else {
                Toast.makeText(getApplicationContext(),"Password doesnt match!", Toast.LENGTH_SHORT).show();
            }
            }
        }
    }

    private void signUpAction(String name, String username, String email, String password, String confirmpass, String company, String role, String location) {

        SharedPreferences sharedPreferencesLogin = signupActivity.this.getSharedPreferences("sharedPreferencesLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorLogin = sharedPreferencesLogin.edit();

        SharedPreferences sharedPreferencesUser = signupActivity.this.getSharedPreferences("sharedPreferencesUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorUser = sharedPreferencesUser.edit();

        signUpInterface api = retrofitapi.getClient(getApplicationContext()).create(signUpInterface.class);
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("name",name);
        hashMap.put("username",username);
        hashMap.put("email",email);
        hashMap.put("password",password);
        hashMap.put("role",role);
        if (role.equals("distributor")){
            hashMap.put("company_code",company);
        }else{
            hashMap.put("company",company);
        }
        hashMap.put("location",location);
        hashMap.put("longitude",slong);
        hashMap.put("latitude",slat);

        try{

            Call<signUpResponse> call = api.signUp(hashMap);
            call.enqueue(new Callback<signUpResponse>() {
                @Override
                public void onResponse(Call<signUpResponse> call, Response<signUpResponse> response) {
                    if (response.code() == 200){

                        Integer status = response.body().getUser().getStatus();


                        if (status == 0){
                            Toast.makeText(getApplicationContext(),"account will be validated by producer!", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getApplicationContext(), "account has been created!", Toast.LENGTH_SHORT).show();


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
                            editorUser.putString("companycode", response.body().getUser().getCompanycode());
                            editorUser.putString("location", response.body().getUser().getLocation());
                            editorUser.putString("longitude", response.body().getUser().getLongitude());
                            editorUser.putString("latitude", response.body().getUser().getLatitude());
                            editorUser.putString("id", "688AA8800");
                            editorUser.apply();


                            Intent i =  new Intent(signupActivity.this, landingActivity.class);
                            startActivity(i);
                            finish();
                        }


                    }else {
                        Toast.makeText(getApplicationContext(),"failed to make account!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<signUpResponse> call, Throwable t) {

                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}