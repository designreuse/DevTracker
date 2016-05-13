package com.tmt.logistics.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmt.logistics.bean.LinkClient;
import com.tmt.logistics.dao.LinkClientDao;

@Controller
public class LinkClientController {

 @Autowired
 LinkClientDao linkClientDao;

 @RequestMapping("/linkClient")
	public ModelAndView retrieveLinkVehicleData(@ModelAttribute LinkClient linkClient) {		 
	   	
	 LinkClient linkedClient = linkClientDao.retrieveLinkClientData(linkClient);	
	   
		Map<String, List> map = new HashMap<String, List>();		  
		map.put("userList", linkedClient.getUserList());
		map.put("clientList", linkedClient.getClientList());
	 
		return new ModelAndView("LinkClient", "map", map);
		}
 
 @RequestMapping("/updateLinkClient")
	public String updateLinkedClientData(@ModelAttribute LinkClient linkClient) {		
	   linkClientDao.updateClientLink(linkClient);	 	
		return "redirect:/vehicleDetails";
	}
}
