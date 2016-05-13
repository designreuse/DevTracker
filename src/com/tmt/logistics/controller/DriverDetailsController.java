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

import com.tmt.logistics.bean.DriverDetails;
import com.tmt.logistics.bean.ResultBean;
import com.tmt.logistics.dao.DriverDao;

@Controller
public class DriverDetailsController {

 @Autowired
 DriverDao driverDao;

 @RequestMapping("/driverDetails")
	public ModelAndView getTrackerScreen(@ModelAttribute DriverDetails driverDetails, HttpSession session) {	
	 driverDetails.setParent_id((String)session.getAttribute("handler_id"));
	 driverDetails.setRole_id((String)session.getAttribute("role_id"));
	 System.out.println(driverDetails.getRole_id()+" ==========> "+driverDetails.getParent_id());
	   	List<String> vehicleList = driverDao.getVehicleList(driverDetails);	
		Map<String, List> map = new HashMap<String, List>();		  
		map.put("vehicleList", vehicleList);		 
		return new ModelAndView("driverDetails", "map", map);
		}

 @RequestMapping("/confirmDriver")
	public ModelAndView confirmVehicleData(@ModelAttribute DriverDetails driverDetails) {
	 return new ModelAndView("ConfirmDriverDetails", "map", null);
	}
 
 @RequestMapping("/driver")
	public ModelAndView insertDriverData(@ModelAttribute DriverDetails driverDetails) {
	 	String handlerId  = driverDao.getVehicleHandlerId(driverDetails.getVehicleNumber());
	 	driverDetails.setHandlerId(handlerId);
		int resultPage =  driverDao.insertDriverData(driverDetails);
		ResultBean resultMsg = new ResultBean();
		if(resultPage == 1){
			resultMsg.setSuccessMessage("Vehicle number "+driverDetails.getVehicleNumber()+" has been successfully associated with the driver "+driverDetails.getDriverName()+"!");
			resultMsg.setShowResult("1");
		}else{			
			resultMsg.setErrorMessage("Vehicle number "+driverDetails.getVehicleNumber()+" and driver "+driverDetails.getDriverName()+"has not been associated!");
			resultMsg.setShowResult("0");			
		}
		resultMsg.setPageUrl("/DevTracker/driverDetails");
		return new ModelAndView("ResultPage", "resultMsg", resultMsg);
	}
}
