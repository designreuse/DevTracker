package com.tmt.logistics.controller;
/**
 *
 * @author Nagesh Chauhan
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tmt.logistics.bean.TestTracker;
import com.tmt.logistics.dao.TestTrackerDao;

@Controller
public class TestTrackerController {

 @Autowired
 TestTrackerDao testTrackerDao;

 @RequestMapping("/testScreen")
	public ModelAndView getTrackerScreen(@ModelAttribute TestTracker testTracker) {		
		
	 List<String> imeiList = testTrackerDao.getTestTrackerUserList();
		
		Map<String, List> map = new HashMap<String, List>();		  
		map.put("imeiList", imeiList);		
		return new ModelAndView("testScreen", "map", map);
	}
 
 @RequestMapping("/test")
 protected void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String imeiNumber = request.getParameter("imeiNumber").trim();
		
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
 
        TestTracker testTrackerInfo = getInfo(imeiNumber);
        JsonElement testTrackerInfoObj = gson.toJsonTree(testTrackerInfo);
        if(testTrackerInfo.getMobile() == null){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }
        myObj.add("testTrackerInfo", testTrackerInfoObj);
        out.println(myObj.toString());
 
        out.close();
	}
 
 private TestTracker getInfo(String imeiNumber) {		 
	TestTracker testTrackerObj = testTrackerDao.getTestTracker(imeiNumber);
	 return testTrackerObj;
 }
 
 @RequestMapping("/testTracker")
	public String insertTestTrackerData(@ModelAttribute TestTracker testTracker) {
		System.out.println("Tracker Detail:::  " + testTracker.getImei()+" ------> "+testTracker.getMobile()+" ---> "+testTracker.getProvider()+ " -------> "+testTracker.getSim());
		/*if (user != null)
			userService.insertData(user);*/
		return "redirect:/testScreen";
	}
 
 
}
