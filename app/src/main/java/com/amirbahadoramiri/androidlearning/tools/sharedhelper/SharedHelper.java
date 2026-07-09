package com.amirbahadoramiri.androidlearning.tools.sharedhelper;

import android.content.Context;
import android.content.SharedPreferences;

/*
 *   Created by: @AmirBahadorAmiri
 *   Created at: 2026-06-12 16:59:29
 *   Github: https://github.com/AmirBahadorAmiri
 */

public class SharedHelper {

    private final String SHARED_KEY = "SHARED_KEY";;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor sharedPreferencesEditor;

    public SharedHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
    }

    public String readString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public int readInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public Boolean readBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public Long readLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public void insert(String key, String value) {
        sharedPreferencesEditor.putString(key, value);
        sharedPreferencesEditor.apply();
    }

    public void insert(String key, Boolean value) {
        sharedPreferencesEditor.putBoolean(key, value);
        sharedPreferencesEditor.apply();
    }

    public void insert(String key, Long value) {
        sharedPreferencesEditor.putLong(key, value);
        sharedPreferencesEditor.apply();
    }

    public void insert(String key, int value) {
        sharedPreferencesEditor.putInt(key, value);
        sharedPreferencesEditor.apply();
    }
}