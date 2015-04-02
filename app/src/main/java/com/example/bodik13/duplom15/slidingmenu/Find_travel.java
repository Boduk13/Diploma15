package com.example.bodik13.duplom15.slidingmenu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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
import java.util.ArrayList;

public class Find_travel extends Fragment {
    //URL to get JSON Array
    String url;

    //JSON Node Names
    public String TAG_ID="id";
    public String TAG_startPoint="startPoint";
    public String TAG_finishPoint="finishPoint";
    public String TAG_intermedientePoints="intermedientePoints";
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
    ListView listViewTravels = null;

    //view detail travel
    TextView name = null;
    TextView sunname = null;
    TextView age = null;
    TextView driveExp = null;
    TextView phoneNumber = null;
    TextView email = null;

    TextView start_trav = null;
    TextView end_trav = null;
    TextView intermedientePoints = null;
    TextView seats = null;
    TextView price = null;
    TextView date = null;
    TextView time = null;

    LinearLayout hide_laya = null;
    LinearLayout main_laya = null;
    //TextView date_start = null;
    ArrayList<Travel> travels_arraylist = new ArrayList<Travel>();

	public Find_travel(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_find_travel, container, false);
        return rootView;
    }
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        // save views as variables in this method
        // "view" is the one returned from onCreateView
        start = (EditText) view.findViewById(R.id.start);
        start = (EditText) view.findViewById(R.id.start);
        finish = (EditText) view.findViewById(R.id.finish);
        search = (Button) view.findViewById(R.id.search_btn);
        listViewTravels = (ListView) view.findViewById(R.id.list_travels);
        back = (Button) view.findViewById(R.id.btn_back);
        hide_laya = (LinearLayout) view.findViewById(R.id.hide_layaut);
        main_laya = (LinearLayout) view.findViewById(R.id.main_layout);
        ///_detail travel
        start_trav = (TextView) view.findViewById(R.id.start_trav);
        end_trav = (TextView) view.findViewById(R.id.end_trav);
        intermedientePoints = (TextView) view.findViewById(R.id.intermedientePoints);
        seats = (TextView) view.findViewById(R.id.seats);
        price = (TextView) view.findViewById(R.id.price);
        date = (TextView) view.findViewById(R.id.date);
        time = (TextView) view.findViewById(R.id.time);
        name = (TextView) view.findViewById(R.id.name);
        sunname = (TextView) view.findViewById(R.id.sunname);
        age = (TextView) view.findViewById(R.id.age);
        driveExp = (TextView) view.findViewById(R.id.drivingExp);
        phoneNumber = (TextView) view.findViewById(R.id.phone_number);
        email = (TextView) view.findViewById(R.id.email);

        //---


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllTextViews();
                showAllElementsSearch();

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
                                mStringArray[i].TAG_intermedientePoints,
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
                clearAllTextViews();
                viewAllDetailPerson(test_model);
                //back.setVisibility(View.VISIBLE);

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
                                jsonObject.getString(TAG_intermedientePoints),
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

    private void clearAllTextViews() {
        name.setText("Ім'я: ");
        sunname.setText("Прізвище: ");
        age.setText("Вік: ");
        driveExp.setText("Стаж водіння: ");
        phoneNumber.setText("Номер телефону: ");
        email.setText("E-Mail: ");
        start_trav.setText("Початок маршруту: ");
        end_trav.setText("Кінець маршруту: ");
        intermedientePoints.setText("Проміжні пункти: ");
        seats.setText("Кількість місць: ");
        price.setText("Ціна за місце: ");
        date.setText("Дата відправлення: ");
        time.setText("Час відправлення: ");
    }

    private void viewAllDetailPerson(Travel testModel) {

        main_laya.setVisibility(View.GONE);
        hide_laya.setVisibility(View.VISIBLE);
        //set text views
        name.setText(name.getText().toString()+testModel.TAG_firstName);
        sunname.setText(sunname.getText().toString()+testModel.TAG_lastName);
        age.setText(age.getText().toString()+testModel.TAG_age);
        driveExp.setText(driveExp.getText().toString()+testModel.TAG_drivingExp);
        phoneNumber.setText(phoneNumber.getText().toString()+testModel.TAG_phone);
        email.setText(email.getText().toString()+testModel.TAG_mail);

        start_trav.setText(start_trav.getText().toString()+testModel.TAG_startPoint);
        end_trav.setText(end_trav.getText().toString()+testModel.TAG_finishPoint);
        intermedientePoints.setText(intermedientePoints.getText().toString()+testModel.TAG_intermedientePoints);
        seats.setText(seats.getText().toString()+testModel.TAG_seats);
        price.setText(price.getText().toString()+testModel.TAG_price);
        date.setText(date.getText().toString()+testModel.TAG_dataFirst);
        time.setText(time.getText().toString() + testModel.TAG_timeFirst);

        //-end set textviews



    }


    private void hideAllElementsSearch(){
        //hide main layout
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




