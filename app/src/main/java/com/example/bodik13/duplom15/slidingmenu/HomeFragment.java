package com.example.bodik13.duplom15.slidingmenu;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bodik13.duplom15.BoxAdapter;
import com.example.bodik13.duplom15.Map;
import com.example.bodik13.duplom15.R;
import com.example.bodik13.duplom15.Travel;

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
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    //JSON Node Names
    public String TAG_ID="id";
    public String TAG_startPoint="startPoint";
    public String TAG_finishPoint="finishPoint";
    public String TAG_waypoint1="waypoint1";
    public String TAG_waypoint2="waypoint2";
    public String TAG_waypoint3="waypoint3";
    public String TAG_waypoint4="waypoint4";
    public String TAG_waypoint5="waypoint5";
    public String TAG_price="price";
    public String TAG_seats="seats";
    public String TAG_dataFirst="dataFirst";
    public String TAG_dataLast="dataLast";
    public String TAG_timeFirst="timeFirst";
    public String TAG_timeLast="timeLast";
    public String TAG_firstName="firstName";
    public String TAG_lastName="lastName";
    public String TAG_adress="adress";
    public String TAG_phone="phone";
    public String TAG_mail="mail";
    public String TAG_age="age";
    public String TAG_drivingExp="drivingExp";
    public String TAG_avatar="avatar";
    public String TAG_carBrand="carBrand";
    public String TAG_carModel="carModel";
    public String TAG_carType="carType";
    public String TAG_carPhoto="carPhoto";
    public String TAG_carColor="carColor";
    public String TAG_carSizeBaggage="carSizeBaggage";
    public String TAG_carNumberSeats="carNumberSeats";
    public String TAG_carAnimals="carAnimals";
    public String TAG_carSmoke="carSmoke";
    ListView myTravels;
    public HomeFragment(){}

    ArrayList<Travel> travels_arraylist = new ArrayList<Travel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myTravels = (ListView) view.findViewById(R.id.myHotTrav);
        myTrevels();

        myTravels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myTravels.getSelectedItemId();
                // String selected = ((TextView) view.findViewById(R.id.firstName)).getText().toString();
                String id_travel = ((TextView) view.findViewById(R.id.id_travel)).getText().toString();




                Travel[] mStringArray = new Travel[travels_arraylist.size()];
                mStringArray = travels_arraylist.toArray(mStringArray);

                Travel test_model = null;
                for(int i = 0; i < mStringArray.length ; i++){

                    if (id_travel == mStringArray[i].TAG_ID) {
                        test_model = new Travel(mStringArray[i].TAG_ID,
                                mStringArray[i].TAG_startPoint,
                                mStringArray[i].TAG_finishPoint,
                                mStringArray[i].TAG_waypoint1,
                                mStringArray[i].TAG_waypoint2,
                                mStringArray[i].TAG_waypoint3,
                                mStringArray[i].TAG_waypoint4,
                                mStringArray[i].TAG_waypoint5,
                                mStringArray[i].TAG_price,
                                mStringArray[i].TAG_seats,
                                mStringArray[i].TAG_dataFirst,
                                mStringArray[i].TAG_dataLast,
                                mStringArray[i].TAG_timeFirst,
                                mStringArray[i].TAG_timeLast,
                                mStringArray[i].TAG_firstName,
                                mStringArray[i].TAG_lastName,
                                mStringArray[i].TAG_adress,
                                mStringArray[i].TAG_phone,
                                mStringArray[i].TAG_mail,
                                mStringArray[i].TAG_age,
                                mStringArray[i].TAG_drivingExp,
                                mStringArray[i].TAG_avatar,
                                mStringArray[i].TAG_carBrand,
                                mStringArray[i].TAG_carModel,
                                mStringArray[i].TAG_carType,
                                mStringArray[i].TAG_carPhoto,
                                mStringArray[i].TAG_carColor,
                                mStringArray[i].TAG_carSizeBaggage,
                                mStringArray[i].TAG_carNumberSeats,
                                mStringArray[i].TAG_carAnimals,
                                mStringArray[i].TAG_carSmoke
                        );
                    }
                }

                showDetailOfTravel(test_model);

                Log.d("JSON", test_model.TAG_ID + " " + test_model.TAG_lastName);

            }
        });



    }



    public void myTrevels(){

        //clean listview

        //---



        String id = reade_from_sharapref_id();
        String url = "http://tempak.esy.es/API/getTripsUser?id="+id;

        JSONArray jsonArray = travels(url);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject;
            try {
                jsonObject = jsonArray.getJSONObject(i);

                Travel travel = new Travel(jsonObject.getString(TAG_ID),
                        jsonObject.getString(TAG_startPoint),
                        jsonObject.getString(TAG_finishPoint),
                        jsonObject.getString(TAG_waypoint1),
                        jsonObject.getString(TAG_waypoint2),
                        jsonObject.getString(TAG_waypoint3),
                        jsonObject.getString(TAG_waypoint4),
                        jsonObject.getString(TAG_waypoint5),
                        jsonObject.getString(TAG_price),
                        jsonObject.getString(TAG_seats),
                        jsonObject.getString(TAG_dataFirst),
                        jsonObject.getString(TAG_dataLast),
                        jsonObject.getString(TAG_timeFirst),
                        jsonObject.getString(TAG_timeLast),
                        jsonObject.getString(TAG_firstName),
                        jsonObject.getString(TAG_lastName),
                        jsonObject.getString(TAG_adress),
                        jsonObject.getString(TAG_phone),
                        jsonObject.getString(TAG_mail),
                        jsonObject.getString(TAG_age),
                        jsonObject.getString(TAG_drivingExp),
                        jsonObject.getString(TAG_avatar),
                        jsonObject.getString(TAG_carBrand),
                        jsonObject.getString(TAG_carModel),
                        jsonObject.getString(TAG_carType),
                        jsonObject.getString(TAG_carPhoto),
                        jsonObject.getString(TAG_carColor),
                        jsonObject.getString(TAG_carSizeBaggage),
                        jsonObject.getString(TAG_carNumberSeats),
                        jsonObject.getString(TAG_carAnimals),
                        jsonObject.getString(TAG_carSmoke)
                );

                travels_arraylist.add(travel);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        };
        BoxAdapter boxAdapter = new BoxAdapter(getActivity().getApplicationContext(), travels_arraylist);
        myTravels.setAdapter(boxAdapter);


    }
    private JSONArray travels (String url){
        //----  йобаний джейсон

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


        try{
            jArray = new JSONArray(str);

        } catch ( JSONException e) {
            e.printStackTrace();
        }
        return jArray;
    }


    private void showDetailOfTravel(Travel test_model) {

        Intent intent = new Intent(getActivity(), Map.class);

        intent.putExtra(String.valueOf(R.string.id), test_model.TAG_ID);
        intent.putExtra(String.valueOf(R.string.firstName), test_model.TAG_firstName);
        intent.putExtra(String.valueOf(R.string.lastName), test_model.TAG_lastName);
        intent.putExtra(String.valueOf(R.string.age), test_model.TAG_age);
        intent.putExtra(String.valueOf(R.string.drivingExp), test_model.TAG_drivingExp);
        intent.putExtra(String.valueOf(R.string.phone), test_model.TAG_phone);
        intent.putExtra(String.valueOf(R.string.prompt_email), test_model.TAG_mail);
        intent.putExtra(String.valueOf(R.string.startPoint), test_model.TAG_startPoint);
        intent.putExtra(String.valueOf(R.string.find_travel), test_model.TAG_finishPoint);
        intent.putExtra(String.valueOf(R.string.seats), test_model.TAG_seats);
        intent.putExtra(String.valueOf(R.string.price), test_model.TAG_price);
        intent.putExtra(String.valueOf(R.string.price), test_model.TAG_price);
        intent.putExtra(String.valueOf(R.string.dataFirst), test_model.TAG_dataFirst);
        intent.putExtra(String.valueOf(R.string.timeFirst), test_model.TAG_timeFirst);
        intent.putExtra(String.valueOf(R.string.waypoint1), test_model.TAG_waypoint1);
        intent.putExtra(String.valueOf(R.string.waypoint2), test_model.TAG_waypoint2);
        intent.putExtra(String.valueOf(R.string.waypoint3), test_model.TAG_waypoint3);
        intent.putExtra(String.valueOf(R.string.waypoint4), test_model.TAG_waypoint4);
        intent.putExtra(String.valueOf(R.string.waypoint5), test_model.TAG_waypoint5);


        startActivity(intent);
    }

    private String reade_from_sharapref_id() {

        SharedPreferences customSharedPreference = getActivity().getSharedPreferences("UserInfo", Activity.MODE_PRIVATE);
        return customSharedPreference.getString("id", "");
    }
}
