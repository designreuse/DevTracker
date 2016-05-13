package com.tmt.logistics.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmt.common.DistanceCalculator;
import com.tmt.common.UtilsBean;
import com.tmt.logistics.bean.CFAgent;
import com.tmt.logistics.bean.ResultBean;
import com.tmt.logistics.dao.AgentDao;

@Controller
public class CFAgentController {

 @Autowired
AgentDao agentDao;

 @RequestMapping("/agent")
	public ModelAndView getCFAgentScreen(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CFAgent agent) {		 
	    String id = request.getParameter("id").trim();
	    List<String> addressList = agentDao.retrieveDeliveryAddress(id);
	 
		Map<String, List> map = new HashMap<String, List>();		  
		map.put("addressList", addressList);		 
		return new ModelAndView("CFAgent", "map", map);
		}
 
 @RequestMapping("/confirmAgentData")
	public ModelAndView confirmAgentData(@ModelAttribute CFAgent agent) {	 
     int getInvoiceCount =  agentDao.isInvoiceAlreadyExists(agent.getInvoiceNumber());	 
	 if(getInvoiceCount >= 1){
		 ResultBean resultMsg = new ResultBean();
		 resultMsg.setErrorMessage("Invoice Number "+agent.getInvoiceNumber()+" is already been registered!");
		 resultMsg.setShowResult("0");
		 resultMsg.setPageUrl("/DevTracker/agent");
		 return new ModelAndView("ResultPage", "resultMsg", resultMsg);
	 }else{
			String fromAddress = agent.getFromAddress().replaceAll(",", "").replaceAll(" ", "+");
			String toAddress = agentDao.getToAddress(agent.getToAddress());
			toAddress = toAddress.replaceAll(",", "").replaceAll(" ", "+");
			String distance = DistanceCalculator.calculateRoute(fromAddress, toAddress);
			System.out.println("===From===> "+fromAddress);
			System.out.println("===To===> "+toAddress);
			System.out.println("totalDistance::: "+distance);
			agent.setDistance(distance);
	 }
	 return new ModelAndView("ConfirmDetails", "agent", agent);
	}
 
 @RequestMapping("/insertAgentData")
	public String insertAgentData(@ModelAttribute CFAgent agent) {
	 UtilsBean utilBean;	 
	 String imeiNumber = agentDao.retrieveIMEINumber(agent.getVehicleNumber());	 
	 System.out.println("Imei Number :::: "+imeiNumber);
	 if(imeiNumber == null){
		return "redirect:/resultPage?vehicleNumber="+agent.getVehicleNumber();
		}else{
			agent.setImei(imeiNumber);
			int pageRedirect =  agentDao.insertAgentDetails(agent);
			ResultBean resultMsg = new ResultBean();
			if(pageRedirect == 1){
				resultMsg.setSuccessMessage("Invoice number "+agent.getInvoiceNumber()+" has been mapped successfully with vehicle number "+agent.getVehicleNumber()+"!");
				resultMsg.setShowResult("1");
				resultMsg.setPageUrl("/DevTracker/agent");
				return "redirect:/agent?id="+agent.getParent_id();
			}else{
				return "redirect:/resultPage?invoiceNumber="+agent.getInvoiceNumber();
			}
			
		}
	}
 
 @RequestMapping("/resultPage")
	public ModelAndView getErrorScreen(HttpServletRequest request, HttpServletResponse response) {		 
	 	ResultBean resultMsg = new ResultBean();   
	 	String vehicleNumber = request.getParameter("vehicleNumber").trim();
	 	String invoiceNumber = request.getParameter("invoiceNumber").trim();
	    if("".equals(vehicleNumber)){
	    	resultMsg.setErrorMessage("Vehicle Number "+vehicleNumber+" is not available to be tracked. Check your vehicle number!");
	    }else{
	    	resultMsg.setErrorMessage("Invoice number "+invoiceNumber+" has not been mapped successfully!");
		 }
	    resultMsg.setShowResult("0");
	    resultMsg.setPageUrl("/DevTracker/agent");
	    return new ModelAndView("ResultPage", "resultMsg", resultMsg);
		} 
}
