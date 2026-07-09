package com.amirbahadoramiri.androidlearning;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class AppManager extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

    }
}
