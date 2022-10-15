package com.example.reconchainapp.ui.scanQR;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class scanQRViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public scanQRViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}