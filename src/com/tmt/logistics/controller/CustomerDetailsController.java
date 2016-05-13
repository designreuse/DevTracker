package com.tmt.logistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmt.logistics.bean.CustomerDetails;
import com.tmt.logistics.dao.CustomerDao;

@Controller
public class CustomerDetailsController {
	
 @Autowired
 CustomerDao customerDao;

 @RequestMapping("/CustomerDetails")
	public ModelAndView getTrackerScreen(@ModelAttribute CustomerDetails customerDetails) {			
			return new ModelAndView("CustomerDetails", "", null);
	}
 
 @RequestMapping("/CustomerAttach")
	public String insertCustomerData(@ModelAttribute CustomerDetails customerDetails) {
	
	 customerDao.insertCustomerData(customerDetails);		
		
		return "redirect:/CustomerDetails";
	}
}
