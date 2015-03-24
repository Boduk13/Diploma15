//<!-- copyrighted content owned by Android Arena (www.androidarena.co.in)-->
package com.example.bodik13.duplom15;


import android.app.Activity;
import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;


public class Registration extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.registration);



        
    }


}
