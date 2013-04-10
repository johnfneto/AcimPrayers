package com.appsolve.acimprayers;

import com.appsolve.acimprayers.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

public class Splash5Activity  extends Activity {
	private Handler mHandler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash5);
        
		mHandler.postDelayed(new Runnable() {
			public void run() {
                startActivity(new Intent(Splash5Activity.this, Splash6Activity.class));
                Splash5Activity.this.finish();
			}
		}, 500);	
        
        //getActionBar().setDisplayHomeAsUpEnabled(true);
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
