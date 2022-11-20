package com.example.reconchainapp.ui.productList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.reconchainapp.databinding.FragmentProductlistBinding;
import com.example.reconchainapp.trackingProductActivity;

public class productListFragment extends Fragment {

    private FragmentProductlistBinding binding;
    String valid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        productListViewModel notificationsViewModel =
                new ViewModelProvider(this).get(productListViewModel.class);

        binding = FragmentProductlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageView img1,img2,img3,img4;
        EditText pl_et_search;

        img1 = binding.frplImgNext1;
        img2 = binding.frplImgNext2;
        img3 = binding.frplImgNext3;
        img4 = binding.frplImgNext4;
        pl_et_search = binding.plEtSearch;
        valid = pl_et_search.getText().toString();




        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getContext(), trackingProductActivity.class);
                startActivity(i);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getContext(), trackingProductActivity.class);
                startActivity(i);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getContext(), trackingProductActivity.class);
                startActivity(i);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getContext(), trackingProductActivity.class);
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