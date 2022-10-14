package com.example.reconchainapp.input;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reconchainapp.R;

public class inputProductActivity extends AppCompatActivity {

    EditText et_idproduct,et_productname,et_date,et_location,et_company,et_raw,et_carbon;
    Button bt_save;
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

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_idproduct.getText().toString().equals("") ||
                        et_productname.getText().toString().equals("") ||
                        et_date.getText().toString().equals("") ||
                        et_location.getText().toString().equals("") ||
                        et_company.getText().toString().equals("") ||
                        et_raw.getText().toString().equals("") ||
                        et_carbon.getText().toString().equals("")
                ){
                    Toast.makeText(getApplicationContext(),"cant blank fill!",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Saved!",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}