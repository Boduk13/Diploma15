package com.example.bodik13.duplom15.slidingmenu;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.bodik13.duplom15.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PageFragment extends Fragment {

    ///edit texts
    public EditText firstname;
    public EditText lastname;
    public EditText mail;
    public EditText phone;
    public EditText adress;
    public EditText age;
    public EditText drivingExp;
    //---

	public PageFragment(){}


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_page, container, false);
         
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firstname = (EditText) view.findViewById(R.id.firstName);
        lastname = (EditText) view.findViewById(R.id.lastName);
        mail = (EditText) view.findViewById(R.id.mail);
        phone = (EditText) view.findViewById(R.id.phone);
        adress = (EditText) view.findViewById(R.id.adress);
        age = (EditText) view.findViewById(R.id.age);
        drivingExp = (EditText) view.findViewById(R.id.drivingExp);

        try {
            set_user_data();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private void set_user_data() throws JSONException {


        try {
            ///reade from sharPref id
            one_user();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private void one_user() throws JSONException {

        String id = reade_from_sharapref_id();
        Log.d("Page", id);
        String url = "http://tempak.esy.es/API/getTripId?id="+id;

        JSONObject jsonObject = travel(url);






///set edit text data
//        Log.d("Page", jsonObject.getString("id"));
        setAllEditTextData(jsonObject.getString("firstName"),
                jsonObject.getString("lastName"),
                jsonObject.getString("mail"),
                jsonObject.getString("phone"),
                jsonObject.getString("adress"),
                jsonObject.getString("age"),
                jsonObject.getString("drivingExp"));


    }
    private void setAllEditTextData(String name, String lname, String email, String mobe, String adres, String agee, String driExp){
        firstname.setText(name);
        lastname.setText(lname);
        mail.setText(email);
        phone.setText(mobe);
        adress.setText(adres);
        age.setText(agee);
        drivingExp.setText(driExp);
    }


    private JSONObject travel(String url) {
        //----  Р№РѕР±Р°РЅРёР№ РґР¶РµР№СЃРѕРЅ

        JSONObject jsonObject = null;
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
            jsonObject = new JSONObject(str);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    private String reade_from_sharapref_id() {

        SharedPreferences customSharedPreference = getActivity().getSharedPreferences("UserInfo", Activity.MODE_PRIVATE);
        return customSharedPreference.getString("id", "");
    }
}
