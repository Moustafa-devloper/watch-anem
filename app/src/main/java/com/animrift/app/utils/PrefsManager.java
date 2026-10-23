package com.animrift.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {

    private static final String PREFS_NAME = "animrift_prefs";

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(key, defaultValue);
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, defaultValue);
    }
}
