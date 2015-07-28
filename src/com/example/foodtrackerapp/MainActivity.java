package com.example.foodtrackerapp;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import java.io.*;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.net.URL;

import android.os.AsyncTask;
import android.app.ProgressDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	String web_info = "";
	String web_info2 = "";
	
	String brand_name = "";
	String brand_name2 = "";
	
	String product_code = "error";
	boolean threadStarted = false;
	private AlertDialog.Builder dialogBuilder; 
	
	String filename = "myfile";
	String string = "Hello world!";
	FileOutputStream outputStream;
	
	 EditText textmsg;
	 
	 String MY_FILE_NAME = "mytextfile.txt";
	 
	 
	 static final int READ_BLOCK_SIZE = 100;	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//TODO: Start the database here (I think this is the right place) in a separate thread.
		//Database needs to be read from and written to by many activities including this one...
		//How can I share it between activities?
		
		/*if(file.exists()) 
		try {
			FileOutputStream fileos = openFileOutput(MY_FILE_NAME, Context.MODE_PRIVATE);
			FileInputStream fileis = openFileInput(MY_FILE_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
		// Create a new file input stream.
		


		
		//textmsg=(EditText)findViewById(R.id.editText1);
		
        final Button button = (Button) findViewById(R.id.graph_back);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	Intent i = new Intent(getApplicationContext(), BlueToothActivity.class);
            	startActivity(i);
            	
            }

        });
        
        final Button button2 = (Button) findViewById(R.id.grapher);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	Intent i = new Intent(getApplicationContext(), GraphActivity.class);
            	startActivity(i);
            	
            }

        });	 
        
        final Button button3 = (Button) findViewById(R.id.FileIOButton);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	Intent i = new Intent(getApplicationContext(), FileActivity.class);
            	startActivity(i);
            }
        });	        
             

		
	}	
	

	public void foodEntryDialog()
	{
		final String[] optionsStr = {"OKAY","CANCEL"};
		dialogBuilder = new AlertDialog.Builder(this);
		//dialogBuilder.setTitle(brand_name2);
		dialogBuilder.setTitle(brand_name2);

		dialogBuilder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
	        	finish();
	        	startActivity(getIntent());	
			}			
			
			
		});
		dialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
	        	finish();
	        	startActivity(getIntent());	
			}			
			
			
		});		

		AlertDialog dialogConfirm = dialogBuilder.create();
		dialogConfirm.show();
	}
	
	public void updateWebInfo()
	{
		
		final TextView web_output = (TextView) findViewById(R.id.web_text_view);
		
	    MainActivity.this.runOnUiThread(new Runnable() {

	        public void run() {
	        	brand_name = getAtrribute("product_name" + '"' + ':' + '"', '"') + ",Calories:," + getAtrribute("calories" + '"' + ':', ',');
	        	web_output.setText(brand_name);

	        }
	    });		
	}
	
	public void updateWebInfo2()
	{
		
		final TextView web_output2 = (TextView) findViewById(R.id.web_text_view2);

		
	    MainActivity.this.runOnUiThread(new Runnable() {
	        public void run() {
	        	brand_name2 = "name," + getDigitEyesName("Lookup Results : ", 3) + ",calories," + getAtrribute2("Calories ", ' ') + ",fat," + getAtrribute2("Total Fat ", 'g');
	        	web_output2.setText(brand_name2);
	        	foodEntryDialog();
	        	writeToFile(brand_name2); //TODO: Replace with call to method which writes a new record to the database or updates the record if it already exists
	        }
	    });		
	}	
	//TODO: Replace the 4 'getter' methods below with a JSON parser method 
	//to easily get all attributes by name
	public String getDigitEyesName(String key, int stopper)
	{
		String b = "";
		int index = 0;
		int counter = 0;
		
		int a = web_info2.indexOf(key) + key.length();
		
		while(counter < stopper)
		{
			b += web_info2.charAt(a + index);
			
			if(web_info2.charAt(a + index) == ' ')
				counter++;
			
			index++;
		}
		//updateWebInfo();
		return b;	
	}		
	
	public String getAtrribute(String key, char stopper)
	{
		String b = "";
		int index = 0;
		
		int a = web_info.indexOf(key) + key.length();
		
		while(web_info.charAt(a + index) != stopper)
		{
			b += web_info.charAt(a + index);
			index++;
		}
		//updateWebInfo();
		return b;	
	}
	
	public String getAtrribute2(String key, char stopper)
	{
		String b = "";
		int index = 0;
		
		int a = web_info2.indexOf(key) + key.length();
		
		boolean stopme = false; 
		
		while(!stopme)
		{
			if(Character.isDigit(web_info2.charAt(a + index)))
			{
				b += web_info2.charAt(a + index);
			}
			else
				stopme = true;
			
			index++;
		}
		//updateWebInfo();
		return b;	
	}	
	
	public String GetFoodName(String input)
	{
		String b = "";
		int index = 0;
		
		int a = input.indexOf("name,") + "name,".length();
		
		boolean stopme = false; 
		
		while(!stopme)
		{
			if(input.charAt(a + index) != ',')
				b += input.charAt(a + index);
			else
				stopme = true;
			
			index++;
		}
		//updateWebInfo();
		return b;	
	}		
	
	

	//TODO: Replace with Zxing code (bundle the code with this app 
	//rather than relying on a separate app being installed)
	static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
	ProgressDialog mProgressDialog;
	String Url = "http://api.v3.factual.com/t/products-cpg-nutrition?filters={%22upc%22:%22071439000060%22}&KEY=0piVpfxqJcqfJZACLLaDC8x3eAxhwYyxY95B4b7F";



	//product barcode mode
	public void scanBar(View v) {
		try {
			//start the scanning activity from the com.google.zxing.client.android.SCAN intent
			Intent intent = new Intent(ACTION_SCAN);
			intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
			startActivityForResult(intent, 0);
		} catch (ActivityNotFoundException anfe) {
			//on catch, show the download dialog
			showDialog(MainActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
		}
	}
	
	//product qr code mode
	public void scanQR(View v) {
		try {
			//start the scanning activity from the com.google.zxing.client.android.SCAN intent
			Intent intent = new Intent(ACTION_SCAN);
			intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
			startActivityForResult(intent, 0);
		} catch (ActivityNotFoundException anfe) {
			//on catch, show the download dialog
			showDialog(MainActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
		}
	}

	//alert dialog for downloadDialog
	private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
		AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
		downloadDialog.setTitle(title);
		downloadDialog.setMessage(message);
		downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialogInterface, int i) {
				Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				try {
					act.startActivity(intent);
				} catch (ActivityNotFoundException anfe) {

				}
			}
		});
		downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialogInterface, int i) {
			}
		});
		return downloadDialog.show();
	}
	
	
	
	Thread downloadThread = new Thread() {                     
	    public void run() 
	    {  
	    	
		        Document doc;
		        Document doc2;

		        String key = "0piVpfxqJcqfJZACLLaDC8x3eAxhwYyxY95B4b7F";
		        String key2 = "/zQ9wlzc2g4z";
		        String test_url = "http://api.v3.factual.com/t/products-cpg-nutrition?filters=%7B%22upc%22:%22"+ product_code + "%22%7D&KEY=" + key;
		        String test_url2 = "http://www.digit-eyes.com/upcCode/" + product_code + ".html?l=en#.VPdzzvm-2m5" + key2;
		        
		        try 
		        {                                              
		            doc = Jsoup.connect(test_url).ignoreContentType(true).get();        
		            web_info = doc.text(); 
		            //updateWebInfo();
		        } 
		        catch (IOException e) 
		        { 
		        	e.printStackTrace();                        
		        }
		        
		        try 
		        {                                              
		            doc2 = Jsoup.connect(test_url2).ignoreContentType(true).get();        
		            web_info2 = doc2.text(); 
		            updateWebInfo2();

		        } 
		        catch (IOException e) 
		        { 
		        	e.printStackTrace();                        
		        }		        
		        
		        try {
		        	  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
		        	  outputStream.write(string.getBytes());
		        	  outputStream.close();
		        	} catch (Exception e) {
		        	  e.printStackTrace();
		        	}
		        
		        
	        
	    }                                                      
	};
	 
	//on ActivityResult method
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	
	TextView output = (TextView) findViewById(R.id.scan_result);
	
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				//get the extras that are returned from the intent
				String contents = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				String test_url = "http://api.v3.factual.com/t/products-cpg-nutrition?filters={%22upc%22:%22" + contents + "%22}&KEY=0piVpfxqJcqfJZACLLaDC8x3eAxhwYyxY95B4b7F";
				
				output.setText(contents);
				product_code = contents;
				
				downloadThread.start();
				

			}
		}
	}
	
	
	//TODO: Replace this method and all references to it with a method 
	//that writes a new record to the database or updates the record if it already exists.
	public void writeToFile(String input) {
		
		//TextView debugger = (TextView) findViewById(R.id.file_output_view);
		
		String filename = "FoodTrackerData";
		String string = input;
		
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
				
				String NameInput	= GetFoodName(input);
				
				Log.d("name:", NameInput);
				while ((data = reader.readLine()) != null) {
					
					
					if(!NameInput.equals(GetFoodName(data)))
						sbuffer.append(data + "\n");
					
					Log.d("name:", GetFoodName(data));
					
				}
				is.close();
				
				
				string += ('\n' + sbuffer.toString());
				
				string = string.substring(0, string.length() - 1);
				
				//debugger.setText(string);
				
			} catch (IOException e) 
			{
				try 
				{
				  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
				  //outputStream.write(string.getBytes());
				  outputStream.close();
				  
				  //debugger.setText(string);
				  
				} 
				catch (Exception m) 
				{
				  m.printStackTrace();
				  //debugger.setText("file not saved");
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
		  //debugger.setText("file not saved");
		}
	    
	}	
	}	
	
}

	
