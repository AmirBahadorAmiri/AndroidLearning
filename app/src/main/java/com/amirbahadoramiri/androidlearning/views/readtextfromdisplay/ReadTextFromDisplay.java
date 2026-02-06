package com.amirbahadoramiri.androidlearning.views.readtextfromdisplay;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;

public class ReadTextFromDisplay extends BaseActivity {

    AppCompatTextView txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_readtextfromdisplay);
        setViewCompat();

        if (!isAccessibilityEnabled(this, MyAccessibilityService.class)) {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName())
            );
            startActivity(intent);
        }

        txt = findViewById(R.id.txt);

        txt.setOnClickListener(v -> {
            txt.setText("hi");
        });

    }

    public static boolean isAccessibilityEnabled(Context context, Class<?> service) {
        String enabledServices = Settings.Secure.getString(
                context.getContentResolver(),
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        );

        if (enabledServices == null) return false;

        String serviceId = context.getPackageName() + "/" + service.getName();
        return enabledServices.contains(serviceId);
    }


}
