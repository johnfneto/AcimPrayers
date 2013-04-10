package com.appsolve.acimprayers;

import java.util.ArrayList;

import com.appsolve.acimprayers.R;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NeedsBaseAdapter extends BaseAdapter {
	public static final String DEBUG_TAG = "GrabWordLog";
	ArrayList<String> items = new ArrayList<String>();
	 private static LayoutInflater inflater=null;
	 private final Context context;

	 
	 private LayoutInflater l_Inflater;

	 public NeedsBaseAdapter(Context context, ArrayList<String> items) {
		this.context = context;
		this.items = items;
		inflater = LayoutInflater.from(context);
	 }

	 public int getCount() {
	  return items.size();
	 }

	 public Object getItem(int position) {
	  return items.get(position);
	 }

	 public long getItemId(int position) {
	  return position;
	 }

	 public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.needs_item, null);
		 //ViewHolder holder;
		
		 /*
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.level_list, parent, false);
		*/
		 
	 
		TextView levelView = (TextView) vi.findViewById(R.id.textView);
		
		levelView.setText(Integer.toString(position+1)+". "+items.get(position));

	  return vi;
	 }
}





