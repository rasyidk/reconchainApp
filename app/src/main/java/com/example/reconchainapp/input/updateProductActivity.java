package com.example.reconchainapp.input;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.reconchainapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class updateProductActivity extends AppCompatActivity {


    EditText up_et_date;
    Spinner spinner_status, spinner_shipping;
    Button bt_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        getSupportActionBar().hide();

        bt_save= findViewById(R.id.up_bt_save);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        up_et_date =  findViewById(R.id.up_et_date);
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
            }
        });
    }
}