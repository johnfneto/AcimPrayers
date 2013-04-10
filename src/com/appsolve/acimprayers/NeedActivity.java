package com.appsolve.acimprayers;

import java.io.IOException;
import java.util.ArrayList;

import com.appsolve.acimprayers.R;




import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;



public class NeedActivity extends ListActivity{
	public static final String DEBUG_TAG = "AcimWisdom";
	private static Context context;
	DataBaseHelper db = new DataBaseHelper(this);
	ArrayList<String> titles = new ArrayList<String>();
	ListView listView;	
	private Handler mHandler = new Handler();
	@Override
	public void onCreate(Bundle savedInstanceState) {
    	overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.need_list);
		
		NeedActivity.context = getApplicationContext();
		
        Button backButton = (Button) findViewById(R.id.backButton);
		
        // Register a callback to be invoked when button1 is clicked.
        backButton.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				// If button1 is clicked GessMeaning Activity starts
				startActivity(new Intent(NeedActivity.this, Splash6Activity.class));				
			}
		});	
        
		listView = this.getListView();
		
		
		//setListAdapter(new ListArrayAdapter(this, LEVELS));

        titles = loadTitles();

        for (int i = 0; i < titles.size(); i++) {

            Log.d(DEBUG_TAG, "title: "+titles.get(i).toString());
       }
		
		mHandler.postDelayed(new Runnable() {
			public void run() {
				//View layout = findViewById(R.id.relativeLayout);
				//layout.setBackgroundResource(R.drawable.background);
				
				setListAdapter(new NeedsBaseAdapter(NeedActivity.this,titles));

				/*
				setListAdapter(new ArrayAdapter<String>(this,
						R.layout.level_list,
						R.id.label, LEVELS));
				*/
				
				getListView().setDivider(NeedActivity.this.getResources().getDrawable(android.R.color.transparent));			
				NeedActivity.this.getListView().setCacheColorHint(0);
				getWindow().setBackgroundDrawableResource(R.drawable.background);
				

				
				
				
				
				
				
			}
		}, 800);
		
	}
	

	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		
		//get selected items
		Log.d(DEBUG_TAG, "position:"+Integer.toString(position));
		//String levelNo = (String) getListAdapter().getItem(position);
		//Log.d(DEBUG_TAG, "levelNo= " + levelNo);
		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
		
    	Intent intent = new Intent(NeedActivity.this, DisplayPrayer.class);
    	Bundle b = new Bundle();
    	b.putString("position", Integer.toString(position+1)); //Your id
    	intent.putExtras(b); //Put your id to your next Intent
    	startActivity(intent);		
		
		
		
		
		}   
    
    
    
    
    
    private ArrayList<String> loadTitles() {
    	ArrayList<String> results = new ArrayList<String>();
    	
        //If data base doesn't exist yet creates it 
        try {     	 
        	db.createDataBase();
         } catch (IOException ioe) {
         	throw new Error("Unable to create database");
         }
        
        //Opens the data base
        try { 
        	db.openDataBase(); 
        }catch(SQLException sqle){ 
        	throw sqle; 
        }
               
    
    	//results = db.getTitles("PrayerMaster");
    	results = db.getNeeds();
    	
	    // closes the database        
        if (db!=null){
            db.close();
        }            

        return results;        
  	}        
}
