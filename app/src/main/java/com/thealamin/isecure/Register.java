package com.thealamin.isecure;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Istiyak on 2/20/2016.
 */
public class Register extends Activity {
    EditText etFirstName, etLastName, etEmail, etPhone, etPassword,etConfirmPass,etLocation, etBlood;
    String firstName, lastName, email, phone, password, bloodGroup, location;
    Button btnSubmit,btnSubmita;
    String clicked;
    EditText ET_NAME,ET_USER_NAME,ET_USER_PASS,ET_USER_CON_PASS;
    String name,user_name,user_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etFirstName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPass = (EditText) findViewById(R.id.et_confirm_password);
        btnSubmit = (Button) findViewById(R.id.btnRegister);


        btnSubmita = (Button) findViewById(R.id.btnLinkToLoginScreen);

        btnSubmita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, RegistrationLogin.class));

            }
        });


    btnSubmit.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){

            if (etFirstName.getText().toString().length() < 2) {
                Toast.makeText(Register.this, "Name should be at least 2 characters", Toast.LENGTH_SHORT).show();
                etFirstName.requestFocus();
            }
                    /*else if(etEmail.getText().toString().length() > 5){
                        Toast.makeText(NewDonorForm.this, "Enter your email address", Toast.LENGTH_SHORT).show();
                        etEmail.requestFocus();
                    }
                    else if(etEmail.getText().toString().indexOf("@") < 0){
                        Toast.makeText(NewDonorForm.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                        etEmail.requestFocus();
                    }*/
            else if (etPassword.getText().toString().length() < 1) {
                Toast.makeText(Register.this, "Password can not be empty", Toast.LENGTH_SHORT).show();
                etPassword.requestFocus();
            } else if (etPassword.getText().toString().equals(etConfirmPass.getText().toString()) != true) {
                Toast.makeText(Register.this, "Password did not match", Toast.LENGTH_SHORT).show();
                etPassword.requestFocus();
            } else {
                doPostRequest();
            }
        }
    });

}



    public  void  doPostRequest() {

        com.android.volley.RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

        String url = "http://thealamin.com/dbtest/reg.php";
        firstName = etFirstName.getText().toString();

        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("Success")){
                    Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, MainActivity.class).putExtra("activity", "Register"));
                }

                finish();
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this, "A problem occurred", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("firstName", firstName); //Add the data you'd like to send to the server.

                MyData.put("email", email); //Add the data you'd like to send to the server.
                MyData.put("password", password); //Add the data you'd like to send to the server.

                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);

    }


    public AlertDialog onCreateDialogSingleChoice(final EditText editText,String title, final CharSequence[] array) {
        clicked = array[0].toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clicked = array[which].toString();
            }
        })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        editText.setText(clicked);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }


}
