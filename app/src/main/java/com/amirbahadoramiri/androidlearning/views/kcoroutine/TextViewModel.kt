package com.amirbahadoramiri.androidlearning.views.kcoroutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextViewModel : ViewModel() {

    private var mutableLiveData : MutableLiveData<CoroutineActivity.TextModel>? = null

    fun getMutableLiveData() : MutableLiveData<CoroutineActivity.TextModel>? {
        if ( mutableLiveData == null )
            mutableLiveData = MutableLiveData<CoroutineActivity.TextModel>()
        return mutableLiveData
    }

}