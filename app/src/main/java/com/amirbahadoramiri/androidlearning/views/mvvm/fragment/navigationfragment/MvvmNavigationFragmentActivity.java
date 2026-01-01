package com.amirbahadoramiri.androidlearning.views.mvvm.fragment.navigationfragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.databinding.ActivityMvvmNavigationLivedataBinding;

public class MvvmNavigationFragmentActivity extends BaseActivity {

    ActivityMvvmNavigationLivedataBinding binding;
    NavHostFragment navHostFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_navigation_livedata);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.getNavController());

    }

}
