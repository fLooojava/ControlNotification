package com.example.controlnotification;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import android.os.Bundle;
import android.os.CountDownTimer;



import android.app.Activity;


import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;


import android.widget.TextView;



public class MainActivity extends Activity
{
	
	
	protected CountDownTimer	timer;
	int countT1 = 0;
	int countT2 = 0;
	PrintWriter writer;
	Socket sock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		// Buttons,Textviews,... initialisieren
		final Button btnStart = (Button) findViewById(R.id.btnStart);
		final Button buttonStop = (Button) findViewById(R.id.btnStop);
		final TextView output = (TextView)findViewById(R.id.TextViewOutput);
		final TextView input = (TextView)findViewById(R.id.txtInput);
		final Button btnPlusT1 = (Button) findViewById(R.id.btnPlusT1);
		final Button btnPlusT2 = (Button) findViewById(R.id.btnPlusT2);
		final Button btnMinT1 = (Button) findViewById(R.id.btnMinT1);
		final Button btnMinT2 = (Button) findViewById(R.id.btnMinT2);
		final TextView txtT1 = (TextView) findViewById(R.id.txtviewScore1);
		final TextView txtT2 = (TextView) findViewById(R.id.txtviewScore2);
		final Button btnReset = (Button) findViewById(R.id.btnReset);
		final ToggleButton togglebtnSound1 = (ToggleButton) findViewById(R.id.togglebtnSound1);
		final ToggleButton togglebtnSound2 = (ToggleButton) findViewById(R.id.togglebtnSound2);

		
		btnPlusT1.setOnClickListener(new View.OnClickListener(){
		
			public void onClick(View v) {
				
				countT1++;
				final String counts = String.valueOf(countT1);
				txtT1.setText(counts);
				network();
				sendData();
			}
		});
		btnPlusT2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				
				countT2++;
				final String counts = String.valueOf(countT2);
				txtT2.setText(counts);
			}
		});
		btnMinT1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				
				countT1--;
				final String counts = String.valueOf(countT1);
				txtT1.setText(counts);
			}
		});
		btnMinT2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				
				countT2--;
				final String counts = String.valueOf(countT2);
				txtT2.setText(counts);
			}
		});
		btnReset.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v) {
				
				int countT1 = 0;
				int countT2 = 0;
				final String count1 = String.valueOf(countT1);
				final String count2 = String.valueOf(countT2);
				txtT1.setText(count1);
				txtT2.setText(count2);
			}
		});
	
		btnStart.setOnClickListener(new View.OnClickListener() {

		    public  void onClick(View v) {
		        // Handle click event.
		    	
				//CharSequence inputstring = input.getText();
				//int time = Integer.parseInt(inputstring.toString());
				int time = 5;
				timer = new CountDownTimer(time*60*1000, 1000) {
				
					 public void onTick(long millisUntilFinished) {
						 long seconds = millisUntilFinished/1000;
					     output.setText("time remaining: "+String.format("%02d", seconds / 60) + ":" + String.format("%02d", seconds % 60));
							
					 }
					
					 public void onFinish() {
					     output.setText("Spielzeit beendet.");
					 }
					};
				    timer.start();

					 }

		});
		buttonStop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
			//output.setText("blub");
			timer.cancel();
			
			}
		});
	}
	

	
	private void network(){
		try {
			String ip = "172.0.0.1";
			int port = 21;
			sock = new Socket(ip,port);
			 System.out.println("Connected to:"+ip+" on port:"+port);//debug
			writer = new PrintWriter(sock.getOutputStream());
			
		} catch (IOException ex){
			ex.printStackTrace();

		}
		
	}
	private void sendData(){
		try{writer.println("test message");
		writer.flush();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

