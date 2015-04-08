package com.example.bodik13.duplom15;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Map extends Activity  {

    private static final LatLng LOWER_MANHATTAN = new LatLng(40.722543, -73.998585);
    private static final LatLng BROOKLYN_BRIDGE = new LatLng(40.7057, -73.9964);
    private static final LatLng WALL_STREET = new LatLng(40.7064, -74.0094);

    //view detail travel
    TextView name = null;
    TextView sunname = null;
    TextView age = null;
    TextView driveExp = null;
    TextView phoneNumber = null;
    TextView email = null;

    TextView start_trav = null;
    TextView end_trav = null;

    TextView seats = null;
    TextView price = null;
    TextView date = null;
    TextView time = null;

    ReadTask downloadTask = null;



    MapFragment mapFragment;
    GoogleMap map;
    Button btn_view_map = null;
    LinearLayout map_layout = null;
    final String TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getAllTextView();
//map
        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        map = mapFragment.getMap();

        if (map == null) {
            finish();
            return;
        }
        init();
        //---map
    }

    private void init() {
        MarkerOptions options = new MarkerOptions();
        options.position(LOWER_MANHATTAN);
        options.position(BROOKLYN_BRIDGE);
        options.position(WALL_STREET);
        map.addMarker(options);
        String url = getMapsApiDirectionsUrl();
        downloadTask = new ReadTask();
        downloadTask.execute(url);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(BROOKLYN_BRIDGE,
                13));
        addMarkers();
    }

public void getAllTextView(){
    start_trav = (TextView) findViewById(R.id.start_trav);
    end_trav = (TextView) findViewById(R.id.end_trav);
    seats = (TextView) findViewById(R.id.seats);
    price = (TextView) findViewById(R.id.price);
    date = (TextView) findViewById(R.id.date);
    time = (TextView) findViewById(R.id.time);
    name = (TextView) findViewById(R.id.name);
    sunname = (TextView) findViewById(R.id.sunname);
    age = (TextView) findViewById(R.id.age);
    driveExp = (TextView) findViewById(R.id.drivingExp);
    phoneNumber = (TextView) findViewById(R.id.phone_number);
    email = (TextView) findViewById(R.id.email);

    clearAllTextViews();

    //set text views
    name.setText(name.getText().toString() + getIntent().getStringExtra(String.valueOf(R.string.firstName)));
    sunname.setText(sunname.getText().toString() + getIntent().getStringExtra(String.valueOf(R.string.lastName)));
    age.setText(age.getText().toString() + getIntent().getStringExtra(String.valueOf(R.string.age)));
    driveExp.setText(driveExp.getText().toString() + getIntent().getStringExtra(String.valueOf(R.string.drivingExp)));
    phoneNumber.setText(phoneNumber.getText().toString() + getIntent().getStringExtra(String.valueOf(R.string.phone)));
    email.setText(email.getText().toString() + getIntent().getStringExtra(String.valueOf(R.string.prompt_email)));

    start_trav.setText(start_trav.getText().toString() + getIntent().getStringExtra(String.valueOf(R.string.startPoint)));
    end_trav.setText(end_trav.getText().toString() + getIntent().getStringExtra(String.valueOf(R.string.find_travel)));
    seats.setText(seats.getText().toString() + getIntent().getStringExtra(String.valueOf(R.string.seats)));
    price.setText(price.getText().toString() + getIntent().getStringExtra(String.valueOf(R.string.price)));
    date.setText(date.getText().toString() + getIntent().getStringExtra(String.valueOf(R.string.dataFirst)));
    time.setText(time.getText().toString() + getIntent().getStringExtra(String.valueOf(R.string.timeFirst)));

String waypoint1 = getIntent().getStringExtra(String.valueOf(R.string.waypoint1));
String waypoint2 = getIntent().getStringExtra(String.valueOf(R.string.waypoint2));
String waypoint3 = getIntent().getStringExtra(String.valueOf(R.string.waypoint3));
String waypoint4 = getIntent().getStringExtra(String.valueOf(R.string.waypoint4));
String waypoint5 = getIntent().getStringExtra(String.valueOf(R.string.waypoint5));

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

        seats.setText("Кількість місць: ");
        price.setText("Ціна за місце: ");
        date.setText("Дата відправлення: ");
        time.setText("Час відправлення: ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String getMapsApiDirectionsUrl() {
        String waypoints = "waypoints=optimize:true|"
                + LOWER_MANHATTAN.latitude + "," + LOWER_MANHATTAN.longitude
                + "|" + "|" + BROOKLYN_BRIDGE.latitude + ","
                + BROOKLYN_BRIDGE.longitude + "|" + WALL_STREET.latitude + ","
                + WALL_STREET.longitude;

        String sensor = "sensor=false";
        String params = waypoints + "&" + sensor;
        String output = "json";
        String end = "&origin=true&destination=true";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + params+end;
        return url;
    }

    private void addMarkers() {
        if (map != null) {
            map.addMarker(new MarkerOptions().position(BROOKLYN_BRIDGE)
                    .title("First Point"));
            map.addMarker(new MarkerOptions().position(LOWER_MANHATTAN)
                    .title("Second Point"));
            map.addMarker(new MarkerOptions().position(WALL_STREET)
                    .title("Third Point"));
        }
    }

    private class ReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try {
                HttpConnection http = new HttpConnection();
                data = http.readUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            new ParserTask().execute(result);
        }
    }

    private class ParserTask extends
            AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(
                String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                PathJSONParser parser = new PathJSONParser();
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
            ArrayList<LatLng> points = null;
            PolylineOptions polyLineOptions = null;

            // traversing through routes
            for (int i = 0; i < routes.size(); i++) {
                points = new ArrayList<LatLng>();
                polyLineOptions = new PolylineOptions();
                List<HashMap<String, String>> path = routes.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                polyLineOptions.addAll(points);
                polyLineOptions.width(2);
                polyLineOptions.color(Color.BLUE);
            }

            map.addPolyline(polyLineOptions);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {




        //onStop();
        //onLowMemory();
        //onDestroy();
        finish();
        super.onBackPressed();
    }
}
