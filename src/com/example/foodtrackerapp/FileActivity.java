package com.example.foodtrackerapp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FileActivity extends Activity {
	
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file);
		
		LoadFile();
		
		final Button clearButton = (Button) findViewById(R.id.clear);
		
		clearButton.setOnClickListener(new View.OnClickListener()
	    {
	        public void onClick(View v)
	        {
	        	ClearFile();
	        }
	    });		
		
			
	}
	
	public void SaveAFile()
	{
		
		writeToFile("food 100","FoodTrackerData");
					
	}	
	
	public void ClearFile()
	{
		
		FileOutputStream outputStream;
		String filename = "FoodTrackerData";
		String string = "";
		TextView debugger = (TextView) findViewById(R.id.file_output_view);
		
		try 
		{
		  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
		  outputStream.write(string.getBytes());
		  outputStream.close();
		  debugger.setText(string);
		} 
		catch (Exception e) 
		{
		  e.printStackTrace();
		  debugger.setText("file not saved");
		}
	}
	
	public void LoadFile() {
		
		TextView debugger = (TextView) findViewById(R.id.file_output_view);
		
		String filename = "FoodTrackerData";
		String data = "";
		
		FileOutputStream outputStream;
		StringBuffer sbuffer = new StringBuffer();
		
		InputStream is = null;
		
		try {
			is = openFileInput("FoodTrackerData");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if (is != null) {
			try {
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));				
				
				while ((data = reader.readLine()) != null) {
					sbuffer.append(data + "\n");
				}
				is.close();
				
				
				debugger.setText(sbuffer.toString());
				
			} catch (IOException e) 
			{
				 debugger.setText("file not found");
			}	
			    
	}	
	}
	
	public void writeToFile(String input, String file_str) {
		
		TextView debugger = (TextView) findViewById(R.id.file_output_view);
		
		String filename = "FoodTrackerData";
		String string = "RFID,calories,sugar,fat,vitaminC";
		
		FileOutputStream outputStream;
		
		String data = "";
		String in_data = input;
		
		StringBuffer sbuffer = new StringBuffer();
		
		//Try to open if its there
		InputStream is = null;
		
		try {
			is = openFileInput("FoodTrackerData");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if (is != null) {
			try {
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));				
				
				while ((data = reader.readLine()) != null) {
					sbuffer.append(data + "\n");
				}
				is.close();
				
				
				string += ('\n' + sbuffer.toString());
				
				string = string.substring(0, string.length() - 1);
				
				debugger.setText(string);
				
			} catch (IOException e) 
			{
				try 
				{
				  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
				  outputStream.write(string.getBytes());
				  outputStream.close();
				  
				  debugger.setText(string);
				  
				} 
				catch (Exception m) 
				{
				  m.printStackTrace();
				  debugger.setText("file not saved");
				}
			}	
			
			

		try 
		{
		  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
		  outputStream.write(string.getBytes());
		  outputStream.close();
		  
		} 
		catch (Exception e) 
		{
		  e.printStackTrace();
		  debugger.setText("file not saved");
		}
	    
	}	
	}
	

	
}
