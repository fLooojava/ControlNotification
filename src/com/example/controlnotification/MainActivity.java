package com.example.controlnotification;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;



import android.annotation.SuppressLint;
import android.app.Activity;


import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;


import android.widget.TextView;



public class MainActivity extends Activity
{

	int countT1 = 0;
	int countT2 = 0;
	Socket socket;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		

		final Button btnSendtime = (Button) findViewById(R.id.btnSendtime);
		final TextView input = (TextView)findViewById(R.id.txtInput);
		final Button btnPlusT1 = (Button) findViewById(R.id.btnPlusT1);
		final Button btnPlusT2 = (Button) findViewById(R.id.btnPlusT2);
		final Button btnMinT1 = (Button) findViewById(R.id.btnMinT1);
		final Button btnMinT2 = (Button) findViewById(R.id.btnMinT2);
		final TextView txtOutput = (TextView)findViewById(R.id.txtOutput);
		final Button btnBreak = (Button) findViewById(R.id.btnBreak);

		
		final Button btnReset = (Button) findViewById(R.id.btnReset);

		
		btnPlusT1.setOnClickListener(new View.OnClickListener(){
		
		
			public void onClick(View v) {
				
				countT1++;
				String counts = String.valueOf(countT1);
				txtOutput.setText("Team 1:  " + counts);
				String team = "t1";
				String data = team + counts;
				try {
					test(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPlusT2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				
				countT2++;
				String counts = String.valueOf(countT2);
				txtOutput.setText("Team 2:  " + counts);
				String team = "t2";
				String data = team + counts;
				try {
					test(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			}
		});
		btnMinT1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				
				countT1--;
				String counts = String.valueOf(countT1);
				txtOutput.setText("Team 1:  " + counts);
				String team = "t1";
				String data = team + counts;
				try {
					test(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnMinT2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				
				countT2--;
				String counts = String.valueOf(countT2);
				txtOutput.setText("Team 2:  " + counts);
				String team = "t2";
				String data = team + counts;
				try {
					test(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnReset.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v) {

				countT1=0;
				countT2=0;
				
				txtOutput.setText("Team 1  " + countT1 + ":"+ countT2 +"  Team 2");
				String reset = "reset";
				try {
					test(reset);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	
		btnSendtime.setOnClickListener(new View.OnClickListener() {

		    public  void onClick(View v) {
		        // Handle click event.
		    	
		    	String string = null;
		    	int time;
				CharSequence inputstring = input.getText();
				if (inputstring.length()==0){
					string = "10";
					txtOutput.setText("Die Spielzeit beträgt: "+ string + " Minuten!");
					// send default value
				}else{
				time = Integer.parseInt(inputstring.toString());
				string = String.valueOf(inputstring);
				txtOutput.setText("Die Spielzeit beträgt: " + string + " Minuten!");
				
				}
				

				try {
					test(string);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
	});
	
		
		btnBreak.setOnClickListener(new View.OnClickListener(){
			
			
			public void onClick(View v) {
				
				String breakmessage ="stop";
				txtOutput.setText("Die Spielzeit wurde angehalten! "  + "'"+breakmessage+"'");

				try {
					test(breakmessage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// send- if the arduino is getting this string, he has to stop the timer!
				
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	  void test(String s) throws IOException {
		  String outputmessage;
		  String inputmessage;
		  String ip="192.168.0.13";
		  int port = 1234;
		  Socket clientSocket = new Socket(ip,port);
		  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		  outputmessage = s;
		  outToServer.writeBytes(outputmessage + '\n');
		  inputmessage= inFromServer.readLine();
		  System.out.println("FROM SERVER: " + inputmessage);
		  clientSocket.close();
		 }
}