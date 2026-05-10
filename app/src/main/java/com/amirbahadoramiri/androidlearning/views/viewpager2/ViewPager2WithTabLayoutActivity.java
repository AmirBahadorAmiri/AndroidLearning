package com.amirbahadoramiri.androidlearning.views.viewpager2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.views.mvvm.fragment.FragmentHome;
import com.amirbahadoramiri.androidlearning.views.mvvm.fragment.FragmentProfile;
import com.amirbahadoramiri.androidlearning.views.mvvm.fragment.FragmentSearch;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2WithTabLayoutActivity extends BaseActivity {

    ViewPager2 viewpager2;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeColor();
        setContentView(R.layout.activity_view_pager2_with_tab_layout);
        setViewCompat();

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(FragmentHome.getInstance());
        fragmentList.add(FragmentProfile.getInstance());
        fragmentList.add(FragmentSearch.getInstance());

        String[] names = new String[]{"خانه","پروفایل","جستوجو"};
        int[] icons = new int[]{R.drawable.round_home_24,R.drawable.round_person_24,R.drawable.round_search_24};

        viewpager2 = findViewById(R.id.viewpager2);
        tabLayout = findViewById(R.id.tabLayout);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        adapter.setFragmentList(fragmentList);
        viewpager2.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewpager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                tab.setText(names[i]);
                tab.setIcon(icons[i]);
            }
        });
        tabLayoutMediator.attach();

    }
}