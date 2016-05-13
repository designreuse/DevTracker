package com.tmt.logistics.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tmt.common.Utils;
import com.tmt.logistics.bean.Coordinates;
import com.tmt.logistics.dao.TrackerConfigureDao;

@Controller
public class TrackerConfigureController {

	@Autowired
	TrackerConfigureDao trackerConfigureDao;
	
	@RequestMapping("/teTracker")
	 protected void testTracker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String imeiNumber = request.getParameter("imeiNumber").trim();
			System.out.println("IMEI-------------- "+imeiNumber);
			Coordinates coordinates = trackerConfigureDao.getLastTrackedDate(imeiNumber);
			System.out.println("***************** "+Utils.showBean(coordinates, true));
			
			PrintWriter out = response.getWriter();
	        response.setContentType("text/html");
	        response.setHeader("Cache-control", "no-cache, no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Expires", "-1");
	 
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.setHeader("Access-Control-Max-Age", "86400");
	        
	        
	        JsonObject myObj = new JsonObject();
	        if(coordinates != null){
	        	Gson gson = new Gson(); 
	        	JsonElement coordinatesInfoObj = gson.toJsonTree(coordinates);
	        	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "+coordinatesInfoObj);
		        myObj.addProperty("success", true);	        
		        myObj.add("coordinatesInfo", coordinatesInfoObj);
	        }else{
	        	myObj.addProperty("success", false);
	        }
	        
	        out.println(myObj.toString());	 
	        out.close();
		}
		
	@RequestMapping("/deTracker")
	 protected void detachtracker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String imeiNumber = request.getParameter("imeiNumber").trim();
			trackerConfigureDao.updateDetachTracker(imeiNumber);
			updatePrinterObject(response);
		}
	
	@RequestMapping("/diTracker")
	 protected void disableTracker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String imeiNumber = request.getParameter("imeiNumber").trim();
			trackerConfigureDao.updateDisableTracker(imeiNumber);
			updatePrinterObject(response);
		}
	
	@RequestMapping("/acTracker")
	 protected void activatetracker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String imeiNumber = request.getParameter("imeiNumber").trim();
			trackerConfigureDao.updateActivateTracker(imeiNumber);
			updatePrinterObject(response);	 
		}
	
	private void updatePrinterObject(HttpServletResponse response)throws ServletException, IOException{
		
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
 
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        
        Gson gson = new Gson(); 
        JsonObject myObj = new JsonObject();
        myObj.addProperty("success", true);	        
        
        out.println(myObj.toString());	 
        out.close();
	}
	 
	/* private DetachTracker getInfo(String vehicleNumber) {		 
		 DetachTracker detachTrackerObj = detachDao.getDetachedUser(vehicleNumber);
		 return detachTrackerObj;
	 }*/
	
}
