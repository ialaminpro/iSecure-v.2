package com.thealamin.isecure;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Set;
import java.util.Iterator;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Locale;
import java.util.Map;

public class MapsActivity extends Nav implements OnMapReadyCallback {




    GPSTracker gps;
    Locale myLocale;
    public static final String TAG = "location-updates-sample";

    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 100;

    /**
     * The fastest rate for active location updates. Exact. Updates will never be more frequent
     * than this value.
     */
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    // Keys for storing activity state in the Bundle.
    public final static String REQUESTING_LOCATION_UPDATES_KEY = "requesting-location-updates-key";
    public final static String LOCATION_KEY = "location-key";
    public final static String LAST_UPDATED_TIME_STRING_KEY = "last-updated-time-string-key";

    /**
     * Provides the entry point to Google Play services.
     */
    public GoogleApiClient mGoogleApiClient;

    /**
     * Stores parameters for requests to the FusedLocationProviderApi.
     */
    public LocationRequest mLocationRequest;

    /**
     * Represents a geographical location.
     */
    public Location mCurrentLocation;


    /**
     * Tracks the status of the location updates request. Value changes when the user presses the
     * Start Updates and Stop Updates buttons.
     */
    public Boolean mRequestingLocationUpdates;

    /**
     * Time when the location was updated represented as a String.
     */
    public String mLastUpdateTime;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladies_toilet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo); //also displays wide logo
        getSupportActionBar().setDisplayShowTitleEnabled(false); //optional


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadLocale();
        //setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);





        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();




    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        backButtonHandler();
    }

    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MapsActivity.this);
        startActivity(new Intent(MapsActivity.this, MainActivity.class));


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





    /**
     * Stores activity data in the Bundle.
     */
    public void onSaveInstanceState(Bundle savedInstanceState) {
        //savedInstanceState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY, mRequestingLocationUpdates);
        savedInstanceState.putParcelable(LOCATION_KEY, mCurrentLocation);
        savedInstanceState.putString(LAST_UPDATED_TIME_STRING_KEY, mLastUpdateTime);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gps = new GPSTracker(MapsActivity.this);



        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings();

     /*   // Instantiates a new CircleOptions object and defines the center and radius
        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(23.754955, 90.376504))
                .radius(1000); // In meters*/

// Nearest area start

        double x1=gps.getLatitude(),y1=gps.getLongitude(),x,y,z,w;
        double x2[] = {23.745572,23.743205,23.736491,23.733149,23.716816,23.707333,23.734797,23.718635,23.702426,23.724957,23.691827,23.710431,23.730024,23.741897,23.751009,23.736112,23.866945,23.850321,23.889224,23.871048,23.859701,23.791270,23.824612,23.771479,23.828152,23.761319,23.765381,23.755701,23.771186,23.804345,23.826101,23.801352,23.805457,-123456789};
        double y2[] = {90.404612,90.381611,90.395725,90.386807,90.382251,90.409203,90.365236,90.365681,90.418255,90.494755,90.431401,90.435549,90.417339,90.428153,90.425211,90.416126,90.400612,90.409117,90.370925,90.432698,90.426182,90.415468,90.405555,90.427392,90.419540,90.389476,90.400640,90.363906,90.359425,90.363140,90.366508,90.381521,90.348875,-123456789};
        String c[] = {"Ramana","Dhanmondi","Sahabag","New Market","Lalbag","Kotwali"," Hajaribag","Kamarangiracar","Sutrapur","Demra","Syamapur","Jatrabari","Motijheel","Sabujbag"," Khilgaon","Paltan","Uttara","Airport","Turag","Uttarkhan","Daksinakhan","Gulshan","Dhaka Cantonment","Badda","Khilkhet","Tejgaon","Tejgaon Industrial Area","Mohammadapur","Adabar","Mirpur","Pallabi","Kafrul","Shah Ali","END"};


        String s[]  = new String[100];
        double res[] = new double[100];

        //Arrays.fill(res, 987654321);

        for(int i=0;x2[i]!=-123456789;i++){
            x=((x2[i]-x1)*(x2[i]-x1));
            y=((y2[i]-y1)*(y2[i]-y1));
            z =(111.2*x)+(111*y);
            w=Math.sqrt(z);
            res[i]= w;
            s[i]=c[i];
        }




        HashMap<Double, String> hmap = new HashMap<Double, String>();



        for(int i=0;i<c.length;i++){
            hmap.put(res[i],s[i]);
        }



        Map<Double, String> map = new TreeMap<Double, String>(hmap);

        System.out.println("\n");

        Set<Entry<Double, String>> set2 = map.entrySet();

        Iterator<Entry<Double, String>> iterator2 = set2.iterator();
        int j=0;
        double lan=0,lon=0;
        int plug=0;
        while(iterator2.hasNext()) {

            Map.Entry<Double, String> me2 = iterator2.next();


            for(int i=0;x2[i]!=-123456789;i++)
            {
                if(c[i].equals(me2.getValue()))
                {
                    plug ++;
                    if(plug == 1) {
                        lan += x2[i];
                        lon += y2[i];
                    }

                    System.out.print (x2[i]+","+y2[i]+" ");
                    System.out.println(me2.getValue());
                }
            }
            j++;

        }


// Nearest Area end


        Polyline line1 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(gps.getLatitude(), gps.getLongitude()), new LatLng(lan, lon))
                .width(5)
                .color(0xFFFF0000)); //non transparent red

       /* Polyline line2 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(23.745572, 90.404612), new LatLng(23.736491, 90.395725))
                .width(5)
                .color(0x7F0000FF)); //semi-tra
*/



       /* Polygon polygon = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(0, 0), new LatLng(0, 5), new LatLng(3, 5), new LatLng(0, 0))
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));

        Polygon polygon1 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(0, 0), new LatLng(0, 5), new LatLng(3, 5))
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));*/

        GoogleMapOptions options = new GoogleMapOptions();

        // Sets the map type to be "hybrid"
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);




        LatLng yourlocation = new LatLng(gps.getLatitude(), gps.getLongitude());
        System.out.println("d-========df=ffd " + gps.getLatitude() + gps.getLongitude()+"sfsdfsf");
        mMap.addMarker(new MarkerOptions().position(yourlocation).draggable(true).title("Your Location"));

        LatLng ramana = new LatLng(23.745572, 90.404612);
        mMap.addMarker(new MarkerOptions().position(ramana).title("ramana model thana, 01713373125 "));

        LatLng Dhanmondi = new LatLng(23.743205, 90.381611);
        mMap.addMarker(new MarkerOptions().position(Dhanmondi).title("Dhanmondi Model Thana,01713373126"));

        LatLng Sahabag = new LatLng(23.736491, 90.395725);
        mMap.addMarker(new MarkerOptions().position(Sahabag).title("Sahabag Model Thana"));

        LatLng NewMarket = new LatLng(23.733149, 90.386807);
        mMap.addMarker(new MarkerOptions().position(NewMarket).title("New Market Thana,01713373125"));

        LatLng Lalbag = new LatLng(23.716816, 90.382251);
        mMap.addMarker(new MarkerOptions().position(Lalbag).title("Lalbag Thana01713373134"));

        LatLng Kotwali = new LatLng(23.707333, 90.409203);
        mMap.addMarker(new MarkerOptions().position(Kotwali).title("Kotwali Model Thana 01713373135"));

        LatLng Hajaribag = new LatLng(23.734797, 90.365236);
        mMap.addMarker(new MarkerOptions().position(Hajaribag).title("Hajaribag Thana01713373136 "));

        LatLng Kamarangiracar = new LatLng(23.718635, 90.365681);
        mMap.addMarker(new MarkerOptions().position(Kamarangiracar).title("Kamarangiracar Thana01713373137 "));

        LatLng Sutrapur = new LatLng(23.702426, 90.418255);
        mMap.addMarker(new MarkerOptions().position(Sutrapur).title("Sutrapur model Thana01713373143 "));

        LatLng Demra = new LatLng(23.724957, 90.494755);
        mMap.addMarker(new MarkerOptions().position(Demra).title("Demra Thana01713373144"));

        LatLng Syamapur = new LatLng(23.691827, 90.431401);
        mMap.addMarker(new MarkerOptions().position(Syamapur).title("Syamapur Thana01713373145 "));

        LatLng Jatrabari = new LatLng(23.710431, 90.435549);
        mMap.addMarker(new MarkerOptions().position(Jatrabari).title("Jatrabari Thana01713373146 "));

        LatLng Motijheel = new LatLng(23.730024, 90.417339);
        mMap.addMarker(new MarkerOptions().position(Motijheel).title("Motijheel Thana01713373152"));

        LatLng Sabujbag = new LatLng(23.741897, 90.428153);
        mMap.addMarker(new MarkerOptions().position(Sabujbag).title("Sabujbag Thana01713373153 "));

        LatLng Khilgaon = new LatLng(23.751009, 90.425211);
        mMap.addMarker(new MarkerOptions().position(Khilgaon).title("Khilgaon Thana01713373154  "));

        LatLng Paltan = new LatLng(23.736112, 90.416126);
        mMap.addMarker(new MarkerOptions().position(Paltan).title("Paltan Thana 01713373155 "));

        LatLng Uttora = new LatLng(23.866945, 90.400612);
        mMap.addMarker(new MarkerOptions().position(Uttora).title("Uttara Thana01713373161  "));

        LatLng Airport = new LatLng(23.850321, 90.409117);
        mMap.addMarker(new MarkerOptions().position(Airport).title("Airport Thana01713373162   "));

        LatLng Turag = new LatLng(23.889224, 90.370925);
        mMap.addMarker(new MarkerOptions().position(Turag).title("Turag Thana01713373163 "));

        LatLng Uttarkhan = new LatLng(23.871048, 90.432698);
        mMap.addMarker(new MarkerOptions().position(Uttarkhan).title("Uttarkhan Thana01713373164"));

        LatLng Daksinakhan = new LatLng(23.859701, 90.426182);
        mMap.addMarker(new MarkerOptions().position(Daksinakhan).title("Daksinakhan Thana01713373165"));

        LatLng Gulshan = new LatLng(23.791270, 90.415468);
        mMap.addMarker(new MarkerOptions().position(Gulshan).title("Gulshan Model Thana01713373171"));

        LatLng Cantonment = new LatLng(23.824612, 90.405555);
        mMap.addMarker(new MarkerOptions().position(Cantonment).title("Dhaka Cantonment Thana-01713373172 "));

        LatLng Budda = new LatLng(23.771479, 90.427392);
        mMap.addMarker(new MarkerOptions().position(Budda).title("Badda Thana 01713373173"));

        LatLng Khilkhet = new LatLng(23.828152, 90.419540);
        mMap.addMarker(new MarkerOptions().position(Khilkhet).title("Khilkhet Thana 01713373174"));

        LatLng Tejgaon = new LatLng(23.761319, 90.389476);
        mMap.addMarker(new MarkerOptions().position(Tejgaon).title("Tejgaon Model Thana 01713373180"));

        LatLng Tejgaoni = new LatLng(23.765381, 90.400640);
        mMap.addMarker(new MarkerOptions().position(Tejgaoni).title("Tejgaon Industrial Area Thana01713373181"));

        LatLng Mohammadapur = new LatLng(23.755701, 90.363906);
        mMap.addMarker(new MarkerOptions().position(Mohammadapur).title("Mohammadapur Model Thana01713373182"));

        LatLng Adabar = new LatLng(23.771186, 90.359425);
        mMap.addMarker(new MarkerOptions().position(Adabar).title("Adabar Thana 01713373183"));

        LatLng Mirpur = new LatLng(23.804345, 90.363140);
        mMap.addMarker(new MarkerOptions().position(Mirpur).title("Mirpur Thana 01713373189"));

        LatLng Pallabi = new LatLng(23.826101, 90.366508);
        mMap.addMarker(new MarkerOptions().position(Pallabi).title("Pallabi Thana 01713373190"));

        LatLng Kafrul = new LatLng(23.801352, 90.381521);
        mMap.addMarker(new MarkerOptions().position(Kafrul).title("Kafrul Thana 01713373191"));

        LatLng Shah = new LatLng(23.805457, 90.348875);
        mMap.addMarker(new MarkerOptions().position(Shah).title("Shah Ali Thana 01713373192"));


        // Add a marker in Sydney and move the camer

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yourlocation, 17));

    }
}
