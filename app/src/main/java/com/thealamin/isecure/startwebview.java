package com.thealamin.isecure;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by Istiyak on 3/20/2016.
 */
public class startwebview extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        System.out.println("---------------" + release + "-----------------" + sdkVersion);

        if(sdkVersion>20){

            Intent intent = new Intent(this,Forum_view.class);
            startActivity(intent);
        }
        else{


            Intent intent = new Intent(this,MyWb.class);
            startActivity(intent);

        }



    }

}
