package com.IBM.smartdoner.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Activity_Splash extends Activity {

	private final int SPLASH_DISPLAY_LENGTH = 5000;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.splash);

		/*
		 * New Handler to start the Menu-Activity and close this Splash-Screen
		 * after some seconds.
		 */
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				/* Create an Intent that will start the Menu-Activity. */
				Intent mainIntent = new Intent(Activity_Splash.this,
						Activity_Start.class);
				startActivity(mainIntent);
				finish();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}
}
