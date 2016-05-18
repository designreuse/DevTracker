package com.tmt.logistics.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tmt.common.Utils;
import com.tmt.logistics.bean.AssignVehicle;
import com.tmt.logistics.bean.CustomerReportDetail;
import com.tmt.logistics.dao.CustomerReportDao;

@Controller
public class ReportCustomerDetailsController{
	
	@Autowired
	CustomerReportDao customerReportDao;
	
	Map<String, String> vehicleConnector;
	Map<String, String> customerdetails;
	Map<String, String> logindetails;
	Map<String, String> driverdetails;
	
	@RequestMapping("/customerReport")
	public ModelAndView getCustomerReportScreen() {		
		List<CustomerReportDetail> vehicleReportDetail = customerReportDao.retrieveVehicleData();		
		vehicleConnector = constructMapObject(vehicleReportDetail, true, false, false, false);
		List<CustomerReportDetail> customerReportDetail = customerReportDao.retrieveCustomerData();
		customerdetails  = constructMapObject(customerReportDetail, false, true, false, false);
		List<CustomerReportDetail> loginReportDetail = customerReportDao.retrieveLoginData();
		logindetails = constructMapObject(loginReportDetail, false, false, true, false);
		List<CustomerReportDetail> driverReportDetail = customerReportDao.retrieveDriverData();
		driverdetails = constructMapObject(driverReportDetail, false, false, false, true);		
		//List<CustomerReportDetail> customerReportDetailList = constructBeanObject(vehicleConnector, customerdetails, logindetails, driverdetails);
		List<CustomerReportDetail> customerReportDetailList = customerReportMappedObj(vehicleConnector, customerdetails, logindetails, driverdetails);
		return new ModelAndView("customerReport", "customerReportDetailList", customerReportDetailList);
	}
	
	private Map<String, String> constructMapObject(List<CustomerReportDetail> customerReportDetail, boolean vehicleFlag, boolean customerFlag, boolean loginFlag, boolean driverFlag){
		
		Map<String, String> mapObject = new HashMap<String, String>();				
		
		for(CustomerReportDetail customerData : customerReportDetail){
			if(vehicleFlag)
				mapObject.put(customerData.getVehicle_number(), customerData.getParent_id()+"#"+customerData.getRole_id()+"#"+customerData.getMap_status()+"#"+customerData.getBroker_parent_id());	
			else if(customerFlag)
				mapObject.put(customerData.getHandler_id(), customerData.getParent_id()+"#"+customerData.getCustomerName());	
			else if(loginFlag)
				mapObject.put(customerData.getHandler_id(), customerData.getParent_id()+"#"+customerData.getUsername()+"#"+customerData.getRole_id()+"#"+customerData.getStatus()+"#"+customerData.getPhone());
			else if(driverFlag)
				mapObject.put(customerData.getVehicle_number(), customerData.getDriverName());
		}
		
		return mapObject;
	}
	
	/*public List<CustomerReportDetail> constructBeanObject(Map<String, String> vehicleConnector, Map<String, String> customerDetails, Map<String, String> loginDetails, Map<String, String> driverDetails){
		List<CustomerReportDetail> customerReportDataList = new LinkedList<CustomerReportDetail>();
		//System.out.println("******* "+customerDetails);
		//System.out.println("@@@@@@@* "+vehicleConnector);
		Iterator iter = vehicleConnector.entrySet().iterator();		
		while(iter.hasNext()){
			String[] brokerData = null;
			String user         = null;
			String brokerName   = null;
			String parent_id    = null;
			String ownerName    = null;
			Map.Entry<String, String> mapIter = (Map.Entry<String, String>) iter.next();
			String vehicleNumber              = mapIter.getKey();
			String handler_id                 = mapIter.getValue();
			String driverName                 = driverDetails.get(vehicleNumber);		
			if(customerDetails.get(handler_id) != null){
			String[] spitToken = customerDetails.get(handler_id).split("#");
			 parent_id   = spitToken[0];
			 ownerName   = spitToken[1];
			}else
				parent_id = handler_id;
			
			if(loginDetails.get(parent_id) == null){
				brokerData  = customerDetails.get(parent_id).split("#");
				user        = loginDetails.get(brokerData[0]);				
				brokerName  = ownerName ;
				ownerName   = brokerData[1];
			}else
				user        = loginDetails.get(parent_id);
						
			CustomerReportDetail custReportData = new CustomerReportDetail();
			custReportData.setVehicle_number(vehicleNumber);
			custReportData.setDriverName(driverName);
			custReportData.setOwnerName(ownerName);
			custReportData.setBrokerName(brokerName);
			custReportData.setUsername(user);
			
			System.out.println("vehicleNumber :::  "+vehicleNumber + " OwnerName ::: "+ownerName + " BrokerName ::: "+brokerName + " UserName ::: "+user);
			customerReportDataList.add(custReportData);
		}
		return customerReportDataList;
	}*/
	
