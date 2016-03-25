package com.thealamin.isecure;

/**
 * Created by hideaway on 3/21/16.
 */
//Simple Android TabHost and TabWidget Example

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class TabView extends AppCompatActivity {
    TabHost tabHost;
    // EditText editText;
    String[] items,phone;

    ArrayList<String> listItems,phoneItems;

    ArrayAdapter<String> adapter,phoneadapter;

    ListView listView;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_view);

        //   String[] food = {"Kola","am","komola","jam","ata"};


    /*    ListAdapter myadapter = new CustomAdapter(this,food);
        ListView myListview = (ListView) findViewById(R.id.listView);
        myListview.setAdapter(myadapter);

        myListview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String food = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(TabView.this, food, Toast.LENGTH_SHORT).show();
                    }
                }
        );  */

        listView=(ListView)findViewById(R.id.listView);

        editText=(EditText)findViewById(R.id.txtsearch);

        initList();


        editText.addTextChangedListener(new TextWatcher() {

            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int
                    after) {


            }


            @Override

            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {

                if (s.toString().equals("")) {

                    // reset listview

                    initList();

                } else {

                    // perform search

                    searchItem(s.toString());

                }

            }


            @Override

            public void afterTextChanged(Editable s) {


            }

        });

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Tab One");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Tab Two");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Tab Three");
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
                TabView.this);
        startActivity(new Intent(TabView.this, MainActivity.class));


    }


    public void searchItem(String textToSearch){

        for(String item:items){

            if(!item.contains(textToSearch)){

                listItems.remove(item);

            }

        }

        adapter.notifyDataSetChanged();

    }

    public void initList(){

        items=new String[]{"Bangladesh\niii","Canada","China","Japan","USA"};
        phone = new String[] {"01680901286","111111","222222","333333","4444444"};

        listItems=new ArrayList<>(Arrays.asList(items));
        phoneItems=new ArrayList<>(Arrays.asList(phone));

        adapter=new ArrayAdapter<String>(this,
                R.layout.list_item, R.id.istitext, listItems);





        // listView.setAdapter(phoneadapter);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String food = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(TabView.this, food, Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }


}