package com.amirbahadoramiri.androidlearning.views.mvvm.livedata.teststring;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LiveDataTestStringRepository extends ViewModel {

    private MutableLiveData<String> stringMutableLiveData;
    private static LiveDataTestStringRepository instance;

    public MutableLiveData<String> getStringMutableLiveData() {
        if (stringMutableLiveData == null)
            stringMutableLiveData = new MutableLiveData<>();
        return stringMutableLiveData;
    }

    public static LiveDataTestStringRepository getInstance() {
        if (instance == null)
            instance = new LiveDataTestStringRepository();
        return instance;
    }
}
