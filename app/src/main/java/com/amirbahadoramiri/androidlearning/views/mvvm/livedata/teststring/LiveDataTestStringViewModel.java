package com.amirbahadoramiri.androidlearning.views.mvvm.livedata.teststring;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class LiveDataTestStringViewModel extends AndroidViewModel {

    public LiveDataTestStringViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getStringMutableLiveData() {
        return LiveDataTestStringRepository.getInstance().getStringMutableLiveData();
    }

}
