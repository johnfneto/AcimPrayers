package com.appsolve.acimprayers;

import com.appsolve.acimprayers.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.support.v4.app.NavUtils;

public class SplashActivity extends Activity {
	private Handler mHandler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        
        //getActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
	@Override
	public boolean onTouchEvent(MotionEvent event) {

			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				// The user has touched the screen, transition to the Main Menu screen				
				View layout = findViewById(R.id.relativeLayout);
				layout.setBackgroundResource(R.drawable.screen1);				
				mHandler.postDelayed(new Runnable() {
							public void run() {
				                startActivity(new Intent(SplashActivity.this, Splash2Activity.class));
				                SplashActivity.this.finish();
							}
						}, 100);								
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
