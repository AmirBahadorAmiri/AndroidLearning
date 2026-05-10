package com.amirbahadoramiri.androidlearning.views.useriidentification;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;

import java.util.UUID;

public class UserIdentificationActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_useridentification);
        setViewCompat();

        AppCompatTextView uuid = findViewById(R.id.uuid);
        AppCompatTextView androidid = findViewById(R.id.androidid);
        AppCompatTextView hardware = findViewById(R.id.hardware);

        uuid.setText("unique id: " + getUniqueID());
        hardware.setText("hardware id: " + getHardware());
        androidid.setText("android id: " + getAndroidId(this));

    }

    public String getUniqueID() {
        return UUID.randomUUID().toString();
    }

    public String getBootloader() {
        return Build.BOOTLOADER;
    }

    public String getHardware() {
        return Build.HARDWARE;
    }

    public String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
