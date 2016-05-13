package com.tmt.logistics.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
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
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tmt.common.Geocoder;
import com.tmt.logistics.bean.TrackCustomerDetails;
import com.tmt.logistics.dao.TrackCustomerDao;

@Controller
public class TrackCustomerController {

 @Autowired
 TrackCustomerDao trackCustomerDao;

 @RequestMapping("/linkAgent")
	public ModelAndView linkAgentScreen(@ModelAttribute TrackCustomerDetails trackCustomerDetails) {		 
	    
	    Map<String, List> map = new HashMap<String, List>();	    
	  //List<String> vehicleTypeList = vehicleDao.fetchVehicleType();	    
		map.put("agentList", Arrays.asList("Agent1", "Agent2", "Agent3"));	 
		return new ModelAndView("LinkAgent", "map", map);
		}
 
 @RequestMapping("/deliveryAddress")
	public ModelAndView deliveryAddress(HttpServletRequest request, HttpServletResponse response) {	
	 String id = request.getParameter("id").trim();
	 System.out.println("Comming In "+id);
	 List<TrackCustomerDetails> addressList = trackCustomerDao.retrieveAddressDetails(id);
	 return new ModelAndView("DeliveryAddressDetails", "trackCustomerDetails", addressList);
	}
 
 @RequestMapping("/trackReport")
	public ModelAndView trackReportScreen(HttpServletRequest request, HttpServletResponse response) {		 
	    String id = request.getParameter("id").trim();
	    System.out.println("Comming In "+id);
	    Map<String, List> map = new HashMap<String, List>();	    
	    List<TrackCustomerDetails> invoiceList = trackCustomerDao.retrieveInvoiceDetails(id);
		 return new ModelAndView("TrackCustomerReport", "trackCustomerDetails", invoiceList);
		}
 
 @RequestMapping("/saveDeliveryAddress")
	public void saveDeliveryAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
	 String detachId = request.getParameter("latlng").trim();
	 String[] splitToken = detachId.split("#");
	 TrackCustomerDetails trackCustomerDetails = new TrackCustomerDetails();
	 trackCustomerDetails.setLatitude(Double.parseDouble(splitToken[0]));
	 trackCustomerDetails.setLongitude(Double.parseDouble(splitToken[1]));
	 trackCustomerDetails.setGodown_name(splitToken[2]);
	 trackCustomerDetails.setParent_id(splitToken[3]);
	 trackCustomerDetails.setAddress(Geocoder.getAddress(trackCustomerDetails.getLatitude(), trackCustomerDetails.getLongitude()));
	 
	 int getCount  = trackCustomerDao.isGodownNameExists(trackCustomerDetails.getGodown_name());
	 System.out.println("------------> "+getCount);
	 if(getCount == 0){
	  trackCustomerDao.saveDeliveryAddress(trackCustomerDetails);
	  updatePrinterObject(response, true);
	 }else{
		 updatePrinterObject(response, false);
	 }
	}
 
 private void updatePrinterObject(HttpServletResponse response, boolean respFlag)throws ServletException, IOException{		
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
     
     String responseFlag = respFlag == true ? "success" : "error";
     System.out.println(">>>>>>>>>>>>>>>>>>>>>> "+responseFlag);
     myObj.addProperty("success", respFlag);	        
     
     out.println(myObj.toString());	 
     out.close();
	}
}
