package com.example.bodik13.duplom15;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.bodik13.duplom15.slidingmenu.MainActivity;

public class SplashScreen extends Activity {

    private static final int TIME = 4 * 1000;// 4 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                if (!reade_from_sharapref().equals("")) {
                    Intent maine = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(maine);
                } else {
                    Intent maine2 = new Intent(SplashScreen.this, Login.class);
                    startActivity(maine2);
                }


                SplashScreen.this.finish();

                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        }, TIME);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, TIME);

    }


    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    private String reade_from_sharapref() {
        SharedPreferences settings = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String id = null;
        id = settings.getString("id", "").toString();
        return id;
    }
}
