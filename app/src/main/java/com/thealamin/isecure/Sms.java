package com.thealamin.isecure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by hideaway on 2/19/16.
 */
public class Sms extends Activity {
    private String number;
    private String txtMessage;
    //(EditText) findViewById(R.id.phoneNumber);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        number = "01676789387";
        txtMessage = "Please help me!";


        sendSMSMessage();
    }

    protected void sendSMSMessage() {

        Log.i("Send SMS", "");
        String phoneNo = number;
        String message = txtMessage;

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Sms.this, MainActivity.class));
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            startActivity(new Intent(Sms.this, MainActivity.class));
        }
    }
}
