package com.example.reconchainapp.ui.productList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.reconchainapp.databinding.FragmentProductlistBinding;

public class productListFragment extends Fragment {

    private FragmentProductlistBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        productListViewModel notificationsViewModel =
                new ViewModelProvider(this).get(productListViewModel.class);

        binding = FragmentProductlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}