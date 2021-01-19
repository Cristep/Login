package com.cristianmorales.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.cristianmorales.login.Utils.Util;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //ImageView image = (ImageView) findViewById(R.id.splashImage);
        //Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        //image.startAnimation(animation);

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        Intent intentLogin = new Intent(this, LoginActivity.class);
        Intent intentMain = new Intent(this, MainActivity.class);
        if (!TextUtils.isEmpty(Util.getUserMailPrefs(prefs)) && !TextUtils.isEmpty(Util.getUserPassPrefs(prefs))) {
            startActivity(intentMain);
        } else {
            startActivity(intentLogin);
        }
        finish();
    }
}