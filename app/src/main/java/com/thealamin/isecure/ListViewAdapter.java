package com.thealamin.isecure;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ListViewAdapter extends BaseAdapter {

	Context mContext;
	LayoutInflater inflater;
	private List<ItemListPogo> itemListPogo = null;
	private ArrayList<ItemListPogo> arraylist;
	boolean anim=true;


	public ListViewAdapter(Context context, List<ItemListPogo> itemLists) 
	{
		mContext = context;
		this.itemListPogo = itemLists;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<ItemListPogo>();
		this.arraylist.addAll(itemLists);
	}

	public class ViewHolder {

		TextView product_name;
		TextView name;
	}

	public void setAnim(boolean anim) {
		this.anim = anim;
	}

	@Override
	public int getCount() {
		return itemListPogo.size();
	}

	@Override
	public ItemListPogo getItem(int position) {
		return itemListPogo.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent)
	{
		final ViewHolder holder;
		if (view == null) 
		{
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.list_item, null);
            
			holder.product_name  = (TextView) view.findViewById(R.id.product_name);
			holder.name     = (TextView) view.findViewById(R.id.name);
			
			view.setTag(holder);



		} 
		else 
		{
			holder = (ViewHolder) view.getTag();
		}

		// Set the results into TextViews
		holder.name.setText(itemListPogo.get(position).getItemName());
		holder.product_name.setText(itemListPogo.get(position).getProductName());

		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v)
			{
				//On things on click

				CharSequence colors[] = new CharSequence[] {"Call", "View Map", "Search Another", "Back"};

				AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

				builder.setTitle("Call "+itemListPogo.get(position).getProductName());
				builder.setItems(colors, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// the user clicked on colors[which]
						Toast.makeText(v.getContext(), "Calling......"+which, Toast.LENGTH_SHORT).show();
					}
				});
				builder.show();

				Toast.makeText(v.getContext(), "Clicked Laugh Vote---------"+position, Toast.LENGTH_SHORT).show();

				System.out.println("This is ----------------- "+position);





			}
		});
		Animation animation = null;


		if(anim == true){
			animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.right_to_left);
			animation.setDuration(350);
		}
		else if(anim == false ){

			animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);
			animation.setDuration(300);

		}

		view.startAnimation(animation);
		animation = null;

		return view;
	}




	// Filter Class
	public void filter(String charText) 
	{
		charText = charText.toLowerCase(Locale.getDefault());
		itemListPogo.clear();

		if (charText.length() == 0) 
		{
			itemListPogo.addAll(arraylist);
		} 
		else
		{
			for (ItemListPogo wp : arraylist) 
			{
				if (wp.getProductName().toLowerCase(Locale.getDefault())
						.contains(charText)) 
				{
					itemListPogo.add(wp);
				}
				
				else if (wp.getItemName().toLowerCase(Locale.getDefault())
						.contains(charText)) 
				{
					itemListPogo.add(wp);
				}
			}
		}
		notifyDataSetChanged();
	}
}
