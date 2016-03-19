package com.thealamin.isecure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by Istiyak on 2/21/2016.
 */
public class logoutsplash  extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    String DEFAULT = "N/A";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logoutsplash);





        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Toast.makeText(logoutsplash.this, "Login Out", Toast.LENGTH_LONG).show();
                SharedPreferences savedata = getApplicationContext().getSharedPreferences("Login", Context.MODE_PRIVATE);

                SharedPreferences.Editor deletedata = savedata.edit();
                deletedata.putString("islogin", "no");
                deletedata.commit();

                startActivity(new Intent(logoutsplash.this, RegistrationLogin.class));
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
