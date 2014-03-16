package com.example.controlnotification;

import android.os.Bundle;
import android.os.CountDownTimer;


import android.app.Activity;


import android.view.Menu;
import android.view.View;
import android.widget.Button;


import android.widget.TextView;



public class MainActivity extends Activity
{


	protected CountDownTimer	timer;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		final Button startCountdown = (Button) findViewById(R.id.buttonCountdown);
		final TextView output = (TextView)findViewById(R.id.TextViewOutput);
		final TextView input = (TextView)findViewById(R.id.editTextInputTime);
		
		startCountdown.setOnClickListener(new View.OnClickListener() {

		    public  void onClick(View v) {
		        // Handle click event.
		    	
				CharSequence inputstring = input.getText();
				int time = Integer.parseInt(inputstring.toString());
	
				final CountDownTimer timer = new CountDownTimer(time*60*1000, 1000) {
				
					 public void onTick(long millisUntilFinished) {
						 long seconds = millisUntilFinished/1000;
					     output.setText("time remaining: "+String.format("%02d", seconds / 60) + ":" + String.format("%02d", seconds % 60));
							
					 }
					
					 public void onFinish() {
					     output.setText("done!");
					 }
					}
					.start(); }

		});
		final Button buttonStop = (Button) findViewById(R.id.buttonStop);
		buttonStop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
			output.setText("blub");
//timer.cancel();
			
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

