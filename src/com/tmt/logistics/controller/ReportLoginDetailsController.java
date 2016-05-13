package com.tmt.logistics.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tmt.logistics.bean.LoginReportDetail;
import com.tmt.logistics.dao.LoginReportDao;

@Controller
public class ReportLoginDetailsController{
	
	@Autowired
	LoginReportDao loginReportDao;
	
	
	@RequestMapping("/loginReport")
	public ModelAndView getLoginReportScreen() {		
		List<LoginReportDetail> loginReportDetail = loginReportDao.retrieveLoginData();				
		return new ModelAndView("LoginReport", "loginReportDetail", loginReportDetail);
	}
	
	@RequestMapping("/loginUpdate")
	 protected void loginUpdation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		    String userName = request.getParameter("username").trim();
		    System.out.println("=======> "+userName);
		    if(!"".equals(userName)){
			String[] splitToken = userName.split("_");			
			String status = splitToken[0].equals("di") ? "D" : "A";
			loginReportDao.updateLoginData(splitToken[1], status);
			updatePrinterObject(response);	 
		    }
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
