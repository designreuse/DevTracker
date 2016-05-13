package com.tmt.logistics.controller;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tmt.logistics.bean.Coordinates;
import com.tmt.logistics.bean.PlayForPay;
import com.tmt.logistics.dao.CoordinatesDao;

@Controller
public class StartupPageController {
	
	 @Autowired
	 CoordinatesDao coordinatesDao;

	@RequestMapping("/startup")
	public ModelAndView homeScreen() {

		return new ModelAndView("startup", "map", null);
	}
	
	@RequestMapping("/payForPlay")
	public ModelAndView payForPlay(Model playForPay) {
		playForPay.addAttribute("PlayForPay", new PlayForPay());		
		return new ModelAndView("PayforPlay", "map", null);
	}

	@RequestMapping("/showmap")
	public ModelAndView mapScreen() {

		return new ModelAndView("showMap", "map", null);
	}
	
	@RequestMapping("/fetch")
	 protected void getCoordinates(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String vehicleNumber = request.getParameter("vehicleNumber").trim();
			System.out.println("====> "+vehicleNumber);
			
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
	 
	        Coordinates coordinateInfo = getInfo(vehicleNumber);
	        System.out.println("@@@@@@@@@@@@@@ "+coordinateInfo.getLatitude());
	        JsonElement coordinateInfoObj = gson.toJsonTree(coordinateInfo);
	        if(coordinateInfo.getImei() == null){
	            myObj.addProperty("success", false);
	        }
	        else {
	            myObj.addProperty("success", true);
	        }
	        myObj.add("coordinateInfo", coordinateInfoObj);
	        out.println(myObj.toString());
	 
	        out.close();
		}
	
	private Coordinates getInfo(String vehicleNumber) {		 
		Coordinates coordinateObj = coordinatesDao.getCoordinates(vehicleNumber);
		 return coordinateObj;
	 }
			
}
