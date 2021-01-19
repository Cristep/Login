package com.cristianmorales.login.Utils;

import android.content.SharedPreferences;

public class Util {

    public static String getUserMailPrefs(SharedPreferences preferences){
        return preferences.getString("email","");
    }

    public static String getUserPassPrefs(SharedPreferences preferences){
        return preferences.getString("pass","");
    }
}
