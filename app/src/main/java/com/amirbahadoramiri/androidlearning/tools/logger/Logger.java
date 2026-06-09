package com.amirbahadoramiri.androidlearning.tools.logger;

import android.util.Log;

import java.util.Objects;

public class Logger {
    private static final String DEBUG = "DEBUG";
    private static final String INFO = "INFO";
    private static final String WARNING = "WARNING";
    private static final String ERROR = "ERROR";
    private static final String VERBOSE = "VERBOSE";
    private static final String WTF = "WTF";
    private static final String ANY = "ANY";

    public static <T> void any(T any) {
        Log.d(ANY, any.toString());
    }
    public static <T> void any(String tag, T any) {
        Log.d(tag, any.toString());
    }

    public static void error(String msg) {
        Log.e(ERROR, msg);
    }
    public static void error(String tag, String msg) {
        Log.e(tag, msg);
    }
    public static void error(Throwable e) {
        Log.e(ERROR, Objects.requireNonNull(e.getMessage()));
    }
    public static void error(String tag, Throwable e) {
        Log.e(tag, Objects.requireNonNull(e.getMessage()));
    }

    public static void debug(String msg) {
        Log.d(DEBUG, msg);
    }
    public static void debug(String tag, String msg) {
        Log.d(tag, msg);
    }
    public static void debug(Throwable e) {
        Log.d(DEBUG, Objects.requireNonNull(e.getMessage()));
    }
    public static void debug(String tag, Throwable e) {
        Log.d(tag, Objects.requireNonNull(e.getMessage()));
    }

    public static void info(String msg) {
        Log.i(INFO, msg);
    }
    public static void info(String tag, String msg) {
        Log.i(tag, msg);
    }
    public static void info(Throwable e) {
        Log.i(INFO, Objects.requireNonNull(e.getMessage()));
    }
    public static void info(String tag, Throwable e) {
        Log.i(tag, Objects.requireNonNull(e.getMessage()));
    }

    public static void warning(String msg) {
        Log.w(WARNING, msg);
    }
    public static void warning(String tag, String msg) {
        Log.w(tag, msg);
    }
    public static void warning(Throwable e) {
        Log.w(WARNING, Objects.requireNonNull(e.getMessage()));
    }
    public static void warning(String tag, Throwable e) {
        Log.w(tag, Objects.requireNonNull(e.getMessage()));
    }

    public static void verbose(String msg) {
        Log.v(VERBOSE, msg);
    }
    public static void verbose(String tag, String msg) {
        Log.v(tag, msg);
    }
    public static void verbose(Throwable e) {
        Log.v(VERBOSE, Objects.requireNonNull(e.getMessage()));
    }
    public static void verbose(String tag, Throwable e) {
        Log.v(tag, Objects.requireNonNull(e.getMessage()));
    }

    public static void wtf(String msg) {
        Log.wtf(WTF, msg);
    }
    public static void wtf(String tag, String msg) {
        Log.wtf(tag, msg);
    }
    public static void wtf(Throwable e) {
        Log.wtf(WTF, Objects.requireNonNull(e.getMessage()));
    }
    public static void wtf(String tag, Throwable e) {
        Log.wtf(tag, Objects.requireNonNull(e.getMessage()));
    }

    public static void print(int priority, String msg) {
        Log.println(priority, DEBUG, msg);
    }
    public static void print(int priority, String tag, String msg) {
        Log.println(priority, tag, msg);
    }
    public static void print(int priority,Throwable e) {
        Log.println(priority,DEBUG, Objects.requireNonNull(e.getMessage()));
    }
    public static void print(int priority, String tag, Throwable e) {
        Log.println(priority,tag, Objects.requireNonNull(e.getMessage()));
    }

}