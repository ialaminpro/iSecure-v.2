package com.thealamin.isecure;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;

public class MainActivity extends Nav{

    ImageButton bta, btcall, btvr, btsms, bte, btv, btg,btdog;

    Locale myLocale;
    // TextView username_to_show;

    private MediaRecorder myAudioRecorder;

    private String outputFile = null;




    int flug = 0;
    int flug_dog = 0;
    public MediaPlayer mp1;
    public int Activeall=0,destroy=0 ;

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


        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";;

        myAudioRecorder=new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);



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

                if (v.getId() == R.id.Emergency && flug == 0 && Activeall == 0) {
                    Activeall++;

                    btcall.setVisibility(View.VISIBLE);

                    btvr.setVisibility(View.VISIBLE);
                    btsms.setVisibility(View.VISIBLE);
                    bte.setVisibility(View.VISIBLE);
                    btv.setVisibility(View.VISIBLE);
                    btg.setVisibility(View.VISIBLE);
                    flug = 1;

                } else if (v.getId() == R.id.Emergency && flug == 1 && Activeall == 1) {
                    Activeall++;

                    btcall.setVisibility(View.INVISIBLE);
                    btvr.setVisibility(View.INVISIBLE);
                    btsms.setVisibility(View.INVISIBLE);
                    bte.setVisibility(View.INVISIBLE);
                    btv.setVisibility(View.INVISIBLE);
                    btg.setVisibility(View.INVISIBLE);
                    flug = 0;

                } else if (destroy == 1) {
                    destroy=0;
                    myAudioRecorder.stop();
                    myAudioRecorder.release();
                    myAudioRecorder = null;

                    Toast.makeText(getApplicationContext(), "Audio recorded successfully", Toast.LENGTH_LONG).show();
                } else if (Activeall == 2) {
                    Activeall = 0;
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                            MainActivity.this);
                    // Setting Dialog Title
                    alertDialog.setTitle("Warning!!!");
                    // Setting Dialog Message
                    alertDialog.setMessage("Please Read Becarefully:\nIf you press YES! It will do all those action\n1. Send SMS & Call to nearest Police Station\n2. Voice Record\n ");
                    // Setting Icon to Dialog
                    alertDialog.setIcon(R.drawable.dialog_icon);
                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    destroy = 1;
                                    /******************** Start Voice Record ************************/
                                    outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
                                    ;

                                    myAudioRecorder = new MediaRecorder();
                                    myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                                    myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                                    myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                                    myAudioRecorder.setOutputFile(outputFile);


                                    try {
                                        myAudioRecorder.prepare();
                                        myAudioRecorder.start();
                                    } catch (IllegalStateException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }


                                    Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();

                                    /******************** End Voice Record ************************/
                                }
                            });
                    // Setting Negative "NO" Button
                    alertDialog.setNegativeButton("NO",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Write your code here to invoke NO event
                                    dialog.cancel();
                                }
                            });
                    // Showing Alert Message
                    alertDialog.show();

                }
            }
        });
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
                MainActivity.this);
        // Setting Dialog Title
        alertDialog.setTitle("Leave application?");
        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to leave the application?");
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.dialog_icon);
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
        // Showing Alert Message
        alertDialog.show();
    }

    public void voicerecord_exit(View view){

        Intent i = new Intent(MainActivity.this,logoutsplash.class);
        startActivity(i);

        finish();
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