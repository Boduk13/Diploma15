package com.example.bodik13.duplom15.slidingmenu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.bodik13.duplom15.BoxAdapter;

import com.example.bodik13.duplom15.Map;
import com.example.bodik13.duplom15.R;
import com.example.bodik13.duplom15.Travel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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

public class Find_travel extends Fragment {
    //URL to get JSON Array
    String url;

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


    EditText start = null;
    EditText finish = null;
    Button search = null;
    Button back = null;
    Button on_map = null;
    ListView listViewTravels = null;



    LinearLayout hide_laya = null;
    LinearLayout main_laya = null;
    //TextView date_start = null;
    ArrayList<Travel> travels_arraylist = new ArrayList<Travel>();


    ///google maps
    GoogleMap mGoogleMap = null;
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);
    private GoogleMap map;

	public Find_travel(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_find_travel, container, false);
       /* View v = inflater.inflate(R.layout.fragment_find_travel, container, false);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
                .title("Hamburg"));
        Marker kiel = map.addMarker(new MarkerOptions()
                .position(KIEL)
                .title("Kiel")
                .snippet("Kiel is cool")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_launcher)));

        // Move the camera instantly to hamburg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);*/
        return rootView;
    }
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        // save views as variables in this method
        // "view" is the one returned from onCreateView
        start = (EditText) view.findViewById(R.id.start);
        finish = (EditText) view.findViewById(R.id.finish);
        search = (Button) view.findViewById(R.id.search_btn);
        listViewTravels = (ListView) view.findViewById(R.id.list_travels);
        back = (Button) view.findViewById(R.id.btn_back);
        on_map = (Button) view.findViewById(R.id.on_map);
        hide_laya = (LinearLayout) view.findViewById(R.id.hide_layaut);
        main_laya = (LinearLayout) view.findViewById(R.id.main_layout);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showAllElementsSearch();

            }
        });

        on_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Map.class);
                startActivity(intent);
            }
        });

        //textView = (TextView) view.findViewById(R.id.txtLabel);

        listViewTravels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewTravels.getSelectedItemId();
                String selected = ((TextView) view.findViewById(R.id.firstName)).getText().toString();
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

                Log.d("JSON", test_model.TAG_ID + " "+ test_model.TAG_lastName);

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //clean listview
                travels_arraylist.clear();
                //---


                String sStart = start.getText().toString();
                String sFinidh = finish.getText().toString();

                url = "http://tempak.esy.es/API/getTrips?first=" + sStart + "&second=" + sFinidh;

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


                       // Log.d("JSON", jsonObject.getString(TAG_ID) + jsonObject.getString(TAG_TIME_FIRST) + jsonObject.getString(TAG_FIRST_NAME) + jsonObject.getString(TAG_LAST_NAME)
                       //         + jsonObject.getString(TAG_PRICE) + jsonObject.getString(TAG_SEATE) + jsonObject.getString(TAG_carBrand) + jsonObject.getString(TAG_carModel));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                };
                BoxAdapter boxAdapter = new BoxAdapter(getActivity().getApplicationContext(), travels_arraylist);
                listViewTravels.setAdapter(boxAdapter);



            }
        });
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



    private void cleareData(){
        travels_arraylist.clear();
        listViewTravels = null;

        //
    }
    private void showAllElementsSearch(){

        main_laya.setVisibility(View.VISIBLE);
        hide_laya.setVisibility(View.GONE);

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



}




