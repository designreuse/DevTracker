package com.tmt.logistics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmt.logistics.bean.LiveTrack;
import com.tmt.logistics.bean.VehicleDetails;
import com.tmt.logistics.dao.AgentDao;
import com.tmt.logistics.dao.VehicleSearchDao;

@Controller
public class LiveTrackingController {
	
 @Autowired
VehicleSearchDao vehicleSearchDao;
 
 @Autowired 
AgentDao agentDao;	
	

 @RequestMapping("/liveTrack")
	public ModelAndView vehicleSearch(@ModelAttribute LiveTrack liveTrack, HttpSession session) {	  
	    Map<String, List> map = new HashMap<String, List>();	    
	    liveTrack.setParent_id((String)session.getAttribute("handler_id"));
	    liveTrack.setRole_id((String)session.getAttribute("role_id"));	   
		//List<String> vehicleNumList = vehicleSearchDao.retrieveVehicleNumbersList(liveTrack);
		//map.put("vehicleNum", vehicleNumList);	 
		return new ModelAndView("LiveTrack", "map", map);
	}
 

}
