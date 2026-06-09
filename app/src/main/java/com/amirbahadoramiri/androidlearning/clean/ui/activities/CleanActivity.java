package com.amirbahadoramiri.androidlearning.clean.ui.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;

public class CleanActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeColor();
        setContentView(R.layout.activity_clean);
        setViewCompat();
    }
}
