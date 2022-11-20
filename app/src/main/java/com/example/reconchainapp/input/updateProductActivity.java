package com.example.reconchainapp.input;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.reconchainapp.R;
import com.example.reconchainapp.api.retrofitapi;
import com.example.reconchainapp.navBottomActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class updateProductActivity extends AppCompatActivity {


    EditText up_et_date,up_et_id,up_et_note,up_et_carbon;
    Spinner spinner_status, spinner_shipping;
    Button bt_save,ip_bt_back;
    String auth, valueQR, slat, slong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        getSupportActionBar().hide();

        bt_save= findViewById(R.id.up_bt_save);
        up_et_id  = findViewById(R.id.up_et_id);
        up_et_date =  findViewById(R.id.up_et_date);
        up_et_note = findViewById(R.id.up_et_note);
        up_et_carbon = findViewById(R.id.up_et_carbon);
        ip_bt_back = findViewById(R.id.ip_bt_back);

        ip_bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =  new Intent(updateProductActivity.this, navBottomActivity.class);
                startActivity(i);
            }
        });



        SharedPreferences preferencesxx = updateProductActivity.this.getSharedPreferences("sharedPreferencesUser", Context.MODE_PRIVATE);
        auth = preferencesxx.getString("token","");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            valueQR = extras.getString("qr");
            //The key argument here must match that used in the other activity
        }


        up_et_id.setText(valueQR);



        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        up_et_date.setText(formattedDate);


        spinner_status = findViewById(R.id.up_spinner_status);
        spinner_shipping = findViewById(R.id.up_spinner_shipping);

        List<String> productList =  new ArrayList<String>();
        productList.add("Good Quality");
        productList.add("Product Damaged");

        List<String> shippingList =  new ArrayList<String>();
        shippingList.add("packed");
        shippingList.add("shipping");
        shippingList.add("delivered");






        ArrayAdapter<String> dataAdapterProduct = new ArrayAdapter<String>(this, R.layout.spinner_item, productList);

        // Drop down layout style - list view with radio button
        dataAdapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> dataAdapterShipping = new ArrayAdapter<String>(this, R.layout.spinner_item, shippingList);

        // Drop down layout style - list view with radio button
        dataAdapterShipping.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner_shipping.setAdapter(dataAdapterShipping);
        spinner_status.setAdapter(dataAdapterProduct);


        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Saved!", Toast.LENGTH_LONG).show();
                String status,shipping,note, carbon;
                carbon = up_et_carbon.getText().toString();
                status = spinner_status.getSelectedItem().toString();
                shipping = spinner_shipping.getSelectedItem().toString();
                note = up_et_note.getText().toString();

                updateData(carbon,formattedDate,status, shipping, note,valueQR);

            }
        });

        getlonglat();
        up_et_carbon.setText("calculating....");
    }

    private void getlonglat() {

        try {

            updateProductInterface api = retrofitapi.getClient(updateProductActivity.this).create(updateProductInterface.class);
            Call<longlatResponse> cx =  api.getLongitudeLatitude("Bearer "+ auth, valueQR);
            cx.enqueue(new Callback<longlatResponse>() {
                @Override
                public void onResponse(Call<longlatResponse> call, Response<longlatResponse> response) {

                    try {
                        String longitude = response.body().getLongitude();
                        String latitude = response.body().getLatitude();

                        Log.d("long", longitude);
                        Log.d("lat", latitude);

                        double dblong= Double.parseDouble(longitude);
                        double dblat= Double.parseDouble(latitude);

                        String slat, slong;
                        SharedPreferences preferencesyyy = updateProductActivity.this.getSharedPreferences("sharedPreferencesUser", Context.MODE_PRIVATE);
                        slat = preferencesyyy.getString("latitude","");
                        slong = preferencesyyy.getString("longitude","");

                        Log.d("slat", slat);
                        Log.d("slong", slong);

                        double dbslong= Double.parseDouble(slong);
                        double dbslat= Double.parseDouble(slat);
                        Double pi = 3.14159265358979;
                        Double lat1 = dblat;
                        Double lon1 = dblong;
                        Double lat2 = dbslat;
                        Double lon2 = dbslong;
                        Double R = 6371e3;

                        Double latRad1 = lat1 * (pi / 180);
                        Double latRad2 = lat2 * (pi / 180);
                        Double deltaLatRad = (lat2 - lat1) * (pi / 180);
                        Double deltaLonRad = (lon2 - lon1) * (pi / 180);

                        /* RUMUS HAVERSINE */
                        Double a = Math.sin(deltaLatRad / 2) * Math.sin(deltaLatRad / 2) + Math.cos(latRad1) * Math.cos(latRad2) * Math.sin(deltaLonRad / 2) * Math.sin(deltaLonRad / 2);
                        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                        Double s = R * c; // hasil jarak dalam meter

                        Double x = ((s/1000)/8.39) * 2.64;

//                        Toast.makeText(updateProductActivity.this, String.valueOf(s), Toast.LENGTH_SHORT).show();


                        up_et_carbon.setText(String.valueOf(Integer.valueOf(x.intValue())));

                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                    }




                }

                @Override
                public void onFailure(Call<longlatResponse> call, Throwable t) {

                }
            });


        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void updateData(String carbon, String formattedDate, String status, String shipping, String note, String valueQR) {

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("id",valueQR);
        hashMap.put("carbon", carbon);
        hashMap.put("date", formattedDate);
        hashMap.put("destination", "XXX");
        hashMap.put("shipping_status",shipping);
        hashMap.put("product_status",status);
        hashMap.put("note",note);

        updateProductInterface api = retrofitapi.getClient(updateProductActivity.this).create(updateProductInterface.class);
        Call<updateProductResponse> call = api.inputData("Bearer "+ auth, hashMap);

        try {
            call.enqueue(new Callback<updateProductResponse>() {
                @Override
                public void onResponse(Call<updateProductResponse> call, Response<updateProductResponse> response) {

                    try {

                        if (response.code() == 200){
                            Toast.makeText(getApplicationContext(), "Successfully Updated!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Failed to Update!", Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toLowerCase(Locale.ROOT).toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<updateProductResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Fail!", Toast.LENGTH_SHORT).show();
                }
            });


        }catch (Exception e){
            Toast.makeText(this, e.getLocalizedMessage().toLowerCase(Locale.ROOT).toString(), Toast.LENGTH_SHORT).show();
        }

    }
}