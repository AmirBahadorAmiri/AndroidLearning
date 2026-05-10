package com.amirbahadoramiri.androidlearning.views.designpatterns;

import android.os.Bundle;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;

public class DesignPatternTest extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_design_pattern_test);
        setViewCompat();

    }
}