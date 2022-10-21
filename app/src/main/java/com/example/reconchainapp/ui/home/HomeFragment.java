package com.example.reconchainapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.reconchainapp.databinding.FragmentHomeBinding;
import com.example.reconchainapp.profile.distributorListActivity;
import com.example.reconchainapp.profile.distributorRequestActivity;
import com.example.reconchainapp.profile.profileActivity;
import com.example.reconchainapp.splashScreenActivity;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageView imageView;

    TextView tv_menu;
    LinearLayout lin_menu;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ImageView imageView =binding.fhImgAcc;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getContext(), profileActivity.class);
                startActivity(i);
            }
        });

        TextView textView = binding.frHmTvEtname;
        SharedPreferences preferences = this.getActivity().getSharedPreferences("valuser", Context.MODE_PRIVATE);
        String role = preferences.getString("role","");
        String name = preferences.getString("name","");
        textView.setText(name);


        RelativeLayout rel_QR, rel_dist_list, rel_dist_req;

        rel_QR = binding.frHmMenuQR;
        rel_dist_list = binding.frHmMenuDistList;
        rel_dist_req =  binding.frHmMenuDistReq;

        tv_menu = binding.frHmTvMenu;
        lin_menu = binding.frHmLin;

        if (role.equals("distributor")){
            tv_menu.setVisibility(View.GONE);
            lin_menu.setVisibility(View.GONE);
        }

        rel_QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://reconchain.vercel.app/dashboard");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        rel_dist_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getContext(), distributorListActivity.class);
                startActivity(i);
            }
        });

        rel_dist_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getContext(), distributorRequestActivity.class);
                startActivity(i);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}