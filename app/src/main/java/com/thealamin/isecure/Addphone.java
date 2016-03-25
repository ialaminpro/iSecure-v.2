package com.thealamin.isecure;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by hideaway on 3/23/16.
 */
public class Addphone extends Activity{

    EditText phone1,phone2,phone3,phone4;
    ImageButton btnskip,btnnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addphone);
        phone1 = (EditText)findViewById(R.id.phone1);
        phone2 = (EditText)findViewById(R.id.phone2);
        phone3 = (EditText)findViewById(R.id.phone3);
        phone4 = (EditText)findViewById(R.id.phone4);
        btnskip = (ImageButton)findViewById(R.id.btnskip);
        btnnext = (ImageButton)findViewById(R.id.btnnext);

    }

    public void btnSkip(View view){

        Toast.makeText(Addphone.this, "skiped", Toast.LENGTH_SHORT).show();

    }

    public void btnnextphone(View view){


        try{

            SharedPreferences phonenumber = getApplicationContext().getSharedPreferences("phone", Context.MODE_PRIVATE);

            SharedPreferences.Editor editdataphonenumber = phonenumber.edit();

            editdataphonenumber.putString("phone1", phone1.getText().toString());
            editdataphonenumber.putString("phone2", phone2.getText().toString());
            editdataphonenumber.putString("phone3", phone3.getText().toString());
            editdataphonenumber.putString("phone4", phone4.getText().toString());

            Toast.makeText(Addphone.this, "Data Saved", Toast.LENGTH_SHORT).show();



            editdataphonenumber.commit();



        }catch (Exception e){

            Toast.makeText(Addphone.this, "Insuficiant memory", Toast.LENGTH_SHORT).show();

        }



    }
}
