package com.tmt.logistics.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmt.logistics.bean.ResultBean;
import com.tmt.logistics.bean.VehicleDetails;
import com.tmt.logistics.dao.VehicleDao;

@Controller
public class VehicleDetailsController {

 @Autowired
 VehicleDao vehicleDao;

 @RequestMapping("/vehicleDetails")
	public ModelAndView getTrackerScreen(@ModelAttribute VehicleDetails vehicleDetails) {		 
	    
	    Map<String, List> map = new HashMap<String, List>();	    
	   List<String> vehicleTypeList = vehicleDao.fetchVehicleType();	    
		map.put("vehicleType", vehicleTypeList);	 
		return new ModelAndView("VehicleDetails", "map", map);
		}
 
 @RequestMapping("/confirmVehicleData")
	public ModelAndView confirmVehicleData(@ModelAttribute VehicleDetails vehicleDetails) {
	 ResultBean resultMsg = new ResultBean();
	 int getVehicleCount = vehicleDao.isVehicleNumberAlreadyExists(vehicleDetails.getVehicleNumber());
	 if(getVehicleCount > 0){
		 resultMsg.setErrorMessage("Vehicle Number "+vehicleDetails.getVehicleNumber()+" is already been registered!");
		 resultMsg.setShowResult("0");
		 resultMsg.setPageUrl("/DevTracker/vehicleDetails");
		 	return new ModelAndView("ResultPage", "resultMsg", resultMsg);
	 }else
		 return new ModelAndView("ConfirmVehicleDetails", "map", null);
	 
	}
 
 @RequestMapping("/insertVehicleData")
	public ModelAndView insertVehicleData(@ModelAttribute VehicleDetails vehicleDetails) {
	 
	        ResultBean resultMsg = new ResultBean();
	 	
	 		int resultPage = vehicleDao.insertVehicleData(vehicleDetails);
	 		if(resultPage == 1){
				resultMsg.setSuccessMessage("Vehicle number "+vehicleDetails.getVehicleNumber()+" has been successfully registered!");
				resultMsg.setShowResult("1");
			}else{			
				resultMsg.setErrorMessage("Vehicle number "+vehicleDetails.getVehicleNumber()+" has not been registered!");
				resultMsg.setShowResult("0");			
			}
	 	
	 	resultMsg.setPageUrl("/DevTracker/vehicleDetails");
	 	return new ModelAndView("ResultPage", "resultMsg", resultMsg);
	}
 
/* @RequestMapping("/vehicleResultPage")
	public ModelAndView getVehicleErrorPage(HttpServletRequest request, HttpServletResponse response) {		 
	    String vehicleNumber = request.getParameter("vehicleNumber").trim();
	    ResultBean resultMsg = new ResultBean();
	    resultMsg.setErrorMessage("Vehicle Number "+vehicleNumber+" is already been registered!");
	    resultMsg.setShowResult("0");
	  	return new ModelAndView("ResultPage", "resultMsg", resultMsg);
		} */
}
