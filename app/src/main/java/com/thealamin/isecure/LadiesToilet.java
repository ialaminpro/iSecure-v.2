package com.thealamin.isecure;


import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class LadiesToilet extends Nav implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, OnMapReadyCallback {


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

    // UI Widgets.
    public Button mStartUpdatesButton;
    public Button mStopUpdatesButton;
    public TextView mLastUpdateTimeTextView;
    public TextView mLatitudeTextView;
    public TextView mLongitudeTextView;

    // Labels.
    public String mLatitudeLabel;
    public String mLongitudeLabel;
    public String mLastUpdateTimeLabel;


    public  double lati,longi;


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


        // Locate the UI widgets.
        mStartUpdatesButton = (Button) findViewById(R.id.start_updates_button);
        mStopUpdatesButton = (Button) findViewById(R.id.stop_updates_button);
        mLatitudeTextView = (TextView) findViewById(R.id.latitude_text);
        mLongitudeTextView = (TextView) findViewById(R.id.longitude_text);
        mLastUpdateTimeTextView = (TextView) findViewById(R.id.last_update_time_text);

        // Set labels.
        mLatitudeLabel = getResources().getString(R.string.latitude_label);
        mLongitudeLabel = getResources().getString(R.string.longitude_label);
        mLastUpdateTimeLabel = getResources().getString(R.string.last_update_time_label);

        mRequestingLocationUpdates = false;
        mLastUpdateTime = "";

        // Update values using data stored in the Bundle.
        updateValuesFromBundle(savedInstanceState);

        // Kick off the process of building a GoogleApiClient and requesting the LocationServices
        // API.
        buildGoogleApiClient();




        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();



    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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




    /**
     * Updates fields based on data stored in the bundle.
     *
     * @param savedInstanceState The activity state saved in the Bundle.
     */
    public void updateValuesFromBundle(Bundle savedInstanceState) {
        Log.i(TAG, "Updating values from bundle");
        if (savedInstanceState != null) {
            // Update the value of mRequestingLocationUpdates from the Bundle, and make sure that
            // the Start Updates and Stop Updates buttons are correctly enabled or disabled.
            if (savedInstanceState.keySet().contains(REQUESTING_LOCATION_UPDATES_KEY)) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean(
                        REQUESTING_LOCATION_UPDATES_KEY);
                setButtonsEnabledState();
            }

            // Update the value of mCurrentLocation from the Bundle and update the UI to show the
            // correct latitude and longitude.
            if (savedInstanceState.keySet().contains(LOCATION_KEY)) {
                // Since LOCATION_KEY was found in the Bundle, we can be sure that mCurrentLocation
                // is not null.
                mCurrentLocation = savedInstanceState.getParcelable(LOCATION_KEY);
            }

            // Update the value of mLastUpdateTime from the Bundle and update the UI.
            if (savedInstanceState.keySet().contains(LAST_UPDATED_TIME_STRING_KEY)) {
                mLastUpdateTime = savedInstanceState.getString(LAST_UPDATED_TIME_STRING_KEY);
            }
            updateUI();

            lati = mCurrentLocation.getLatitude();
            longi = mCurrentLocation.getLongitude();


        }
    }

    /**
     * Builds a GoogleApiClient. Uses the {@code #addApi} method to request the
     * LocationServices API.
     */
    public synchronized void buildGoogleApiClient() {
        Log.i(TAG, "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
    }

    /**
     * Sets up the location request. Android has two location request settings:
     * {@code ACCESS_COARSE_LOCATION} and {@code ACCESS_FINE_LOCATION}. These settings control
     * the accuracy of the current location. This sample uses ACCESS_FINE_LOCATION, as defined in
     * the AndroidManifest.xml.
     * <p/>
     * When the ACCESS_FINE_LOCATION setting is specified, combined with a fast update
     * interval (5 seconds), the Fused Location Provider API returns location updates that are
     * accurate to within a few feet.
     * <p/>
     * These settings are appropriate for mapping applications that show real-time location
     * updates.
     */
    public void createLocationRequest() {
        mLocationRequest = new LocationRequest();

        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    /**
     * Handles the Start Updates button and requests start of location updates. Does nothing if
     * updates have already been requested.
     */
    public void startUpdatesButtonHandler( ) {
        if (!mRequestingLocationUpdates) {

            mRequestingLocationUpdates = true;
            setButtonsEnabledState();
            startLocationUpdates();
        }
    }

    /**
     * Handles the Stop Updates button, and requests removal of location updates. Does nothing if
     * updates were not previously requested.
     */
    public void stopUpdatesButtonHandler(View view) {
        if (mRequestingLocationUpdates) {
            mRequestingLocationUpdates = false;
            setButtonsEnabledState();
            stopLocationUpdates();
        }
    }

    /**
     * Requests location updates from the FusedLocationApi.
     */
    public void startLocationUpdates() {
        // The final argument to {@code requestLocationUpdates()} is a LocationListener
        // (http://developer.android.com/reference/com/google/android/gms/location/LocationListener.html).
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);


    }

    /**
     * Ensures that only one button is enabled at any time. The Start Updates button is enabled
     * if the user is not requesting location updates. The Stop Updates button is enabled if the
     * user is requesting location updates.
     */
    public void setButtonsEnabledState() {
        if (mRequestingLocationUpdates) {
            mStartUpdatesButton.setEnabled(true);
            mStopUpdatesButton.setEnabled(false);
        } else {
            mStartUpdatesButton.setEnabled(true);
            mStopUpdatesButton.setEnabled(false);
        }
    }

    /**
     * Updates the latitude, the longitude, and the last location time in the UI.
     */
    public void updateUI() {




        mLatitudeTextView.setText(String.format("%s: %f", mLatitudeLabel,
                mCurrentLocation.getLatitude()));
        mLongitudeTextView.setText(String.format("%s: %f", mLongitudeLabel,
                mCurrentLocation.getLongitude()));
        mLastUpdateTimeTextView.setText(String.format("%s: %s", mLastUpdateTimeLabel,
                mLastUpdateTime));


    }

    /**
     * Removes location updates from the FusedLocationApi.
     */
    public void stopLocationUpdates() {
        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.

        // The final argument to {@code requestLocationUpdates()} is a LocationListener
        // (http://developer.android.com/reference/com/google/android/gms/location/LocationListener.html).
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        mGoogleApiClient.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "TurnOnOffLocation Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.thealamin.isecure/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Within {@code onPause()}, we pause location updates, but leave the
        // connection to GoogleApiClient intact.  Here, we resume receiving
        // location updates if the user has requested them.

        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop location updates to save battery, but don't disconnect the GoogleApiClient object.
        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
        }
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();

        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "TurnOnOffLocation Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.thealamin.isecure/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    /**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        Log.i(TAG, "Connected to GoogleApiClient");


        if (mCurrentLocation == null) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions

                return;
            }
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
            //  updateUI();
        }


        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    /**
     * Callback that fires when the location changes.
     */
    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
        Toast.makeText(this, getResources().getString(R.string.location_updated_message),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    /**
     * Stores activity data in the Bundle.
     */
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY, mRequestingLocationUpdates);
        savedInstanceState.putParcelable(LOCATION_KEY, mCurrentLocation);
        savedInstanceState.putString(LAST_UPDATED_TIME_STRING_KEY, mLastUpdateTime);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {





        mMap = googleMap;

        LatLng yourlocation = new LatLng(23.754955, 90.376504);
        mMap.addMarker(new MarkerOptions().position(yourlocation).title("Your Location"));

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
