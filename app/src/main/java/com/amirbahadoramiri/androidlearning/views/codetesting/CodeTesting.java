package com.amirbahadoramiri.androidlearning.views.codetesting;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import androidx.annotation.Nullable;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;

public class CodeTesting extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_codetesting);
        setViewCompat();

        Runtime.getRuntime().availableProcessors();

        HandlerThread handlerThread = new HandlerThread("TestHandlerThread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        Handler handler = new Handler(looper);
        handler.post(() -> {

        });

    }
}