	public List<CustomerReportDetail> customerReportMappedObj(Map<String, String> vehicleConnector, Map<String, String> customerDetails, Map<String, String> loginDetails, Map<String, String> driverDetails){
	List<CustomerReportDetail> customerReportDataList = new LinkedList<CustomerReportDetail>();
	Iterator iter = vehicleConnector.entrySet().iterator();		
	while(iter.hasNext()){
		TokenBean tokenBean;
		CustomerReportDetail custReportData = new CustomerReportDetail();
		Map.Entry<String, String> mapIter = (Map.Entry<String, String>) iter.next();
		String vehicleNumber              = mapIter.getKey();
		String vehicleConnectorValue      = mapIter.getValue();
		
		custReportData.setVehicle_number(vehicleNumber);
		
		String[] spitToken = vehicleConnectorValue.split("#");
		String parent_id   = spitToken[0];
		int role_id        = Integer.parseInt(spitToken[1]);
		//System.out.println(role_id+" $$$$$$$$$$$$$$$$ "+vehicleNumber);
		custReportData.setMap_status(spitToken[2]);
		String broker_parent_id = spitToken[3];
		//System.out.println("%%%%%%%%%%%%%%%%%%%% "+broker_parent_id);
		if(role_id > 1){			
		String[] loginDetailsValue = (broker_parent_id == null || broker_parent_id.equals("0") ? loginDetails.get(parent_id).split("#") : loginDetails.get(broker_parent_id).split("#"));
			
		String login_parent_id   = loginDetailsValue[0];
		String login_user_name   = loginDetailsValue[1];
		int login_role_id     = Integer.parseInt(loginDetailsValue[2]);
		if(login_role_id == 3){
			custReportData.setBrokerName(login_user_name);
			custReportData.setPhone(loginDetailsValue[4]);
			
			tokenBean = getTokenBean(loginDetails, parent_id);						
			custReportData.setOwnerName(tokenBean.getUser_name());	
			
			
			tokenBean = getTokenBean(loginDetails, tokenBean.getParent_id());						
			custReportData.setUsername(tokenBean.getUser_name());
			
		}else if(login_role_id == 2){
			custReportData.setOwnerName(login_user_name);						
			tokenBean = getTokenBean(loginDetails, login_parent_id);						
			custReportData.setUsername(tokenBean.getUser_name());			
		}
		}else if(role_id == 1){
			tokenBean = getTokenBean(loginDetails, parent_id);			
			custReportData.setUsername(tokenBean.getUser_name());
		}		
		customerReportDataList.add(custReportData);				
	}
	
	return customerReportDataList;
	}
	
	private TokenBean getTokenBean(Map<String, String> loginDetails, String parent_id){
		TokenBean tokenBean = new TokenBean();
		String[] retOwnerValue = loginDetails.get(parent_id).split("#");
		tokenBean.setParent_id(retOwnerValue[0]);
		tokenBean.setUser_name(retOwnerValue[1]);
		tokenBean.setStatus(retOwnerValue[3]);
		tokenBean.setPhone(retOwnerValue[4]);
		return tokenBean;
	}
		
	public class TokenBean{
		private String parent_id;
		private String user_name;
		private int role_id;
		private String status;
		private String phone;
		
		public String getParent_id() {
			return parent_id;
		}
		public void setParent_id(String parent_id) {
			this.parent_id = parent_id;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public int getRole_id() {
			return role_id;
		}
		public void setRole_id(int role_id) {
			this.role_id = role_id;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}	
	}
	
	
	@RequestMapping("/disassociateVehicle")	
	public void dissociateVehicle(@ModelAttribute CustomerReportDetail custReportVehicle, HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {			
		String dataString = request.getParameter("dataString").trim();
		String[] splitToken = dataString.split("#");
		custReportVehicle.setVehicle_number(splitToken[0]);
		custReportVehicle.setRole_id(splitToken[1]);
		custReportVehicle.setParent_id(splitToken[2]);
		customerReportDao.updateVehicleDisassociation(custReportVehicle);	
		updatePrinterObject(response);	
		}
	
	@RequestMapping("/disassociateBroker")	
	public void disassociateBroker(@ModelAttribute CustomerReportDetail custReportVehicle, HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {			
		String dataString = request.getParameter("dataString").trim();
		System.out.println("*********************** "+dataString);
		String[] splitToken = dataString.split("#");
		custReportVehicle.setPhone(splitToken[0]);
		//custReportVehicle.setRole_id(splitToken[1]);
		custReportVehicle.setParent_id(splitToken[1]);
		custReportVehicle.setVehicle_number(splitToken[2]);
		customerReportDao.updateBrokerDisassociation(custReportVehicle);	
		updatePrinterObject(response);	
		}
	
private void updatePrinterObject(HttpServletResponse response)throws ServletException, IOException{
		
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
        myObj.addProperty("success", true);	        
        
        out.println(myObj.toString());	 
        out.close();
	}
}
