package com.example.reconchainapp.profile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.reconchainapp.R;
import com.example.reconchainapp.navBottomActivity;
import com.example.reconchainapp.ui.home.DL.distributorListActivity;
import com.example.reconchainapp.ui.home.DR.distributorRequestActivity;
import com.example.reconchainapp.user.login.logInActivity;

public class profileActivity extends AppCompatActivity {

    RelativeLayout todl,rel_logout, rel_todr, rel_term,rel_help, rel_edit;
    TextView tv_name, tv_id,tv_role;
    Button pr_btback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences preferences = profileActivity.this.getSharedPreferences("sharedPreferencesUser", Context.MODE_PRIVATE);
        String name = preferences.getString("name","");
        String role = preferences.getString("role","");
        String id = preferences.getString("companycode","");
        getSupportActionBar().hide();


        pr_btback = findViewById(R.id.pr_btback);
        todl = findViewById(R.id.pr_rel_todl);
        rel_todr = findViewById(R.id.pr_rel_todr);
        rel_logout = findViewById(R.id.pr_rel_logout);
        rel_help =  findViewById(R.id.pr_rel_help);
        rel_term = findViewById(R.id.pr_rel_term);
        rel_edit = findViewById(R.id.pr_rel_edit);

        tv_name =  findViewById(R.id.pr_tv_nama);
        tv_id = findViewById(R.id.pr_tv_id);
        tv_role = findViewById(R.id.pr_tv_role);

        tv_name.setText(name);
        tv_id.setText("Company code: " + id);
        tv_role.setText(role);

        pr_btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(profileActivity.this, navBottomActivity.class);
                startActivity(i);
            }
        });
        todl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(profileActivity.this, distributorListActivity.class);
                startActivity(i);
            }
        });
        rel_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(profileActivity.this, editProfileActivity.class);
                startActivity(i);
            }
        });

        rel_todr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(profileActivity.this, distributorRequestActivity.class);
                startActivity(i);
            }
        });

        rel_term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://reconchain.vercel.app/terms");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        rel_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://reconchain.vercel.app/helpcenter");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        if (role.equals("distributor")){
            todl.setVisibility(View.GONE);
            rel_todr.setVisibility(View.GONE);
        }else{
            todl.setVisibility(View.GONE);
            rel_todr.setVisibility(View.GONE);
        }

        rel_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

    private void logout() {
        SharedPreferences sharedPreferencesLogin = profileActivity.this.getSharedPreferences("sharedPreferencesLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorLogin = sharedPreferencesLogin.edit();

        SharedPreferences sharedPreferencesUser = profileActivity.this.getSharedPreferences("sharedPreferencesUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorUser = sharedPreferencesUser.edit();

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:

                        editorLogin.putString("login", "no");
                        editorLogin.apply();

                        //USER SHARED PREF
                        editorUser.putString("token", "");
                        editorUser.putString("name", "");
                        editorUser.putString("username", "");
                        editorUser.putString("password","");
                        editorUser.putString("email","");
                        editorUser.putString("role", "");
                        editorUser.putString("company", "");
                        editorUser.putString("location", "");
//                        editorUser.putString("longitude", response.body().getUser().getLongitude());
//                        editorUser.putString("latitude", response.body().getUser().getLatitude());
                        editorUser.putString("id", "");
                        editorUser.apply();

                        Intent i =  new Intent(profileActivity.this, logInActivity.class);
                        startActivity(i);
                        finish();

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(profileActivity.this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}