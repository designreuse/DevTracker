package com.tmt.logistics.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tmt.logistics.bean.DetachTracker;
import com.tmt.logistics.bean.TrackerConfigure;
import com.tmt.logistics.dao.DetachDao;

@Controller
public class DetachTrackerController {

	@Autowired
	DetachDao detachDao;
		
	@RequestMapping("/detachTracker")
	public ModelAndView getTrackerScreen(@ModelAttribute DetachTracker detachTracker) {			
		    List<String> vehicleList = detachDao.getDetachUserList();	
			Map<String, List> map = new HashMap<String, List>();		  
			map.put("vehicleList", vehicleList);		
			return new ModelAndView("detachTracker", "map", map);
		}
	
	@RequestMapping("/trackerConfigure")
	public ModelAndView getTrackerConfigure(@ModelAttribute TrackerConfigure trackerConfigure) {			
			List<TrackerConfigure> trackConfList = detachDao.getTrackerData();				
			return new ModelAndView("TrackerConfigure", "trackConfList", trackConfList);			
		}

	
	@RequestMapping(value="/detach", method = RequestMethod.POST, params = { "detach" })
	public String updateDetachedData(@ModelAttribute DetachTracker detachTracker) {
		System.out.println("Detach Tracker Detail :::>>>>  " + detachTracker.getImei()+" ------> "+detachTracker.getMobile()+" ---> "+detachTracker.getProvider()+ " -------> "+detachTracker.getSim());
		detachDao.updateDetachedData(detachTracker.getImei(), "detach");
		detachDao.updateDetachVehicleConnector(detachTracker.getConnectedWith());
		return "redirect:/detachTracker";
	}
	
	@RequestMapping(value="/detach", method = RequestMethod.POST, params = { "disable" })
	public String updateDisabledData(@ModelAttribute DetachTracker detachTracker) {
		System.out.println("Detach Tracker Detail :::====  " + detachTracker.getImei()+" ------> "+detachTracker.getMobile()+" ---> "+detachTracker.getProvider()+ " -------> "+detachTracker.getSim());
		detachDao.updateDetachedData(detachTracker.getImei(), "disable");
		return "redirect:/detachTracker";
	}
	
	
	/*@RequestMapping("/detachDevice")
	public String updateDetachDevice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String vehicleNumbr = request.getParameter("vehicleNumber").trim();
		String imeiNumbr = request.getParameter("imei").trim();
		System.out.println("Detach Tracker Detail:::  " + vehicleNumbr+" ------> "+imeiNumbr);
		detachService.updateDetachedData(vehicleNumbr, imeiNumbr);
		return "redirect:/detachTracker";
	}*/
	
	@RequestMapping("/detached")
	 protected void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String vehicle = request.getParameter("vehicleNumber").trim();
			System.out.println("########################### "+vehicle);
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
	 
	        DetachTracker detachTrackerInfo = getInfo(vehicle);
	        JsonElement detachTrackerInfoObj = gson.toJsonTree(detachTrackerInfo);
	        if(detachTrackerInfo.getImei() == null){
	            myObj.addProperty("success", false);
	        }
	        else {
	            myObj.addProperty("success", true);
	        }
	        myObj.add("detachTrackerInfo", detachTrackerInfoObj);
	        out.println(myObj.toString());
	 
	        out.close();
		}
	 
	 private DetachTracker getInfo(String vehicleNumber) {		 
		 DetachTracker detachTrackerObj = detachDao.getDetachedUser(vehicleNumber);
		 return detachTrackerObj;
	 }
	
}
