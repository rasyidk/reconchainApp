package com.example.reconchainapp.input;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reconchainapp.MainActivity;
import com.example.reconchainapp.R;
import com.example.reconchainapp.api.retrofitapi;
import com.example.reconchainapp.navBottomActivity;
import com.example.reconchainapp.profile.profileActivity;
import com.example.reconchainapp.ui.home.DR.distributorRequestActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class inputProductActivity extends AppCompatActivity {

    EditText et_idproduct,et_productname,et_date,et_location,et_company,et_raw,et_carbon;
    Button bt_save, bt_back;
    String auth;
    String valueQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_product);
        getSupportActionBar().hide();

        et_idproduct = findViewById(R.id.ip_et_idproduct);
        et_productname =  findViewById(R.id.ip_et_productname);
        et_date = findViewById(R.id.ip_et_date);
        et_location = findViewById(R.id.ip_et_locatuionmanuf);
        et_company= findViewById(R.id.ip_et_company);
        et_raw = findViewById(R.id.ip_et_raw);
        et_carbon = findViewById(R.id.ip_et_carbonused);

        bt_save = findViewById(R.id.ip_bt_save);
        bt_back = findViewById(R.id.ip_bt_back);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             valueQR = extras.getString("qr");
            //The key argument here must match that used in the other activity
        }

        SharedPreferences preferencesxx = inputProductActivity.this.getSharedPreferences("sharedPreferencesUser", Context.MODE_PRIVATE);
        auth = preferencesxx.getString("token","");

        SharedPreferences preferences = inputProductActivity.this.getSharedPreferences("sharedPreferencesUser", Context.MODE_PRIVATE);

        String company = preferences.getString("company","");
        String location = preferences.getString("location","");


        getSupportActionBar().hide();


        Log.d("QR", valueQR);
        Toast.makeText(getApplicationContext(),valueQR,Toast.LENGTH_SHORT).show();


        //set text
        et_idproduct.setText(valueQR);
        et_company.setText(company);
        et_location.setText(location);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(inputProductActivity.this, navBottomActivity.class);
                startActivity(i);
            }
        });
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_productname.getText().toString().equals("") ||
                        et_date.getText().toString().equals("") ||
                        et_location.getText().toString().equals("") ||
                        et_company.getText().toString().equals("") ||
                        et_raw.getText().toString().equals("") ||
                        et_carbon.getText().toString().equals("")
                ){
                    Toast.makeText(getApplicationContext(),"field cant be empty!",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_LONG).show();
                    inputProduct();
                }

            }
        });

    }

    private void inputProduct() {

        String productname, date, location, company, rawmaterial,carbonused ;
        productname = et_productname.getText().toString();
        location = et_location.getText().toString();
        carbonused = et_carbon.getText().toString();
        rawmaterial = et_raw.getText().toString();
        date = et_date.getText().toString();

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("id",valueQR);
        hashMap.put("product_name", productname);
        hashMap.put("producer_loc",location);
        hashMap.put("carbon",carbonused);
        hashMap.put("material",rawmaterial);
        hashMap.put("date",date);

        Log.d("HASmap", hashMap.toString());

        inputProductInterface api = retrofitapi.getClient(inputProductActivity.this).create(inputProductInterface.class);
        Call<inputProductResponse> call = api.inputData("Bearer "+ auth, hashMap);

        try {
            call.enqueue(new Callback<inputProductResponse>() {
                @Override
                public void onResponse(Call<inputProductResponse> call, Response<inputProductResponse> response) {
                    try {
                        if (response.code()  == 200){
                            Toast.makeText(getApplicationContext(),"Successfully Added!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(inputProductActivity.this, navBottomActivity.class);
                            startActivity(i);
                        }else {
                            Toast.makeText(getApplicationContext(),"Failed!XXX", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<inputProductResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Failed!", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
        }



    }
}