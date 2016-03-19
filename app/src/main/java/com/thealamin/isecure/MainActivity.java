package com.thealamin.isecure;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.Locale;

public class MainActivity extends Nav{
    ImageButton bta, btcall, btvr, btsms, bte, btv, btg,btdog;

    Locale myLocale;
    // TextView username_to_show;




    int flug = 0;
    int flug_dog = 0;
    public MediaPlayer mp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mp1 = MediaPlayer.create(MainActivity.this,R.raw.dogwhistle2);


         /*  username_to_show = (TextView) findViewById(R.id.username_to_nav_profile);
            SharedPreferences showdata = getSharedPreferences("Login", Context.MODE_PRIVATE);
            String nameshow = showdata.getString("username", "User");
            username_to_show.setText(nameshow); */


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo); //also displays wide logo
        getSupportActionBar().setDisplayShowTitleEnabled(false); //optional





         /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        bta = (ImageButton) findViewById(R.id.Emergency);
        btcall = (ImageButton) findViewById(R.id.call);
        btvr = (ImageButton) findViewById(R.id.voicerecord);
        btdog = (ImageButton) findViewById(R.id.btdog);


        btsms = (ImageButton) findViewById(R.id.sms);
        bte = (ImageButton) findViewById(R.id.police);
        btv = (ImageButton) findViewById(R.id.video);
        btg = (ImageButton) findViewById(R.id.cameraPic);

        btdog.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (flug_dog == 0) {

                    mp1.start();
                    mp1.setLooping(true);
                    flug_dog = 1;

                } else if (flug_dog == 1) {
                    flug_dog = 0;
                    mp1.pause();
                    mp1.setLooping(false);


                }
                // mp1.stop() / mp1.pause()

            }
        });




        btv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Videorecord.class));
            }
        });

        btvr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VoiceRecord.class));
            }
        });

        btg.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Camerapic.class));
            }
        });

        btsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Sms.class));
            }
        });

        btcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Call.class));
            }
        });


        bte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });

        bta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( v.getId() == R.id.Emergency && flug==0){


                    btcall.setVisibility(View.VISIBLE);

                    btvr.setVisibility(View.VISIBLE);
                    btsms.setVisibility(View.VISIBLE);
                    bte.setVisibility(View.VISIBLE);
                    btv.setVisibility(View.VISIBLE);
                    btg.setVisibility(View.VISIBLE);
                    flug=1;

                }
                else if( v.getId() == R.id.Emergency && flug==1){


                    btcall.setVisibility(View.INVISIBLE);
                    btvr.setVisibility(View.INVISIBLE);
                    btsms.setVisibility(View.INVISIBLE);
                    bte.setVisibility(View.INVISIBLE);
                    btv.setVisibility(View.INVISIBLE);
                    btg.setVisibility(View.INVISIBLE);
                    flug=0;

                }
            }
        });
    }

    public void voicerecord_exit(View view){

        Intent i = new Intent(MainActivity.this,logoutsplash.class);
        startActivity(i);

        finish();
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






     /*   public void logout(View view){
            Toast.makeText(MainActivity.this, "Loged Out...", Toast.LENGTH_SHORT).show();
            SharedPreferences savedata = getApplicationContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editdata = savedata.edit();
            editdata.putString("islogin", "N/A");
            editdata.commit();
            Intent i = new Intent(MainActivity.this,RegistrationLogin.class);
            startActivity(i);
            finish();
        }
        */
}