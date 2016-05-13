package com.tmt.logistics.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmt.common.Utils;
import com.tmt.logistics.bean.AssignVehicle;
import com.tmt.logistics.bean.ResultBean;
import com.tmt.logistics.dao.AssignVehicleDao;

@Controller
public class AssignVehicleController {

	@Autowired
	AssignVehicleDao assignVehicleDao;
	
		
	@RequestMapping("/assignVehicle")	
	public ModelAndView getConnectTrackerScreen(@ModelAttribute AssignVehicle assgnVehicle, HttpSession session) {			
				
		assgnVehicle.setParent_id((String)session.getAttribute("handler_id"));
		assgnVehicle.setRole_id((String)session.getAttribute("role_id"));
		AssignVehicle assignVehicle = assignVehicleDao.retrieveListData(assgnVehicle);	
		
			Map<String, List> map = new HashMap<String, List>();		  
			//map.put("custList", assignVehicle.getCustName());
			map.put("userList", assignVehicle.getUserList());
			map.put("vehicleList", Arrays.asList(Arrays.toString(assignVehicle.getVehicleNumber().toArray()).replace("[", "").replace("]", "").toUpperCase().split(",")));
			map.put("imeiList", assignVehicle.getImeiList());
			return new ModelAndView("AssignVehicle", "map", map);
		}
	
	@RequestMapping("/insertMap")
	public ModelAndView updateCustomerVehicleMap(@ModelAttribute AssignVehicle assignVehicle) {	
		System.out.println("->>>>>>>----> "+Utils.showBean(assignVehicle, true));
		   int pageRedirect = assignVehicleDao.updateCustVehicleMapping(assignVehicle);	
		   ResultBean resultMsg = new ResultBean();
			if(pageRedirect == 1){
				resultMsg.setSuccessMessage("User name "+assignVehicle.getUserName()+" has been mapped successfully with vehicle number "+assignVehicle.getVehNumber()+"!");
				resultMsg.setShowResult("1");
			}else{			
				resultMsg.setErrorMessage("User name "+assignVehicle.getUserName()+" has not been mapped successfully with vehicle number "+assignVehicle.getVehNumber()+"!");
				resultMsg.setShowResult("0");			
			}
			resultMsg.setPageUrl("/DevTracker/assignVehicle");
			return new ModelAndView("ResultPage", "resultMsg", resultMsg);
	}
	
	@RequestMapping("/confirmMap")
	public ModelAndView getConfirmScreen(@ModelAttribute AssignVehicle assignVehicle) {		
		
		return new ModelAndView("ConfirmAssignVehicle", "map", null);
	}
	
}
