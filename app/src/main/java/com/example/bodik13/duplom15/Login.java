package com.example.bodik13.duplom15;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bodik13.duplom15.slidingmenu.MainActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class Login extends ActionBarActivity {
    EditText login_user;
    EditText login_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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

                Log.d("Login", "click btn_login");
                if (login()) {
                    Log.d("Login", "from ushualy");
                    finish();
                    Intent maine = new Intent(Login.this, MainActivity.class);
                    startActivity(maine);
                }

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

        login_user = (EditText) findViewById(R.id.login_user);
        login_pass = (EditText) findViewById(R.id.pass_user);

        //---

        login_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                is_Valid_Person_Name(login_user);

            }
        });

        login_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                is_Valid_Person_Password(login_pass);
}
        });

    }

    private boolean login() {

        ArrayList<User> users = all_users();

        String login = login_user.getText().toString();
        String password = login_pass.getText().toString();

        for (User item : users) {
            Log.d("Login", item.getUser_name() + item.getUser_pass());
            Log.d("Login", "=" + login + password + "=");
            if (item.getUser_name().equals(login) && item.getUser_pass().equals(password)) {
                write_to_sharapref(item.getUser_name(), item.getUser_pass(), item.getUser_id());
                return true;
            }
        }

        return false;
    }

    ;

    private ArrayList all_users() {
        ArrayList<User> users_arraylist = new ArrayList<User>();


        String url = "http://tempak.esy.es/API/getUsers";

        JSONArray jsonArray = users(url);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject;
            User user = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);

                user = new User(jsonObject.getString("id"), jsonObject.getString("mail"), jsonObject.getString("password"));


            } catch (JSONException e) {
                e.printStackTrace();
            }
            users_arraylist.add(user);


        }
        return users_arraylist;
    }

    private JSONArray users(String url) {
        //----  Р№РѕР±Р°РЅРёР№ РґР¶РµР№СЃРѕРЅ

        JSONArray jArray = null;
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll()
                .penaltyLog()
                .build());

        String str = "";
        HttpResponse response;
        HttpClient myClient = new DefaultHttpClient();
        HttpPost myConnection = new HttpPost(url);

        try {
            response = myClient.execute(myConnection);
            str = EntityUtils.toString(response.getEntity(), "UTF-8");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            jArray = new JSONArray(str);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jArray;
    }

    private void write_to_sharapref(String login, String password, String id) {
        SharedPreferences settings = getSharedPreferences("UserInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Username", login);
        editor.putString("Password", password);
        editor.putString("id", id);
        editor.commit();
    }

    public void is_Valid_Person_Name(EditText edt) throws NumberFormatException {
        if (edt.getText().toString().equals("")) {
            edt.setError("Введіть електронну адресу для входу!");
        } else

        {
            if (!edt.getText().toString().matches("[a-zA-Z0-9]+[a-zA-Z0-9._]*[a-zA-Z0-9]*+@[a-zA-Z0-9]*[.]([a-z][a-z]|[a-z][a-z][a-z])")) {
                edt.setError("Вашу електронну адресу введено невірно, перевірте і повторіть спробу!");

            }
        }

    }

    public void is_Valid_Person_Password(EditText edt) throws NumberFormatException {
        if (edt.getText().toString().equals("")) {
        } else

        {
            if (!edt.getText().toString().matches("^(?=.*[0-9].*[0-9])[a-zA-Z0-9]{3,}$")) {
                edt.setError("Пароль некоректний!");
            }
        }


    }

}