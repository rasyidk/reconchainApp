package com.example.reconchainapp.ui.home.DL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reconchainapp.R;
import com.example.reconchainapp.api.retrofitapi;
import com.example.reconchainapp.navBottomActivity;
import com.example.reconchainapp.profile.profileActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class distributorListActivity extends AppCompatActivity {

    distributorListAdapter distributorListAdapterku;
    RecyclerView rv;
    ProgressBar progressBar;
    LinearLayout errorLayout;
    Button btnRetry;
    TextView txtError;
    TextView txtEmptyData;
//    String auth = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im1rcnMxMjMiLCJwYXNzd29yZCI6IjEyMyIsImNvbXBhbnlfY29kZSI6ImQ4NzIyNSIsInJvbGUiOiJwcm9kdWNlciIsImVtYWlsIjoiZW1haWxAbWFpbC5jb20iLCJpYXQiOjE2NjgzMDE0MDV9.D_-O7vcEteyryD8dLMXt_0g1E7K4p4iFVKayFl95nuw";

    String auth;

    ArrayList<distributorListRequestData> mainModels;

    Button bt_tods, bt_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributor_list);
        getSupportActionBar().hide();

        SharedPreferences preferences = distributorListActivity.this.getSharedPreferences("sharedPreferencesUser", Context.MODE_PRIVATE);
        auth = preferences.getString("token","");

        //define models
        mainModels = new ArrayList<>();

        //define object
        rv = findViewById(R.id.dl_main_recycler);
        progressBar = findViewById(R.id.dl_main_progress);
        btnRetry = findViewById(R.id.error_btn_retry);
        txtError = findViewById(R.id.error_txt_cause);
        errorLayout = findViewById(R.id.error_layout);
        txtEmptyData = findViewById(R.id.dl_tv_emptydata);
        txtEmptyData.setVisibility(View.GONE);
        bt_back = findViewById(R.id.dl_bt_back);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(distributorListActivity.this, navBottomActivity.class);
                startActivity(i);
            }
        });



        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFirstPage();
                progressBar.setVisibility(View.VISIBLE);
                errorLayout.setVisibility(View.GONE);
                txtEmptyData.setVisibility(View.GONE);
            }
        });

        loadFirstPage();

    }

    private void loadFirstPage(){
//        hideErrorView();


        distributorListInterface api = retrofitapi.getClient(distributorListActivity.this).create(distributorListInterface.class);
        Call<distributorListResponse> call = api.getDistributorList("Bearer "+ auth);
        call.enqueue(new Callback<distributorListResponse>() {
            @Override
            public void onResponse(Call<distributorListResponse> call, Response<distributorListResponse> response) {

                try {

//                    Toast.makeText(getApplicationContext(), "HEYYHOOO", Toast.LENGTH_SHORT).show();
                    if (response.code() == 200){

                        List<distributorListRequestData> results = response.body().getResults();

                        if (results.isEmpty()){
                            Toast.makeText(getApplicationContext(),"Data Empty!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            txtEmptyData.setVisibility(View.VISIBLE);

                        }else {
//                            Toast.makeText(getApplicationContext(), String.valueOf(results.size()),Toast.LENGTH_SHORT).show();

                            for (int i=0;i<results.size();i++){
                                distributorListRequestData model = new distributorListRequestData(results.get(i).getUsername(), results.get(i).getId(),results.get(i).getName(),results.get(i).getLocation(), results.get(i).getEmail());
                                Log.d("id",String.valueOf(results.get(i).getId()));
                                Log.d("company",results.get(i).getUsername());
                                Log.d("name",results.get(i).getName());
                                Log.d("location",results.get(i).getLocation());
                                mainModels.add(model);

                            }
                            progressBar.setVisibility(View.GONE);
                            call();

                        }



                    }else{
                        Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        showErrorViewstr("error");
                    }


                }catch (Exception e){
                    showErrorViewstr("error");
                    Log.d("ERROR", e.getLocalizedMessage());
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<distributorListResponse> call, Throwable t) {
//                t.printStackTrace();
//                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage().toLowerCase(Locale.ROOT), Toast.LENGTH_SHORT).show();
                showErrorView(t);
            }
        });



    }

    private void call() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                distributorListActivity.this, LinearLayoutManager.VERTICAL, false
        );
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        distributorListAdapterku = new distributorListAdapter(distributorListActivity.this, mainModels);
        rv.setAdapter(distributorListAdapterku);

    }

    private void showErrorView(Throwable throwable) {

        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            txtError.setText(fetchErrorMessage(throwable));
        }
    }

    private void showErrorViewstr(String throwable) {

        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            txtError.setText(throwable);
        }
    }
    private String fetchErrorMessage(Throwable throwable) {
        String errorMsg = getResources().getString(R.string.error_msg_unknown);

        if (!isNetworkConnected()) {
            errorMsg = getResources().getString(R.string.error_msg_no_internet);
        } else if (throwable instanceof TimeoutException) {
            errorMsg = getResources().getString(R.string.error_msg_timeout);
        }

        return errorMsg;
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) distributorListActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}