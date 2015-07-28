package com.example.foodtrackerapp;
 
public class WeightActivity extends Activity {
 
	ArrayList<String> difficultyLevelOptionsList = new ArrayList<String>();
	 TextView txtView1;
	 Spinner spinner;
	 

	    @Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_weight);
	        
	        spinner = (Spinner) findViewById(R.id.spinner1); 
	    
	  
	        InputStream weight_file = null;
			
			try 
			{
				weight_file = openFileInput("weight_file");
			} 
			catch (FileNotFoundException e1) 
			{
						  
				try 
				{
				FileOutputStream outputStream;
				  outputStream = openFileOutput("weight_file", Context.MODE_PRIVATE);
				  outputStream.write("".getBytes());
				  outputStream.close();
				} 
				catch (Exception e) 
				{
				  e.printStackTrace();
				}
				e1.printStackTrace();
			}
	 
	  // Create the ArrayAdapter
	  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(WeightActivity.this
	            ,android.R.layout.simple_spinner_item,difficultyLevelOptionsList);
	  
	                 // Set the Adapter
	  spinner.setAdapter(arrayAdapter);
	  arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  
	  PopulateSpinner();
	  // Set the ClickListener for Spinner
	  spinner.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() { 

	              public void onItemSelected(AdapterView<?> adapterView, 
	             View view, int i, long l) { 
	             // TODO Auto-generated method stub
	          Toast.makeText(WeightActivity.this,"You Selected : "
	           + difficultyLevelOptionsList.get(i)+" Level ",Toast.LENGTH_SHORT).show();
	             
	               }
	                // If no option selected
	    public void onNothingSelected(AdapterView<?> arg0) {
	     // TODO Auto-generated method stub
	          
	    } 

	        });

	               
	 }
	    
		public void PopulateSpinner() {
			
			//TextView debugger = (TextView) findViewById(R.id.file_output_view);
			
			String filename = "FoodTrackerData";
			String string = "";
			
			FileOutputStream outputStream;
			
			String data = "";
			String in_data = "";
			
			StringBuffer sbuffer = new StringBuffer();
			
			//Try to open if its there
			InputStream is = null;
			
			try 
			{
				is = openFileInput(filename);
			} catch (FileNotFoundException e1) 
			{
						  
				  
				e1.printStackTrace();
			}
			
			
			if (is != null) {
				try {
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));				
									

					while ((data = reader.readLine()) != null) {
						
							sbuffer.append(data + "\n");
							difficultyLevelOptionsList.add(data);
												
					}
					is.close();

					
				} catch (IOException e) 
				{
					  e.printStackTrace();
				}	
				
		    
		}

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
	    

	}