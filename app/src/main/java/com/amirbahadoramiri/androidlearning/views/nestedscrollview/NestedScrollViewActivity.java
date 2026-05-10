package com.amirbahadoramiri.androidlearning.views.nestedscrollview;

import android.os.Bundle;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.google.android.material.appbar.MaterialToolbar;

public class NestedScrollViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeColor();
        setContentView(R.layout.activity_nested_scroll_view);
        setViewCompat();

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
}