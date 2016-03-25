package com.thealamin.isecure;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by hideaway on 2/19/16.
 */
public class Sms extends Activity {
    private String number;
    public static String policenumber=" ";
    private String txtMessage;
    public  static  String phone1,phone2,phone3,phone4;
    //(EditText) findViewById(R.id.phoneNumber);
    GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences phonenumberload = getSharedPreferences("phone", Context.MODE_PRIVATE);
        phone1 = phonenumberload.getString("phone1", "10921");
        phone2 = phonenumberload.getString("phone2", "10921");
        phone3 = phonenumberload.getString("phone3", "10921");
        phone4 = phonenumberload.getString("phone4", "10921");

        //number = "01676789387";
        //txtMessage = "Please help me!";
//        MultipleSMS sm = new MultipleSMS();
//        for (int i = 0; i < MobNumber.size(); i++)
//        {
//            String message = MessageText.getText().toString();
//            String tempMobileNumber = MobNumber.get(i).toString();
//            sm.MultipleSMS(tempMobileNumber, message);
//        }

        sendSMSMessage();
    }

    protected void sendSMSMessage() {

        gps = new GPSTracker(this);


// Nearest area start

        double x1=gps.getLatitude(),y1=gps.getLongitude(),x,y,z,w;
        txtMessage = "Please help me! I'm in danger\n I'm Here: maps://maps.google.com/maps?daddr="+x1+","+y1+"\n";
        double x2[] = {23.745572,23.743205,23.736491,23.733149,23.716816,23.707333,23.734797,23.718635,23.702426,23.724957,23.691827,23.710431,23.730024,23.741897,23.751009,23.736112,23.866945,23.850321,23.889224,23.871048,23.859701,23.791270,23.824612,23.771479,23.828152,23.761319,23.765381,23.755701,23.771186,23.804345,23.826101,23.801352,23.805457,-123456789};
        double y2[] = {90.404612,90.381611,90.395725,90.386807,90.382251,90.409203,90.365236,90.365681,90.418255,90.494755,90.431401,90.435549,90.417339,90.428153,90.425211,90.416126,90.400612,90.409117,90.370925,90.432698,90.426182,90.415468,90.405555,90.427392,90.419540,90.389476,90.400640,90.363906,90.359425,90.363140,90.366508,90.381521,90.348875,-123456789};
        String c[] = {"01676789387","01676789387","01676789387","01676789387","01676789387","01676789387"," 01676789387","01676789387","01676789387","01676789387","01676789387","01676789387","01676789387","01676789387"," 01676789387","01676789387","01676789387","01676789387","01676789387","01676789387","01676789387","01676789387","01676789387","01676789387","01676789387","01676789387"," 01676789387","01676789387","01676789387","01676789387","01676789387","01676789387","01676789387","END"};


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

        Set<Map.Entry<Double, String>> set2 = map.entrySet();

        Iterator<Map.Entry<Double, String>> iterator2 = set2.iterator();
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

                    //System.out.print (x2[i]+","+y2[i]+" ");
                    policenumber=me2.getValue();
                }
            }
            j++;

        }


// Nearest Area end

        //LatLng yourlocation = new LatLng(gps.getLatitude(), gps.getLongitude());

        Log.i("Send SMS", "");
        String phoneNo = policenumber;
        //System.out.print ("fgggggggggggggggggggggggggg"+policenumber);
        String message = txtMessage;
        LatLng yourlocation = new LatLng(gps.getLatitude(), gps.getLongitude());
        //System.out.println("d-========df=ffd " + gps.getLatitude() + gps.getLongitude()+"sfsdfsf");
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            smsManager.sendTextMessage(phone1, null, message, null, null);
            smsManager.sendTextMessage(phone2, null, message, null, null);
            smsManager.sendTextMessage(phone3, null, message, null, null);
            smsManager.sendTextMessage(phone4, null, message, null, null);

            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
            finish();
          //  startActivity(new Intent(Sms.this, MainActivity.class));

        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            finish();
         // hyni    startActivity(new Intent(Sms.this, MainActivity.class));
        }
    }
}
