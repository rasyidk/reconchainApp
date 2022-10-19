package com.example.reconchainapp.input;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reconchainapp.R;
import com.example.reconchainapp.navBottomActivity;
import com.example.reconchainapp.profile.distributorRequestActivity;

public class inputProductActivity extends AppCompatActivity {

    EditText et_idproduct,et_productname,et_date,et_location,et_company,et_raw,et_carbon;
    Button bt_save, bt_back;
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
                    Toast.makeText(getApplicationContext(),"saved!",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}