package com.thealamin.isecure;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Istiyak on 3/23/2016.
 */

public class hospitalAmbulanceFierservice extends Activity {


    TabHost tabHost;
    // List view
    private ListView lv;
    private ListView lv2;
    private ListView lv3;
    private ListView lv4;

    // Listview Adapter
    //ArrayAdapter<String> adapter;

    // Search EditText
    EditText inputSearch;
    EditText inputSearch2;
    EditText inputSearch3;
    EditText inputSearch4;

    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;
    ArrayList<HashMap<String, String>> productList2;
    ArrayList<HashMap<String, String>> productList3;
    ArrayList<HashMap<String, String>> productList4;


    ArrayList<ItemListPogo> arraylist = new ArrayList<ItemListPogo>();
    ArrayList<ItemListPogo> arraylist2 = new ArrayList<ItemListPogo>();
    ArrayList<ItemListPogo> arraylist3 = new ArrayList<ItemListPogo>();
    ArrayList<ItemListPogo> arraylist4 = new ArrayList<ItemListPogo>();


    ListViewAdapter adapter;
    ListViewAdapter adapter2;
    ListViewAdapter adapter3;
    ListViewAdapter adapter4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
        setContentView(R.layout.police_rab_media);

        String products[] = {


                " Mirpur-13, Kafrul , Govt.Unani Medical College & Hospital, Dhaka",
                " Sher-E-Bangla Nagar , Shaheed Sohrawardi Medicel College Hospital, Dhaka",
                " Shahbag Square , Birdem General Hospital, Dhaka",
                " Shahbag circle , BSMMU, Dhaka",
                " Shaymoli bus stand , Dhaka Child Hospital, Dhaka",
                " Bokshi Bazar, 100 Ramna , Dhaka Medical College Hospital, Dhaka",
                " Nayabazar , Dhaka City General Hospital, Dhaka",
                " Sher-E-Bangla Nagar , National Heart Institute & Hospital, Dhaka",
                " Sher-E-Bangla Nagar, National Mental Health Institute & Hospital, Dhaka",
                " Shayamoli, Sher-E-Bangla Nagar , National Ophthalmological Institute & Hospital, Dhaka",
                " Mahakhali , National Cancer Research Institute & Hospital, Dhaka",
                " Sher-E-Bangla Nagar , National Kidney Institute & Hospital, Dhaka",
                " Sher-E-Bangla Nagar , National Orthopedics Hospital, Dhaka",
                " Mirpur , Kidney Foundation Bangladesh, Dhaka",
                " Mahakhali Kacha Bazar , ICDDRB, Dhaka",};



        String name[] = {

                "02-8012048",
                "02-9130800",
                "02-8616641",
                "02-9661051",
                "02-8116061",
                "02-8626812",
                "02-7390860",
                "02-9122560",
                "02-9118171",
                "02-9118336",
                "02-9880078",
                "02-91365560",
                "02-9144190",
                "02-8055827",
                "02-8806523",};




        // Listview Tab 2 RAB


        String products2[] = {


                " Basundhara R/A , Apollo Hospitals Dhaka, Dhaka",
                " Shahabag , BIRDEM Hospital, Dhaka",
                " Kakrail , Anju- Man- E- Mafidul Islam, Dhaka",
                " Shahabag , BSMMU (PG Hospital), Dhaka",
                " Green Road , Criticare EMS, Dhaka",
                " Sher-E-Bangla Nagar , Dhaka Shishu Hospital, Dhaka",
                " Savar , Dip Clinic & Diagnostic Centre, Dhaka",
                " Shahbag , Ibrahim Cardiac Hospital & Research Insititute, Dhaka",};



        String name2[] = {

                "01713063067",
                "02-8616641",
                "02-9336611",
                "02-8614001",
                "01711191947",
                "02-8116061",
                "02-7710066",
                "01713003004",};


        //List view Tab 3 Advocate

        String products3[] = {
                "Victim Support 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka",
                "RAB 1 , Shere Bangla , Dhaka"};

        String name3[] = {"017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx",
                "017xxxxxxxx"};


        //Tab listview 4 for tab 4 Media


        String products4[] = {
                "Mirpur Rd , Mohammadpur Fire Station , Dhaka",
                "Shaheed Tajuddin Ahmend Ave , Tejgaon Fire Station , Dhaka",
                "Kazi Alauddin Rd , Bangladesh Fire Service & Civil Defense , Dhaka",
                "Dhakeshwari, Palashi Barrack Fire Station , Dhaka",
                "Orphanages Rd , Lalbagh Fire Station , Dhaka",
                "Kazi Alauddin Rd , Siddique Bazar Fire Station , Dhaka",
                "Khilgaon , Khilgaon Fire Station , Dhaka",
                "M C Rd , Postogola Fire Station , Dhaka",
                "Begam Rokeya Sarani Rd , Fire Service & Civil Defence Training Complex , Dhaka",
                "Kurmitola , Kurmitola Fire Station , Dhaka",};



