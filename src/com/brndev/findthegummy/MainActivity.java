package com.brndev.findthegummy;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.appbrain.AppBrain;
//import com.tapfortap.AdView;


public class MainActivity extends Activity {
	
	//private AdView adView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        
        //adView = (AdView) findViewById(R.id.ad_view);
        //adView.loadAds();
    }
    
    
    @Override
    public void onBackPressed() {
    	super.onBackPressed();

    	AppBrain.getAds().showInterstitial(this);
    }

    
    @Override
    public void onResume() {
    	super.onResume();

    	//adView.loadAds();
    }
    
    
    
    @Override
    public void onPause() {
    	super.onPause();
    	

    }
    
   

}
