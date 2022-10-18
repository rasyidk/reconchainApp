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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        getSupportActionBar().hide();
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        up_et_date =  findViewById(R.id.up_et_date);
        up_et_date.setText(formattedDate);

        final Spinner spinner = (Spinner) findViewById(R.id.coba_spinner);
        spinner_status = findViewById(R.id.up_spinner_status);
        spinner_shipping = findViewById(R.id.up_spinner_shipping);

        // Spinner click listener


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Item 1");
        categories.add("Item 2");
        categories.add("Item 3");
        categories.add("Item 4");
        categories.add("Item 5");
        categories.add("Item 6");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner_shipping.setAdapter(dataAdapter);
        spinner_status.setAdapter(dataAdapter);

    }
}