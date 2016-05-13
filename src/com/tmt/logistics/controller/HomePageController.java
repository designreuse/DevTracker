package com.tmt.logistics.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tmt.common.DistanceCalculator;
import com.tmt.common.Utils;
import com.tmt.common.UtilsBean;
import com.tmt.logistics.bean.Invoice;
import com.tmt.logistics.bean.PlayForPay;
import com.tmt.logistics.bean.ResultBean;
import com.tmt.logistics.dao.AgentDao;
import com.tmt.logistics.dao.HomePageDao;

@Controller
public class HomePageController {
	
	@Autowired
	HomePageDao homePageDao;
	 
	@Autowired
	AgentDao agentDao;
	
	@RequestMapping("/home")
	public ModelAndView homeScreen() {
		System.out.println("----Comming here-------> ");
		return new ModelAndView("home", "map", null);
	}
	
	@RequestMapping("/map")
	public ModelAndView mapScreen() {
		return new ModelAndView("Map", "map", null);
	}
	
	@RequestMapping("/trackInMap")
	public ModelAndView trackInMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("----Track In Map-------> ");
		String invoiceNumber = request.getParameter("searchInvoice").trim();
		System.out.println("Invoice Number :: "+invoiceNumber);
		
		Invoice invoiceData = homePageDao.retrieveInvoiceDetails(invoiceNumber);
		
		if(invoiceData == null){
		ResultBean resultMsg = new ResultBean();
		resultMsg.setErrorMessage("Invoice Number "+invoiceNumber+" is not available to be tracked. Check your invoice number!");
		resultMsg.setShowResult("0");
		resultMsg.setPageUrl("/DevTracker/startup");
		return new ModelAndView("ResultPage", "resultMsg", resultMsg);		
		}else{
		JSONArray jObj = homePageDao.retrieveCoordinates(invoiceData);		
		Map<String, JSONArray> map = new HashMap<String, JSONArray>();
		map.put("coordinates", jObj);		
		return new ModelAndView("Map", "map", map);
		}
	}
	
	private ModelAndView retrieveCoordinates(Invoice invoiceData){		
		JSONArray jObj = homePageDao.retrieveCoordinates(invoiceData);		
		Map<String, JSONArray> map = new HashMap<String, JSONArray>();
		map.put("coordinates", jObj);		
		return new ModelAndView("Map", "map", map);
	}
		
	@RequestMapping("/confirmPayForPlay")
	public ModelAndView confirmPayForPlay(@ModelAttribute PlayForPay payForPlay, Model playForPay) {
		//UtilsBean utilBean;
		System.out.println("------> "+payForPlay.getFromAddress());
		playForPay.addAttribute("PlayForPay", new PlayForPay());
		
		String imeiNumber = agentDao.retrieveIMEINumber(payForPlay.getVehicleNumber());	 
		System.out.println("Imei Number :::: "+imeiNumber);
		
		if(imeiNumber == null){
			ResultBean resultMsg = new ResultBean();
			resultMsg.setErrorMessage("Vehicle Number "+payForPlay.getVehicleNumber()+" is not available to be tracked. Check your vehicle number!");
			resultMsg.setShowResult("0");
			resultMsg.setPageUrl("/DevTracker/payForPlay");
			return new ModelAndView("ResultPage", "resultMsg", resultMsg);
		}else{
			payForPlay.setImeiNumber(imeiNumber);
			String fromAddress = payForPlay.getFromAddress().replaceAll(",", "").replaceAll(" ", "+");
			String toAddress = payForPlay.getToAddress().replaceAll(",", "").replaceAll(" ", "+");
			String distance = DistanceCalculator.calculateRoute(fromAddress, toAddress);
			System.out.println("===From===> "+fromAddress);
			System.out.println("===To===> "+toAddress);
			System.out.println("totalDistance::: "+distance);
			payForPlay.setDistance(distance);
			return new ModelAndView("ConfirmPayForPlay", "payForPlay", payForPlay);
		}
	}
	
	@RequestMapping("/confirmPlay4PayData")
	public String confirmPlay4PayData(@ModelAttribute PlayForPay playForPay) {
		System.out.println(Utils.showBean(playForPay, true));
	 homePageDao.insertPlayForPayData(playForPay);	
	 return "redirect:/startup";
	}
}
