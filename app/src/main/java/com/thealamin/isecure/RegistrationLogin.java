package com.thealamin.isecure;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

public class RegistrationLogin extends AppCompatActivity {
    EditText etFirstName, etLastName, etEmail, etPhone, etPassword,etConfirmPass,etLocation, etBlood;
    String firstName, lastName, email, phone, password, bloodGroup, location;
    Button btnSubmit,btnSubmita;
    String clicked;
    EditText ET_NAME,ET_USER_NAME,ET_USER_PASS,ET_USER_CON_PASS;
    String name,user_name,user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);

        btnSubmit = (Button) findViewById(R.id.btnLogin);
        btnSubmita = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        btnSubmita.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(RegistrationLogin.this, Register.class));

                }
            });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etEmail.getText().toString().length() < 2) {
                    Toast.makeText(RegistrationLogin.this, "Name should be at least 2 characters", Toast.LENGTH_SHORT).show();
                    etEmail.requestFocus();
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
                    Toast.makeText(RegistrationLogin.this, "Password can not be empty", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                }  else {
                    doPostRequest();
                }
            }
        });

    }


    public void btnSkip(View view){

        startActivity(new Intent(RegistrationLogin.this, MainActivity.class).putExtra("activity", "RegistrationLogin"));

    }



    public  void  doPostRequest() {

        com.android.volley.RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

        String url = "http://thealamin.com/dbtest/login.php";

        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals("Success")){

                            Toast.makeText(RegistrationLogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            SharedPreferences savedata = getApplicationContext().getSharedPreferences("Login", Context.MODE_PRIVATE);

                            SharedPreferences.Editor editdata = savedata.edit();

                            editdata.putString("islogin", "ok");
                            editdata.putString("username",etEmail.getText().toString());
                            editdata.putString("password",etPassword.getText().toString());


                            editdata.commit();


                            startActivity(new Intent(RegistrationLogin.this, MainActivity.class).putExtra("activity", "RegistrationLogin"));
                        }
                        else{
                            Toast.makeText(RegistrationLogin.this, "Incorrect Password or Eamil", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationLogin.this, RegistrationLogin.class));
                        }

                        finish();
                    }
                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistrationLogin.this, "A problem occurred", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
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
