package com.tmt.logistics.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmt.logistics.bean.AssignVehicle;
import com.tmt.logistics.bean.Coordinates;
import com.tmt.logistics.bean.ResultBean;
import com.tmt.logistics.bean.VehicleDetails;
import com.tmt.logistics.dao.AgentDao;
import com.tmt.logistics.dao.VehicleSearchDao;

@Controller
public class LiveTrackingController {
	
 @Autowired
VehicleSearchDao vehicleSearchDao;
 
 @Autowired 
AgentDao agentDao;	
	

 @RequestMapping("/vehicleSearch")
	public ModelAndView vehicleSearch(@ModelAttribute VehicleDetails vehicleDetail, HttpSession session) {	  
	    Map<String, List> map = new HashMap<String, List>();	    
	    vehicleDetail.setParent_id((String)session.getAttribute("handler_id"));
	    vehicleDetail.setRole_id((String)session.getAttribute("role_id"));	   
		List<String> vehicleNumList = vehicleSearchDao.retrieveVehicleNumbersList(vehicleDetail);
		map.put("vehicleNum", vehicleNumList);	 
		return new ModelAndView("VehicleSearch", "map", map);
	}
 
 @RequestMapping("/locateVehicle")
	public ModelAndView locateVehicle(@ModelAttribute VehicleDetails vehicleDetails) throws ServletException, IOException {
	 Map<String, List<?>> map;	
	 System.out.println("----Locate Vehicle-------> ");
		//String vehicleNumber = request.getParameter("vehicleNumber").trim().toUpperCase();
		System.out.println("Vehicle Number :: "+vehicleDetails.getVehicleNumber());
		System.out.println("Role :: "+vehicleDetails.getRole_id());
		System.out.println("Parent :: "+vehicleDetails.getParent_id());
		
		String imeiNumber = agentDao.retrieveIMEINumber(vehicleDetails.getVehicleNumber());
		System.out.println(vehicleDetails.getVehicleNumber()+" ---->>>>>>>>>>>>>>>> "+imeiNumber);
		String imeiStatus = agentDao.isIMEINumberActive(imeiNumber);
		int imeiCount = vehicleSearchDao.isIMEIExists(imeiNumber);
		 if(imeiCount == 0 || imeiNumber == null || "".equals(imeiNumber) || !"Active".equalsIgnoreCase(imeiStatus)){
			 ResultBean resultMsg = new ResultBean();
			 resultMsg.setErrorMessage("Vehicle Number "+vehicleDetails.getVehicleNumber()+" is un-available to track!");
			 resultMsg.setShowResult("0");
			 resultMsg.setPageUrl("/DevTracker/vehicleSearch");
			 return new ModelAndView("ResultPage", "resultMsg", resultMsg);
			}else{
				JSONArray jObj = vehicleSearchDao.retrieveVehicleData(imeiNumber, vehicleDetails.getVehicleNumber());					
				map = new HashMap<String, List<?>>();
				map.put("coordinates", jObj);
				//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@ "+jObj);
				List<String> vehicleNumList = vehicleSearchDao.retrieveVehicleNumbersList(vehicleDetails);
				//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ "+vehicleNumList);				
				JSONArray myArray = new JSONArray();
				myArray.addAll(vehicleNumList);
				map.put("vehicleNum", vehicleNumList);	
			}
		
		
		return new ModelAndView("VehicleSearch", "map", map);
	}
}