        String name4[] = {

                "02-9112078",
                "01730002226",
                "02-9555555",
                "02-8628688",
                "02-8619981",
                "02-9555555",
                "02-7218329",
                "01730002216",
                "02-9001055",
                "02-9713399",};



        lv = (ListView) findViewById(R.id.list_view);
        lv2 = (ListView) findViewById(R.id.list_view2);
        lv3 = (ListView) findViewById(R.id.list_view3);
        lv4 = (ListView) findViewById(R.id.list_view4);

        inputSearch = (EditText) findViewById(R.id.inputSearch);
        inputSearch2 = (EditText) findViewById(R.id.inputSearch2);
        inputSearch3 = (EditText) findViewById(R.id.inputSearch3);
        inputSearch4 = (EditText) findViewById(R.id.inputSearch4);


        // Adding items to listview

        for (int i = 0; i < products.length; i++)
        {
            ItemListPogo wp = new ItemListPogo(products[i], name[i] );
            arraylist.add(wp);
        }


        //Adding item Tab 2 here (Tab 2 for 2 everywhere)

        for (int i = 0; i < products2.length; i++)
        {
            ItemListPogo wp2 = new ItemListPogo(products2[i], name2[i] );
            arraylist2.add(wp2);
        }

        //Adding item Tab 3 here (Tab 3 for 3 everywhere)

        for (int i = 0; i < products3.length; i++)
        {
            ItemListPogo wp3 = new ItemListPogo(products3[i], name3[i] );
            arraylist3.add(wp3);
        }

        //Adding item Tab 4 here (Tab 4 for 4 everywhere)

        for (int i = 0; i < products4.length; i++)
        {
            ItemListPogo wp4 = new ItemListPogo(products4[i], name4[i] );
            arraylist4.add(wp4);
        }





        adapter = new ListViewAdapter(hospitalAmbulanceFierservice.this, arraylist);
        adapter2 = new ListViewAdapter(hospitalAmbulanceFierservice.this, arraylist2);
        adapter3 = new ListViewAdapter(hospitalAmbulanceFierservice.this, arraylist3);
        adapter4 = new ListViewAdapter(hospitalAmbulanceFierservice.this, arraylist4);


        lv.setAdapter(adapter);

        lv2.setAdapter(adapter2);

        lv3.setAdapter(adapter3);

        lv4.setAdapter(adapter4);



        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text


                String text = inputSearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);

                if (text.equals("")) {
                    adapter.setAnim(true);
                } else {
                    adapter.setAnim(false);
                }


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

                adapter.setAnim(true);

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                //	adapter.setAnim(true);
            }
        });



        //Text Input Watcher for Tab 2 here

        inputSearch2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text


                String text = inputSearch2.getText().toString().toLowerCase(Locale.getDefault());
                adapter2.filter(text);

                if(text.equals("")){
                    adapter2.setAnim(true);
                }
                else {
                    adapter2.setAnim(false);
                }



            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

                adapter2.setAnim(true);

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                //	adapter.setAnim(true);
            }
        });





        //Text Input Watcher for Tab 3 here

        inputSearch3.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text


                String text = inputSearch3.getText().toString().toLowerCase(Locale.getDefault());
                adapter3.filter(text);

                if(text.equals("")){
                    adapter3.setAnim(true);
                }
                else {
                    adapter3.setAnim(false);
                }



            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

                adapter3.setAnim(true);

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                //	adapter.setAnim(true);
            }
        });

        //Text Input Watcher for Tab 4 here

        inputSearch4.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text


                String text = inputSearch4.getText().toString().toLowerCase(Locale.getDefault());
                adapter4.filter(text);

                if(text.equals("")){
                    adapter4.setAnim(true);
                }
                else {
                    adapter4.setAnim(false);
                }



            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

                adapter4.setAnim(true);

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                //	adapter.setAnim(true);
            }
        });



        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Hospital");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Hospital");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Ambulance");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Ambulance");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Victim Support");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Victim Support");
        host.addTab(spec);

        spec = host.newTabSpec("Fier Service");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Fier Service");
        host.addTab(spec);



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
                hospitalAmbulanceFierservice.this);
        startActivity(new Intent(hospitalAmbulanceFierservice.this, MainActivity.class));


    }



}

