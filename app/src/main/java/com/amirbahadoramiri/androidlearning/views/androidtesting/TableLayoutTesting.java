package com.amirbahadoramiri.androidlearning.views.androidtesting;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;

public class TableLayoutTesting extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_table_layout);
        setViewCompat();
    }
}
