package com.example.reconchainapp.ui.scanQR;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.reconchainapp.R;
import com.example.reconchainapp.databinding.FragmentScanqrBinding;
import com.example.reconchainapp.input.inputProductActivity;
import com.example.reconchainapp.input.updateProductActivity;
import com.example.reconchainapp.splashScreenActivity;
import com.example.reconchainapp.user.logInActivity;
import com.google.zxing.Result;

public class scanQRFragment extends Fragment {

    private FragmentScanqrBinding binding;
    private CodeScanner mCodeScanner;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scanQRViewModel scanQRViewModel =
                new ViewModelProvider(this).get(scanQRViewModel.class);

        binding = FragmentScanqrBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        checkCameraPermissions(getContext());
        CodeScannerView scannerView = binding.scannerView;
        mCodeScanner = new CodeScanner(getContext(), scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getContext(), result.getText(), Toast.LENGTH_SHORT).show();

                        String res = result.getText();

                        SharedPreferences preferences = getActivity().getSharedPreferences("valuser", Context.MODE_PRIVATE);
                        String role = preferences.getString("role","");
//                        Toast.makeText(getContext(),role,Toast.LENGTH_LONG).show();

                        if (role.equals("produsen")){
                            Intent i =  new Intent(getContext(), inputProductActivity.class);
                            startActivity(i);
                        }else if (role.equals("distributor")){
                            Intent i =  new Intent(getContext(), updateProductActivity.class);
                            startActivity(i);
                        }
                    }
                });
            }
        });

        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    public static void checkCameraPermissions(Context context){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            // Permission is not granted
            Log.d("checkCameraPermissions", "No Camera Permissions");
            ActivityCompat.requestPermissions((Activity) context,
                    new String[] { Manifest.permission.CAMERA },
                    100);
        }
    }

}