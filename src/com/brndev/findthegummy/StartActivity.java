package com.brndev.findthegummy;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.appbrain.AppBrain;
import com.millennialmedia.android.BasicMMAdListener;
import com.millennialmedia.android.MMAdView;
import com.tapfortap.TapForTap;


public class StartActivity extends Activity {
	
	//private AdView adView;
	
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		AppBrain.init(this);

		
		MMAdView interAdView = new MMAdView(this, "96027", MMAdView.FULLSCREEN_AD_LAUNCH, 0, null); 
		interAdView.fetch();
		interAdView.setListener(new BasicMMAdListener(){
		  @Override
		  public void MMAdCachingCompleted(MMAdView adview, boolean success)
		  {
		    if(success)
		      adview.display();
		  }
		});
		
		
		TapForTap.setDefaultAppId("271e0ea0-d93f-012f-fd3b-4040d804a637");
		TapForTap.checkIn(this);
		
		setContentView(R.layout.activity_start);
		
		//adView = (AdView) findViewById(R.id.ad_view);
		//adView.loadAds();
	
		
		final Activity activity = this;
		
		final Button button1 = (Button) findViewById(R.id.button1);

		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(activity, MainActivity.class);
				
				startActivity(i);
				
			}
		});
		
		final Button button2 = (Button) findViewById(R.id.button2);
		
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				openActionView("http://www.amazon.com/s/ref=sr_nr_n_0?rh=n%3A2350149011%2Cp_4%3AB%26R+Nelson+Development%2Cn%3A%212350150011%2Cn%3A2478844011&bbn=2350150011&ie=UTF8&qid=1346540094&rnid=2350150011");
			}
		});
		
	}
	
	private void openActionView(String url){

        Intent updateIntent = null;

        updateIntent = new Intent(Intent.ACTION_VIEW,

                Uri.parse(url));

        startActivity(updateIntent);

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

}
