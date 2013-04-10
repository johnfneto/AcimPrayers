package com.appsolve.acimprayers;


import com.appsolve.acimprayers.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class Splash2Activity  extends Activity {
	private Handler mHandler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash2);
        
        
        //getActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
	@Override
	public boolean onTouchEvent(MotionEvent event) {

			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				// The user has touched the screen, transition to the Main Menu screen				
				View layout = findViewById(R.id.relativeLayout);
				layout.setBackgroundResource(R.drawable.screen2);				
				mHandler.postDelayed(new Runnable() {
							public void run() {
				                startActivity(new Intent(Splash2Activity.this, Splash3Activity.class));
				                Splash2Activity.this.finish();
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
