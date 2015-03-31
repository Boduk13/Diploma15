package com.example.bodik13.duplom15.slidingmenu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.bodik13.duplom15.BoxAdapter;
import com.example.bodik13.duplom15.JSONParser;

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
    final String TAG_ID = "id";
    final String TAG_TIME_FIRST = "timeFirst";
    final String TAG_FIRST_NAME = "firstName";
    final String TAG_LAST_NAME = "lastName";
    final String TAG_PRICE = "price";
    final String TAG_SEATE = "seats";
    final String TAG_carBrand = "carBrand";
    final String TAG_carModel = "carModel";


    EditText start = null;
    EditText finish = null;
    Button search = null;
    ListView listViewTravels = null;
    ArrayList<Travel> travels_arraylist = new ArrayList<Travel>();

	public Find_travel(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_find_travel, container, false);
        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // save views as variables in this method
        // "view" is the one returned from onCreateView
        start = (EditText) view.findViewById(R.id.start);
        finish = (EditText) view.findViewById(R.id.finish);
        search = (Button) view.findViewById(R.id.search_btn);
        listViewTravels = (ListView) view.findViewById(R.id.list_travels);

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
                        test_model = new Travel(mStringArray[i].TAG_ID, mStringArray[i].TAG_TIME_FIRST, mStringArray[i].TAG_FIRST_NAME,
                                mStringArray[i].TAG_LAST_NAME, mStringArray[i].TAG_PRICE, mStringArray[i].TAG_SEATE, mStringArray[i].TAG_carBrand, mStringArray[i].TAG_carModel);
                    }
                }

                ///передати test_model на новий фрагмент
                //-------------
                /////
                Fragment person = new PagesFragment();
                if (person != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frame_container, person).commit();



                }

                Log.d("JSON", test_model.TAG_ID + " "+ test_model.TAG_LAST_NAME);




            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sStart = start.getText().toString();
                String sFinidh = finish.getText().toString();


                url = "http://tempak.esy.es/ajax/getTrip3?first=" + sStart + "&second=" + sFinidh;

                JSONArray jsonArray = travels(url);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject;
                    try {
                        jsonObject = jsonArray.getJSONObject(i);

                        Travel travel = new Travel(jsonObject.getString(TAG_ID),
                                jsonObject.getString(TAG_TIME_FIRST),
                                jsonObject.getString(TAG_FIRST_NAME),
                                jsonObject.getString(TAG_LAST_NAME),
                                jsonObject.getString(TAG_PRICE),
                                jsonObject.getString(TAG_SEATE),
                                jsonObject.getString(TAG_carBrand),
                                jsonObject.getString(TAG_carModel));

                        travels_arraylist.add(travel);


                        Log.d("JSON", jsonObject.getString(TAG_ID) + jsonObject.getString(TAG_TIME_FIRST) + jsonObject.getString(TAG_FIRST_NAME) + jsonObject.getString(TAG_LAST_NAME)
                                + jsonObject.getString(TAG_PRICE) + jsonObject.getString(TAG_SEATE) + jsonObject.getString(TAG_carBrand) + jsonObject.getString(TAG_carModel));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                };
                BoxAdapter boxAdapter = new BoxAdapter(getActivity().getApplicationContext(), travels_arraylist);
                listViewTravels.setAdapter(boxAdapter);

            }
        });
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




