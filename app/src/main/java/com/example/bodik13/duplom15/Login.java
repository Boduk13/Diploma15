package com.example.bodik13.duplom15;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.bodik13.duplom15.slidingmenu.MainActivity;


public class Login extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        super.onCreate(savedInstanceState);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.login);








        //registration text - set onclicklistener
        TextView registration = (TextView) findViewById(R.id.reg);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registration = new Intent(Login.this, Registration.class);
                startActivity(registration);
            }
        });
        //-----

        //button login
        Button btn_login = (Button) findViewById(R.id.login_btn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maine = new Intent(Login.this, MainActivity.class);
               startActivity(maine);
            }
        });

        //---

        //button facebook for test mysql
        Button btn_test_mysql = (Button) findViewById(R.id.facebook);
        btn_test_mysql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        //---
    }



}
