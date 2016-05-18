package com.tmt.logistics.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tmt.logistics.bean.ConfigureAssociation;
import com.tmt.logistics.bean.ResultBean;

@Controller
public class ConfigureAssociationController {

			
	@RequestMapping("/configureAssociation")	
	public ModelAndView configureAsscociation(@ModelAttribute ConfigureAssociation configureAssociation, HttpSession session) {			
				
		session.getAttribute("handler_id");
		session.getAttribute("role_id");		
		
		Map<String, List> map = new HashMap<String, List>();		  
		map.put("configureRoles", Arrays.asList("Admin", "Owner"));
		return new ModelAndView("ConfigureAssociation", "map", map);
		}
	
	@RequestMapping("/associateVehicleAndOwner")
	public void associateMap(@RequestBody final String json, HttpServletResponse response)throws IOException {	
		ObjectMapper mapper = new ObjectMapper();
        System.out.println("===========================================================");
 
        Map<String, String> ids = mapper.readValue(json, HashMap.class);
        String replace = ids.get("vehicle").toString().replace("[","").replace("]","");
        List<String> myList = new ArrayList<String>(Arrays.asList(replace.split(",")));
        System.out.println("@@@@@:>:: "+myList);
        System.out.println("@@@@@::: "+ids.get("owner"));


        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
 
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        
        
        JsonObject myObj = new JsonObject();
	    myObj.addProperty("success", true);  
        
        out.println(myObj.toString());	 
        out.close();
        
        //1 Owner many brokers

        //1 Owner many vehicles 1 broker


        //1 owner 1 vehicle 1 broker

	}
	
}
