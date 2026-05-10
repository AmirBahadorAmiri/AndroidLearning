package com.amirbahadoramiri.androidlearning.views.nestedscrollview;

import android.os.Bundle;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.google.android.material.appbar.MaterialToolbar;

public class CollapsingToolBar extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeColor();
        setContentView(R.layout.activity_collapsing_tool_bar);
        setViewCompat();

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}