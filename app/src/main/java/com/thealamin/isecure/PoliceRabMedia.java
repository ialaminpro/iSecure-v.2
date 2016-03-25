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

public class PoliceRabMedia extends Activity {


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
        
        
   
        // Listview Data
		String products[] = {


				"Ramana , Ramana Thana , Dhaka",
				"Dhanmondi , Dhanmondi Thana , Dhaka",
				"Shahabag , Shahabag Thana , Dhaka",
				"Newmarket , Newmarket Thana , Dhaka",
				"Lalbag , Lalbag Thana , Dhaka",
				"Tejgaon , Tejgaon Thana , Dhaka",
				"Badda , Badda Thana , Dhaka",
				"Khilkhet , Khilkhet Thana , Dhaka",
				"Dhaka Cantonment , Cantonment Thana , Dhaka",
				"Adabar , Adabar Thana , Dhaka",
				"Mirpur , Mirpur Thana , Dhaka",
				"Jatrabari , Jatrabari Thana , Dhaka",
				"Motijheel , Motijheel Thana , Dhaka",
				"Mohammadpur , Mohammadpur Thana , Dhaka",
				"Khilgaon , Khilgaon Thana , Dhaka",
				"Paltan , Paltan Thana , Dhaka",
				"Uttara , Uttara Thana , Dhaka",
				"Aiport , Airport Thana , Dhaka",
				"Turag , Turag Thana , Dhaka",
				"Gulshan , Gulshan Thana , Dhaka"};



		String name[] = {

				"01713373125",
				"01713373126",
				"01713373127",
				"01713373128",
				"01713373134",
				"01713373180",
				"01713373173",
				"01713373174",
				"01713373172",
				"01713373183",
				"01713373189",
				"01713373146",
				"01713373152",
				"01713373182",
				"01713373154",
				"01713373155",
				"01713373161",
				"01713373162",
				"01713373163",
				"01713373171",};




		// Listview Tab 2 RAB

		String products2[] = {


				"Gulshan, Badda, Cantonment, Airport & Uttara Thana , RAB-1, Dhaka",
				"Ramna, Dhanmondi, Lalbagh, Kotawali, Hazaribagh, Kamrangichor, Thana , RAB-2, Dhaka",
				"Sutrapur, Motijheel, Demra, Khilgaon, Shempur & Sabujbagh , RAB-3, Dhaka",
				"Tejgaon, Mohammadpur, Kafrul, Pallabi, Mirpur, Savar, Dhamrai , RAB-4, Dhaka",
				"Demra, Shutrapur, Shampur, Kotoali thana & Sodor, Bondor, Fotulla , RAB-10, Dhaka",
				"Gulshan, Badda, Cantonment, Airport & Uttara Thana , RAB-1, Dhaka",
				"Ramna, Dhanmondi, Lalbagh, Kotawali, Hazaribagh, Kamrangichor, Thana , RAB-2, Dhaka",
				"Sutrapur, Motijheel, Demra, Khilgaon, Shempur & Sabujbagh , RAB-3, Dhaka",
				"Tejgaon, Mohammadpur, Kafrul, Pallabi, Mirpur, Savar, Dhamrai , RAB-4, Dhaka",
				"Demra, Shutrapur, Shampur, Kotoali thana & Sodor, Bondor, Fotulla , RAB-10, Dhaka",
				"Gulshan, Badda, Cantonment, Airport & Uttara Thana , RAB-1, Dhaka",
				"Ramna, Dhanmondi, Lalbagh, Kotawali, Hazaribagh, Kamrangichor, Thana , RAB-2, Dhaka",
				"Sutrapur, Motijheel, Demra, Khilgaon, Shempur & Sabujbagh , RAB-3, Dhaka",
				"Tejgaon, Mohammadpur, Kafrul, Pallabi, Mirpur, Savar, Dhamrai , RAB-4, Dhaka",
				"Demra, Shutrapur, Shampur, Kotoali thana & Sodor, Bondor, Fotulla , RAB-10, Dhaka",
				"Gulshan, Badda, Cantonment, Airport & Uttara Thana , RAB-1, Dhaka",
				"Ramna, Dhanmondi, Lalbagh, Kotawali, Hazaribagh, Kamrangichor, Thana , RAB-2, Dhaka",
				"Sutrapur, Motijheel, Demra, Khilgaon, Shempur & Sabujbagh , RAB-3, Dhaka",
				"Tejgaon, Mohammadpur, Kafrul, Pallabi, Mirpur, Savar, Dhamrai , RAB-4, Dhaka",
				"Demra, Shutrapur, Shampur, Kotoali thana & Sodor, Bondor, Fotulla , RAB-10, Dhaka",
		};



