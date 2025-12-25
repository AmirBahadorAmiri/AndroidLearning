package com.amirbahadoramiri.androidlearning.tools.logger;

import android.util.Log;

public class Logger {
    private static final String DEBUG = "DEBUG";
    private static final String INFO = "INFO";
    private static final String WARNING = "WARNING";
    private static final String ERROR = "ERROR";
    private static final String VERBOSE = "VERBOSE";
    private static final String WTF = "WTF";

    public static void loge(String msg) {
        Log.e(ERROR, msg);
    }
    public static void loge(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void logd(String msg) {
        Log.d(DEBUG, msg);
    }
    public static void logd(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void logi(String msg) {
        Log.i(INFO, msg);
    }
    public static void logi(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void logw(String msg) {
        Log.w(WARNING, msg);
    }
    public static void logw(String tag, String msg) {
        Log.w(tag, msg);
    }

    public static void logv(String msg) {
        Log.v(VERBOSE, msg);
    }
    public static void logv(String tag, String msg) {
        Log.v(tag, msg);
    }

    public static void logwtf(String msg) {
        Log.wtf(WTF, msg);
    }
    public static void logwtf(String tag, String msg) {
        Log.wtf(tag, msg);
    }

    public static void log_print(int priority, String msg) {
        Log.println(priority, DEBUG, msg);
    }
    public static void log_print(String tag, int priority, String msg) {
        Log.println(priority, tag, msg);
    }
}