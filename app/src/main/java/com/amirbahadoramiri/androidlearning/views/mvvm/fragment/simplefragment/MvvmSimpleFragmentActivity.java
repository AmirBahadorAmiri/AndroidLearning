package com.amirbahadoramiri.androidlearning.views.mvvm.fragment.simplefragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.databinding.ActivityMvvmSimpleFragmentBinding;
import com.amirbahadoramiri.androidlearning.views.mvvm.fragment.FragmentHome;
import com.amirbahadoramiri.androidlearning.views.mvvm.fragment.FragmentNotification;
import com.amirbahadoramiri.androidlearning.views.mvvm.fragment.FragmentProfile;
import com.amirbahadoramiri.androidlearning.views.mvvm.fragment.FragmentSearch;

public class MvvmSimpleFragmentActivity extends BaseActivity {

    ActivityMvvmSimpleFragmentBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_simple_fragment);

        binding.bottomNavigation.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.home) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, FragmentHome.getInstance()).commit();
            }
            if (menuItem.getItemId() == R.id.search) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, FragmentSearch.getInstance()).commit();
            }
            if (menuItem.getItemId() == R.id.notification) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, FragmentNotification.getInstance()).commit();
            }
            if (menuItem.getItemId() == R.id.profile) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, FragmentProfile.getInstance()).commit();
            }
            return true;
        });

    }

}
