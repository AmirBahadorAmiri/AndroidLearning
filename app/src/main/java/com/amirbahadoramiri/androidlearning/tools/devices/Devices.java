package com.amirbahadoramiri.androidlearning.tools.devices;

import android.content.Context;
import android.provider.Settings;

/*
 *   Created by: @AmirBahadorAmiri
 *   Created at: 2026-06-12 16:06:52
 *   Github: https://github.com/AmirBahadorAmiri
 */

public class Devices {
    public static String getUniqueId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}