package com.appsolve.acimprayers;


import com.appsolve.acimprayers.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Splash6Activity   extends Activity {
	public static final String DEBUG_TAG = "AcimPrayer";
	public static final String ACIM_PREFS = "Acim_Prefs";
	
	public static final int NEED = 1;
	public static final int PRAYER = 2;
	public static final int VOICE = 3;
	
	private Handler mHandler = new Handler();
	private Handler mHandler1 = new Handler();
	private Handler mHandler2 = new Handler();
	private Handler mHandler3 = new Handler();
	
	int height;
	int width;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash6);
        
		final Animation mAnimIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
		final Animation mAnimOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
		
		final View layout = findViewById(R.id.relativeLayout);

		

		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		height = metrics.heightPixels;
		width = metrics.widthPixels;
		
		
		mHandler1.postDelayed(new Runnable() {
			public void run() {

				layout.startAnimation(mAnimOut);


			}
		}, 300);	

		/*
		mHandler2.postDelayed(new Runnable() {
			public void run() {
				layout1.setVisibility(View.VISIBLE);

				layout1.startAnimation(mAnimIn);

			}
		}, 600);	
		/*
		mHandler2.postDelayed(new Runnable() {
			public void run() {
				layout1.startAnimation(mAnimIn);
				layout2.startAnimation(mAnimOut);
			}
		}, 200);	

		mHandler3.postDelayed(new Runnable() {
			public void run() {
				layout2.startAnimation(mAnimIn);
				layout3.startAnimation(mAnimOut);
			}
		}, 300);	*/
        //getActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    
    
    
	@Override
	public boolean onTouchEvent(MotionEvent event) {

			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				// The user has touched the screen, transition to the Main Menu screen		
				int area;
		        int X;
		        int Y;
			       
	            X = (int)event.getX(); 
	            Y = (int)event.getY(); 
	            
	            if (X>Y)
	            	area = NEED;
	            else
	            	if (X > -Y + height)
	            		area = PRAYER;
	            	else 
	            		area = VOICE;
				
				
				View layout = findViewById(R.id.relativeLayout);
				//layout.setBackgroundResource(R.drawable.screen2);				

				switch (area) {
				case NEED:
	                startActivity(new Intent(Splash6Activity.this, NeedActivity.class));
	                Splash6Activity.this.finish();
					break;
				case PRAYER:
	                startActivity(new Intent(Splash6Activity.this, PrayerActivity.class));
	                Splash6Activity.this.finish();
					break;
				case VOICE:
	                startActivity(new Intent(Splash6Activity.this, VoiceActivity.class));
	                Splash6Activity.this.finish();
					break;
				default:
					break;
				}

					
				
				
			}
		return true;		
	}    
    
	
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