				String name2[] = {

						"02-8963419",
						"02-8363764",
						"02-7174687",
						"02-8059254",
						"02-7518674",
						"02-8963419",
						"02-8363764",
						"02-7174687",
						"02-8059254",
						"02-7518674",
						"02-8963419",
						"02-8363764",
						"02-7174687",
						"02-8059254",
						"02-7518674",
						"02-8963419",
						"02-8363764",
						"02-7174687",
						"02-8059254",
						"02-7518674",};




		//List view Tab 3 Advocate


		String products3[] = {


				" Motijheel , Barrister Khan & Associates, Dhaka",
				" Mirpur , Advocate Nazmul Karim, Dhaka",
				" Tejgaon , Gonoshahajjo sangstha (gss), Dhaka",
				" Khilgaon , Advocate syed mahbub hossain, Dhaka",
				" Kotwali , Advocate a. h. md. nurul huda khandker, Dhaka",
				" Dhanmondi , Advocate farid ahmed, Dhaka",
				" Uttara , S. Alam Law Associates, Dhaka",
				" Motijheel , Barrister Khan & Associates, Dhaka",
				" Mirpur , Advocate Nazmul Karim, Dhaka",
				" Tejgaon , Gonoshahajjo sangstha (gss), Dhaka",
				" Khilgaon , Advocate syed mahbub hossain, Dhaka",
				" Kotwali , Advocate a. h. md. nurul huda khandker, Dhaka",
				" Dhanmondi , Advocate farid ahmed, Dhaka",
				" Uttara , S. Alam Law Associates, Dhaka",};



		String name3[] = {

				"01711300699",
				"01911023388",
				"02-28819059",
				"02-1199071793",
				"02-29672914",
				"02-1819213018",
				"02-1711134151",
				"01711300699",
				"01911023388",
				"02-28819059",
				"02-1199071793",
				"02-29672914",
				"02-1819213018",
				"02-1711134151",};

		//Tab listview 4 for tab 4 Media

		String products4[] = {
				"Media 1 , Shere Bangla , Dhaka",
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

		String name4[] = {"017xxxxxxxx",
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





		adapter = new ListViewAdapter(PoliceRabMedia.this, arraylist);
        adapter2 = new ListViewAdapter(PoliceRabMedia.this, arraylist2);
        adapter3 = new ListViewAdapter(PoliceRabMedia.this, arraylist3);
        adapter4 = new ListViewAdapter(PoliceRabMedia.this, arraylist4);


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
		TabHost.TabSpec spec = host.newTabSpec("Police");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Police");
		host.addTab(spec);

		//Tab 2
		spec = host.newTabSpec("RAB");
		spec.setContent(R.id.tab2);
		spec.setIndicator("RAB");
		host.addTab(spec);

		//Tab 3
		spec = host.newTabSpec("Advocate");
		spec.setContent(R.id.tab3);
		spec.setIndicator("Advocate");
		host.addTab(spec);

		spec = host.newTabSpec("Media");
		spec.setContent(R.id.tab4);
		spec.setIndicator("Media");
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
				PoliceRabMedia.this);
		startActivity(new Intent(PoliceRabMedia.this, MainActivity.class));


	}





}
