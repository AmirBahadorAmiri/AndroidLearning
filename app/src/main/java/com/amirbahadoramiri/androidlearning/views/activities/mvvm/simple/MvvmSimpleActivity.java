package com.amirbahadoramiri.androidlearning.views.activities.mvvm.simple;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.databinding.ActivityMvvmSimpleBinding;
import com.amirbahadoramiri.androidlearning.models.User;

public class MvvmSimpleActivity extends BaseActivity {

    ActivityMvvmSimpleBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm_simple);
        setViewCompat();

        binding.setUser(new User("HassanKachal","HassanKachal@gmail.com"));

    }
}
