package com.tmt.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public  class Geocoder {
	private final static String MARKER = "\"formatted_address\" : \"";
	
	public static String getAddress(double latitude, double longitude)throws IOException {
		 BufferedReader reader = null;	
		 String linreRees = "";
		 try {	        
	            URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude + "&sensor=false");
	            URLConnection connection = url.openConnection();

	            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
	            
	            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),Charset.forName("UTF-8")));

	            // Find address line
	            String line;
	            while ((line = reader.readLine()) != null) {
	                int index = line.indexOf(MARKER);
	                if (index != -1) {
	                	linreRees = line.substring(index + MARKER.length(), line.length() - 2); 	                	
	                	break;
	                }
	            }	           
	            System.out.println("ADD  :: "+linreRees);
	        } catch(IOException e){      
	        	e.printStackTrace();
	        }finally{
				reader.close();
	        }
		 
		 return linreRees;
	    }

	
	
}
