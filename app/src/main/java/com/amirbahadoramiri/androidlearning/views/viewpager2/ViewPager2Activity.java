package com.amirbahadoramiri.androidlearning.views.viewpager2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.views.mvvm.fragment.FragmentHome;
import com.amirbahadoramiri.androidlearning.views.mvvm.fragment.FragmentProfile;
import com.amirbahadoramiri.androidlearning.views.mvvm.fragment.FragmentSearch;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2Activity extends BaseActivity {

    ViewPager2 viewpager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_view_pager2);
        setViewCompat();

        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(FragmentHome.getInstance());
        fragmentList.add(FragmentProfile.getInstance());
        fragmentList.add(FragmentSearch.getInstance());

        viewpager2 = findViewById(R.id.viewpager2);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        adapter.setFragmentList(fragmentList);
        viewpager2.setAdapter(adapter);


    }
}