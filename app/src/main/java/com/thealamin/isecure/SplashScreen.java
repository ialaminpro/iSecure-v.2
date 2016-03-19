package com.thealamin.isecure;

/**
 * Created by hideaway on 2/19/16.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import java.util.Locale;

public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    String DEFAULT = "N/A";

    Locale myLocale;





    @Override
    public void onCreate(Bundle savedInstanceState) {

        loadlanguagepreference();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


       


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                SharedPreferences showdata = getSharedPreferences("Login", Context.MODE_PRIVATE);
                String name = showdata.getString("islogin", DEFAULT);

                if(name.equals(DEFAULT)){

                    Intent i = new Intent(SplashScreen.this,RegistrationLogin.class);
                    startActivity(i);

                    finish();

                }


                else{
                    Intent i = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(i);

                    finish();

                }


                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);



    }

    public void loadlanguagepreference(){


        SharedPreferences showdata = getSharedPreferences("Language", Context.MODE_PRIVATE);
        String name = showdata.getString("language", "bn");

        if(name.equals("en")){

            changeLang("en");

        }


        else{

            changeLang("bn");


        }


    }



    public void changeLang(String lang)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        //  updateTexts();
    }

    public void saveLocale(String lang)
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    public void loadLocale()
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language);
    }



}